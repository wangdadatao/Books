package com.datao.web.user.buy;

import com.datao.pojo.User;
import com.datao.pojo.Zone;
import com.datao.service.UserService;
import com.datao.web.BaseServlet;
import com.google.common.collect.Maps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 海涛 on 2016/4/20.
 */
@WebServlet("/user/userfindbalance.do")

public class UserFindBalanceServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserService();
        Map<String, Object> result = Maps.newHashMap();
        User user = getUser(req);
        if (user != null) {
            Zone zone = userService.finBalance(user);
            result.put("status", "success");
            result.put("money", zone.getMoney());
        } else {
            result.put("status", "error");
            result.put("errorMessage", "请登录后再试!");
        }
        sendJson(resp, result);
    }
}
