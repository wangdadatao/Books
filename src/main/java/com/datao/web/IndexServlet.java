package com.datao.web;

import com.datao.dao.UserDAO;
import com.datao.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/17.
 * 进入主页
 */
@WebServlet("/index.do")
public class IndexServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初次登录查看是否有cookie
        Cookie[] myCookie = request.getCookies();
        String name = null;
        String pwd = null;
        if (myCookie != null) {
            for (Cookie cookie : myCookie) {
                if ("username".equals(cookie.getName())) {
                    name = cookie.getValue();
                } else if ("password".equals(cookie.getName())) {
                    pwd = cookie.getValue();
                }
            }
        }

        //如果有cookie 则直接进入home.do
        if (name != null && pwd != null) {
            User user = new UserDAO().findByName(name);

            if (user != null && user.getPassword().equals(pwd)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                System.out.println("设置了session对象,直接进入home!");
                response.sendRedirect("/userhome.do");
            }
        } else {
            //未找到cookie
            String code = request.getParameter("code");
            request.setAttribute("code", code);
            System.out.println("要进入主页了!");
            request.getRequestDispatcher("WEB-INF/views/indexx.jsp").forward(request, response);
        }
    }

}
