package com.datao.web.user;

import com.datao.dao.BookDAO;
import com.datao.pojo.Book;
import com.datao.service.BookService;
import com.datao.util.Page;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 海涛 on 2016/3/19.
 * 学生页
 */
@WebServlet("/userhome.do")
public class UserHomeServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String key = request.getParameter("key");
        System.out.println("收到的Key是:" + key);

        if (key == null) {
            doGet(request, response);
        } else {
            List<Book> books = new BookDAO().findByKey(key);
            System.out.println(books.size());
            System.out.println("我是userhome,我搜索了,我要展示搜索内容了...");
            if (books.size() <= 0) {
                doGet(request, response);
            } else {
                Page<Book> page = new Page<>("1", books.size());
                page.setItems(books);
                request.setAttribute("page", page);
                request.getRequestDispatcher("WEB-INF/views/userlist.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageNo = request.getParameter("p");

        BookService bookService = new BookService();

        Page page = bookService.showIndexTopic(pageNo);

        request.setAttribute("page", page);
        forward(request, response, "userlist.jsp");
    }
}
