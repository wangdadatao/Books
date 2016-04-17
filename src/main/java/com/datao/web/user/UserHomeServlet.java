package com.datao.web.user;

import com.datao.dao.BookDAO;
import com.datao.entity.Book;
import com.datao.entity.User;
import com.datao.service.BookService;
import com.datao.util.Page;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by 海涛 on 2016/3/19.
 * 学生页
 */
@WebServlet("/userhome.do")
public class UserHomeServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                Page<Book> page = new Page<Book>("1", books.size());
                page.setItems(books);
                request.setAttribute("id", user.getId());
                request.setAttribute("page", page);
                request.setAttribute("books", page.getItems());
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


        /* List<Book> books = new BookDAO().queryAll();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String p = request.getParameter("p");
        System.out.println("收到的p为:" + p);

        Page<Book> page = new BookService().findByPage(p);
        System.out.println(page.getPageNo() + "-->" + page.getStart());


        request.setAttribute("id", user.getId());
        request.setAttribute("page", page);
        request.setAttribute("books", page.getLists());

        System.out.println("我是home我运行了!--我要去list页面了");
        request.getRequestDispatcher("WEB-INF/views/userlist.jsp").forward(request, response);*/
    }
}
