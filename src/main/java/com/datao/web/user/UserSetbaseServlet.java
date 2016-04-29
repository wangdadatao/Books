package com.datao.web.user;

import com.google.common.collect.Maps;
import com.datao.pojo.User;
import com.datao.error.DataAccessException;
import com.datao.service.UserService;
import com.datao.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 海涛 on 2016/4/15.
 * user设置基本资料
 */
@WebServlet("/usersetbase.do")
public class UserSetbaseServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request);
        Map<String, Object> result = Maps.newHashMap();
        UserService userService = new UserService();

        if (user != null) {

            String petname = request.getParameter("petname");
            String sex = request.getParameter("sex");
            String age = request.getParameter("age");
            String phonenum = request.getParameter("phonenum");

            try {
                userService.upUser(user.getId(), petname,  sex, age, phonenum);
                result.put("status", "success");
            } catch (DataAccessException e) {
                result.put("status", "error");
                result.put("errorMessage", e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("errorMessage", "参数错误");
        }
        sendJson(response, result);
    }
}
