package com.datao.web;

import com.datao.dao.AdminDAO;
import com.datao.entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/17.
 * 管理员登录
 */
@WebServlet("/login.do")
public class LoginServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得登录帐号和密码
        String name = request.getParameter("username");
        String password = request.getParameter("password");


        AdminDAO adminDAO = new AdminDAO();
        Admin admin = null;
        //判断是否输入了帐号
        if (name != null) {
            admin = adminDAO.findByName(name);
        } else {
            response.sendRedirect("/index.do");
            return;
            //request.setAttribute("code","1002");
            //request.getRequestDispatcher("/WEB-INF/views/indexx.jsp").forward(request,response);
        }


        if (admin != null && admin.getPassword().equals(password)) {
            //登录成功设置session对象
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);

            String rememberme = request.getParameter("rememberme");

            //登录成功后设置cookies
            if ("remember-me".equals(rememberme)) {
                Cookie nameCookie = new Cookie("username", admin.getName());
                nameCookie.setMaxAge(60 * 60);//设置cookie生命周期
                nameCookie.setPath("/");
                nameCookie.setHttpOnly(true);

                Cookie pwdCookie = new Cookie("password", admin.getPassword());
                pwdCookie.setMaxAge(60 * 60);
                pwdCookie.setPath("/");
                pwdCookie.setHttpOnly(true);

                //把设置好的Cookie添加到客户端中
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }

            response.sendRedirect("/adminhome.do");
        } else {
            request.setAttribute("code", "1001");
            request.getRequestDispatcher("/WEB-INF/views/indexx.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
