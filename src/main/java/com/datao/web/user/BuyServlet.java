package com.datao.web.user;

import com.datao.dao.BuyDAO;
import com.datao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/23.
 */
public class BuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] bookIds = ids.split(",");

        User user = (User) request.getSession().getAttribute("user");
        Integer stuid = user.getId();

        //如果购物车里有书籍,则添加到borrow里
        if(bookIds.length > 0){
            for(String i: bookIds){
                new BuyDAO().addBorrow(stuid,Integer.valueOf(i));
            }
        }
        response.sendRedirect("/zone.do");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
