package com.datao.web.user.buy;

import com.datao.pojo.User;
import com.datao.error.DataAccessException;
import com.datao.service.UserService;
import com.datao.web.BaseServlet;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 海涛 on 2016/4/20.
 * 付钱
 */
@WebServlet("/user/paymoney.do")
public class UserPayMoneyServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        String password = req.getParameter("password");
        UserService userService = new UserService();
        Map<String, Object> result = Maps.newHashMap();

        User user = getUser(req);

        if (StringUtils.isNotEmpty(ids) && user != null && StringUtils.isNotEmpty(password)) {
            try {
                userService.deductMoney(user, ids,password);
            } catch (DataAccessException e) {
                result.put("status", "error");
                result.put("errorMessage", e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("errorMessage", "购买失败,参数错误!");
        }
        sendJson(resp, result);
    }
}
