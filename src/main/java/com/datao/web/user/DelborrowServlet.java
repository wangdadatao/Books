package com.datao.web.user;

import com.datao.dao.BuyDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/24.
 */
public class DelborrowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer borrowid = Integer.valueOf(request.getParameter("borrowid"));

        //删除借书记录
        new BuyDAO().delBorrow(borrowid);

        response.sendRedirect("/zone.do");

    }
}
