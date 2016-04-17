package com.datao.web.user;

import com.datao.dao.BuyDAO;
import com.datao.dao.PictureDAO;
import com.datao.dao.ZoneDAO;
import com.datao.entity.*;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 海涛 on 2016/3/21.
 * 个人空间
 */
@WebServlet("/userzone.do")
public class ZoneServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        goSet(request, response);
    }


    private void goSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Zone zone = new ZoneDAO().findById(user.getId());

        List<Picture> pictures = new PictureDAO().finByStuID(user.getId());
        List<Book> books = new BuyDAO().findByStuId(user.getId());
        List<Buy> borrows = new BuyDAO().userBooks(user.getId());

        request.setAttribute("pictures", pictures);
        request.setAttribute("borrows", borrows);
        request.setAttribute("books", books);
        request.setAttribute("zone", zone);
        request.getRequestDispatcher("/WEB-INF/views/zone.jsp").forward(request, response);

    }
}
