package com.datao.web.user;

import com.datao.dao.BuyDAO;
import com.datao.pojo.User;
import com.datao.service.UserService;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/24.
 */
@WebServlet("/user/delbuy.do")
public class DelBuyServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer buyid = Integer.valueOf(request.getParameter("bookid"));

        System.out.println("要删除的书籍id是:" + buyid);

        UserService userService = new UserService();
        User user = getUser(request);
        userService.delBuyBook(user, buyid);


        response.sendRedirect("/userzone.do");

    }
}
