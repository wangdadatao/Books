package com.datao.web.user;

import com.google.common.collect.Maps;
import com.datao.pojo.User;
import com.datao.error.DataAccessException;
import com.datao.service.UserService;
import com.datao.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 海涛 on 2016/3/19.
 * 学生登录
 */
@WebServlet("/userlog.do")
public class UserLogServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService();
        Map<String, Object> result = Maps.newHashMap();

        String name = request.getParameter("username");
        String password = request.getParameter("password");

        String rememberme = request.getParameter("rememberme");

        if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)) {
            try {
                User user = userService.userlog(name, password);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if ("remember-me".equals(rememberme)) {
                    Cookie namecookie = new Cookie("username", user.getName());
                    namecookie.setMaxAge(-1);
                    namecookie.setPath("/");
                    namecookie.setHttpOnly(true);
                    Cookie pwdcookie = new Cookie("password", user.getPassword());
                    pwdcookie.setMaxAge(-1);
                    pwdcookie.setHttpOnly(true);
                    pwdcookie.setPath("/");

                    //把设置好的cookie发送到客户端
                    response.addCookie(namecookie);
                    response.addCookie(pwdcookie);
                }
                result.put("status", "success");
            } catch (DataAccessException e) {
                result.put("status", "error");
                result.put("errorMessage", e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("errorMessage", "帐号或密码错误!");
        }
        sendJson(response, result);
    }
}
