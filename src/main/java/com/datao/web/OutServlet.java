package com.datao.web;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/18.
 */
public class OutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我out运行了");
        //安全退出
        //1:移除session
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //2:删除cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies){
                if("username".equals(cookie.getName()) || "password".equals(cookie.getName())){
                    System.out.println("我删除了cookie");
                    Cookie cook = cookie;
                    cook.setHttpOnly(true);
                    cook.setPath("/");
                    cook.setMaxAge(0);
                    response.addCookie(cook);
                }
            }
        }

        //3:回到主页
        response.sendRedirect("/index.do");

    }
}
