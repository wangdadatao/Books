package com.datao.web.file;

import com.datao.dao.ZoneDAO;
import com.datao.pojo.User;
import com.datao.pojo.Zone;
import com.datao.web.BaseServlet;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by 海涛 on 2016/3/24.
 * 得到用户头像
 */
@WebServlet("/gethead.img")
public class GetheadServlet extends BaseServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我运行了");
        //获得头像的名字
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Zone zone = new ZoneDAO().findById(user.getId());

        //创建文件
        File file = new File("h:/upload", zone.getHeadimg());

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
