package com.datao.web.safe;

import com.datao.dao.ZoneDAO;
import com.datao.entity.User;
import com.datao.entity.Zone;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/3/23.
 */
public class emailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");
        final String nemail = request.getParameter("email");

        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setHostName("smtp.126.com");
        simpleEmail.setAuthentication("guyan1","XJYZwyj");
        simpleEmail.setCharset("UTF-8");
        simpleEmail.setStartTLSEnabled(true);

        try {
            simpleEmail.setFrom("guyan1@126.com");
            simpleEmail.setSubject("绑定邮箱验证码:");
            simpleEmail.setMsg("验证码为:7788,半小时内有效!");
            simpleEmail.addTo(nemail);
            simpleEmail.send();

            //把发送的验证码储存到session中
            HttpSession session = request.getSession();
            session.setAttribute("email","7788");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute("email");

        String getCaptcha = request.getParameter("captcha");

        if(captcha.equals(getCaptcha)){
            User user = (User) session.getAttribute("user");
            Zone zone = new ZoneDAO().findById(user.getId());

            zone.setEmail(request.getParameter("email"));

            new ZoneDAO().upEmail(zone);

            request.getRequestDispatcher("/WEB-INF/views/set.jsp").forward(request,response);
        }
    }
}
