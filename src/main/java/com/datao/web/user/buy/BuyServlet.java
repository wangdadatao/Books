package com.datao.web.user.buy;

import com.datao.pojo.Book;
import com.datao.service.UserService;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 海涛 on 2016/3/23.
 * 购买书籍
 */
@WebServlet("/user/buybooks.do")
public class BuyServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");

        String[] bookIds = ids.split(",");
        UserService userService = new UserService();

        List<Book> books = userService.finLotsBook(bookIds);

        request.setAttribute("books", books);

        forward(request, response, "buybooks.jsp");
    }


}
