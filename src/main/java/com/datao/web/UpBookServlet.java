package com.datao.web;

import com.datao.dao.BookDAO;
import com.datao.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/17.
 */
public class UpBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publishing = request.getParameter("publishing");
        String price = request.getParameter("price");

        Book book = new Book();
        book.setId(Integer.valueOf(id));
        book.setCode(code);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishing(publishing);
        book.setPrice(Integer.valueOf(price));

        BookDAO bookDAO = new BookDAO();
        bookDAO.upBook(book);
        response.sendRedirect("/home.do");
    }
}
