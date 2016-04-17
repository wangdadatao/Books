package com.datao.web.file;

import com.datao.web.BaseServlet;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 海涛 on 2016/3/26.
 * 得到图片
 */
@WebServlet("/getpicture.img")
public class GetPictureServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("photo");

        //创建文件
        File file = new File("h:/upload", path);

        response.setContentType("image/png");

        //建立IO流
        FileInputStream fileInputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();

        IOUtils.copy(fileInputStream, outputStream);

        outputStream.flush();
        outputStream.close();
        fileInputStream.close();
    }
}
