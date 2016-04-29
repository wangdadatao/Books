package com.datao.web.user;

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
 * Created by 海涛 on 2016/3/19.
 */
@WebServlet("/userreg.do")
public class UserRegServlet extends BaseServlet {

    /**
     * 学生账号注册
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");

        String capt = (String) request.getSession().getAttribute("captcha");
        UserService userService = new UserService();
        Map<String, Object> result = Maps.newHashMap();

        if (StringUtils.isNotEmpty(captcha) && captcha.equals(capt)) {
            if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password)) {
                try {
                    userService.addUser(name, password);
                    result.put("status", "success");
                } catch (DataAccessException e) {
                    result.put("status", "error");
                    result.put("errorMessage", e.getMessage());
                }
            } else {
                result.put("status", "error");
                result.put("errorMessage", "参数错误!");
            }
        } else {
            result.put("status", "error");
            result.put("errorMessage", "验证码错误!");
        }
        sendJson(response, result);
    }
}
