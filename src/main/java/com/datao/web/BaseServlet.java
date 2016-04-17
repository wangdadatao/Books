package com.datao.web;

import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import com.datao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 海涛 on 2016/4/15.
 */
public class BaseServlet extends HttpServlet {


    public void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/" + url).forward(request, response);
    }

    //返回Json对象
    public void sendJson(HttpServletResponse response, Object result) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(result));
        out.flush();
        out.close();
    }

    //返回查询结果
    public void sendText(HttpServletResponse response, String result) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    //把ipv6修改成ipv4
    public String getIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    public User getUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("user");
    }


    //判断是否是Ajax请求
    public boolean isAjaxReq(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }


}

