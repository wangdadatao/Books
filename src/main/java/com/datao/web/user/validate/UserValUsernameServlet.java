package com.datao.web.user.validate;

import com.datao.pojo.User;
import com.datao.service.UserService;
import com.datao.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/16.
 * 注册验证用户名是否已存在
 */
@WebServlet("/user/valusername.do")
public class UserValUsernameServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        System.out.println(username);

        UserService userService = new UserService();

        if (StringUtils.isNotEmpty(username)) {
            User user = userService.findUserByName(username);

            if (user == null) {
                sendText(resp, "true");
            } else {
                sendText(resp, "false");
            }
        } else {
            sendText(resp, "false");
        }
    }
}
