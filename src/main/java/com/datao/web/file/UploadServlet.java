package com.datao.web.file;

import com.datao.dao.PictureDAO;
import com.datao.dao.ZoneDAO;
import com.datao.entity.Picture;
import com.datao.entity.User;
import com.datao.entity.Zone;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by 海涛 on 2016/3/24.
 */
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Zone zone = new ZoneDAO().findById(user.getId());

        if ("1".equals(page)) {

            String path = upFile(request, response);

            Picture picture = new Picture();
            if (path != null) {
                picture.setStuid(user.getId());
                picture.setPhoto(path);

                new PictureDAO().addPicture(picture);
            } else {
                response.sendError(500);
            }

            response.sendRedirect("/zone.do");

        } else if ("2".equals(page)) {
            //上传头像前,先把以前的头像给删除了
            File oldHead = new File("h:/upload", zone.getHeadimg());
            if (!"123.jpg".equals(zone.getHeadimg())) {
                System.out.println("删除旧文件:" + oldHead.delete());
            }

            String path = upFile(request, response);

            zone.setHeadimg(path);
            new ZoneDAO().addHeadImg(zone);

            response.sendRedirect("");
        }
    }

    private String upFile(HttpServletRequest request, HttpServletResponse response) {
        File file = new File("h:/upload");

        //判断文件夹在不在
        if (!file.exists()) {
            file.mkdirs(); //如果不存在,新建文件夹
        }

        //临时文件夹
        File temPath = new File("H:/temp");
        if (!temPath.exists()) {
            temPath.mkdirs();
        }

        //判断form中的enctype是否是:multipart/form-data
        if (ServletFileUpload.isMultipartContent(request)) {

            DiskFileItemFactory itemFactory = new DiskFileItemFactory();
            //设置缓冲区
            itemFactory.setSizeThreshold(1024 * 1024);
            //设置临时文件
            itemFactory.setRepository(temPath);

            ServletFileUpload servletFileUpload = new ServletFileUpload(itemFactory);
            //设置文件的最大体积
            servletFileUpload.setSizeMax(1024 * 1024 * 10);

            try {
                //解析表单元素,将表单元素分为文件元素和非文件元素
                List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
                for (FileItem fileItem : fileItemList) {
                    if (!fileItem.isFormField()) {
                        //文件元素

                        //获取文件的mime头
                        String contentType = fileItem.getContentType();
                        //获取文件的真实名
                        String fileName = fileItem.getName();
                        String exmName = fileName.substring(fileName.lastIndexOf("."));
                        //获取文件的大小
                        long fileSize = fileItem.getSize();

                        InputStream inputStream = fileItem.getInputStream();
                        fileName = UUID.randomUUID().toString() + exmName;

                        FileOutputStream fileOutputStream = new FileOutputStream(new File(file, fileName));

                        IOUtils.copy(inputStream, fileOutputStream);

                        fileOutputStream.flush();
                        fileOutputStream.close();
                        inputStream.close();
                        return fileName;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
