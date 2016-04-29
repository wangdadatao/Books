package com.datao.web.user;

import com.datao.error.DataAccessException;
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
 * Created by 海涛 on 2016/4/27.
 */
@WebServlet("/user/setpassword.do")
public class UserSetPasswrodServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nowPassword = req.getParameter("nowpassword");
        String newPasswrod = req.getParameter("newpassword");
        UserService userService = new UserService();
        Map<String,Object> result = Maps.newHashMap();
        try{
            userService.setPassword(getUser(req),nowPassword,newPasswrod);
            result.put("status","success");
        }catch (DataAccessException e){
            result.put("status","error");
            result.put("errorMessage",e.getMessage());
        }
       sendJson(resp,result);

    }
}
