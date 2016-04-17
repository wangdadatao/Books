package com.datao.web.user;

import com.datao.dao.ZoneDAO;
import com.datao.entity.User;
import com.datao.entity.Zone;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/22.
 * 用户设置
 */
@WebServlet("/userset.do")
public class SetServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goSet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String setId = request.getParameter("setId");
        if (setId == null) {
            goSet(request, response);
        } else {
            if (setId.equals("1")) {
                String userid = request.getParameter("userid");
                String petname = request.getParameter("petname");
                String majro = request.getParameter("major");
                String sex = request.getParameter("sex");
                String age = request.getParameter("age");
                String phonenum = request.getParameter("phonenum");

                Zone zone = new Zone();
                zone.setUserid(Integer.valueOf(userid));
                zone.setPetname(petname);
                zone.setSex(sex);
                zone.setAge(Integer.valueOf(age));
                zone.setPhonenum(phonenum);

                ZoneDAO zoneDao = new ZoneDAO();
                zoneDao.upInfor(zone);

                goSet(request, response);

            }else if(setId.equals("2")){
                String userid = request.getParameter("userid");
                String email = request.getParameter("email");
                if(email != null){
                    Zone zone = new Zone();
                    zone.setEmail(email);
                    zone.setUserid(Integer.valueOf(userid));

                    ZoneDAO zonedao = new ZoneDAO();
                    zonedao.upEmail(zone);

                    goSet(request,response);
                }
            }
        }
    }


    private void goSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Zone zone = new ZoneDAO().findById(user.getId());

        request.setAttribute("zone",zone);

        request.getRequestDispatcher("/WEB-INF/views/set.jsp").forward(request, response);

    }
}
