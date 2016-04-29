package com.datao.web;

import com.datao.dao.BookDAO;
import com.datao.pojo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 海涛 on 2016/3/17.
 * 管理员home
 */
@WebServlet("/adminhome.do")
public class HomeServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //关键字查询书籍
        String key = request.getParameter("key");

        System.out.println("我搜索了" + key);
        BookDAO bookDAO = new BookDAO();
        List<Book> books = null;
        if (key != null) {
            books = bookDAO.findByKey(key);
            request.setAttribute("books", books);
            request.getRequestDispatcher("WEB-INF/views/list.jsp").forward(request, response);

        } else {
            findBook(request, response, "1001");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        findBook(request, response, null);

    }

    private void findBook(HttpServletRequest request, HttpServletResponse response, String code) throws ServletException, IOException {
        //查询所有书籍
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.queryAll();

        request.setAttribute("books", books);
        request.getRequestDispatcher("WEB-INF/views/list.jsp?code=" + code).forward(request, response);
    }
}
