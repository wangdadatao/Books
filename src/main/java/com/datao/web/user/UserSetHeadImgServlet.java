package com.datao.web.user;

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
 * Created by 海涛 on 2016/4/22.
 */
@WebServlet("/user/settingheadimg.do")
public class UserSetHeadImgServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newHeadimg = req.getParameter("newheadimg");

        UserService userService = new UserService();
        Map<String, Object> result = Maps.newHashMap();

        try {
            userService.setHeadImg(getUser(req), newHeadimg);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "error");
            result.put("errorMessage", "参数错误！");
        }
        sendJson(resp, result);
    }
}
