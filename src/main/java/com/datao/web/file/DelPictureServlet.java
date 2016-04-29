package com.datao.web.file;

import com.datao.dao.PictureDAO;
import com.datao.pojo.Picture;
import com.datao.pojo.User;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/26.
 * 删除照片
 */
@WebServlet("/delpicture.do")
public class DelPictureServlet extends BaseServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        String path = request.getParameter("photo");

        System.out.println("要删除的图片是：" + path);

        if (path != null) {
            Picture picture = new PictureDAO().findByPhoto(path);

            System.out.println("要删除的图片对像是：" + picture);

            if (user.getId().equals(picture.getUserid())) {

                System.out.println("相等，可以删除！");

                new PictureDAO().delPicture(path);

                File photo = new File("h:/upload", path);
                if (photo.exists()) {
                    photo.delete();
                }
            }
        }

        response.sendRedirect("/userzone.do?photo=1");

    }
}
