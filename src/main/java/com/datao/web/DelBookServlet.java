package com.datao.web;

import com.datao.dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/17.
 */
public class DelBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String)request.getParameter("id");
        System.out.println(id);
        BookDAO bookDAO = new BookDAO();
        bookDAO.delBook(Integer.valueOf(id));
        response.sendRedirect("/home.do");
    }
}
