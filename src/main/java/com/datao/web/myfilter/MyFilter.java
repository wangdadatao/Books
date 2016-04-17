package com.datao.web.myfilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 海涛 on 2016/3/18.
 */
public class MyFilter extends AbstractFilter {
    String staticPath = null;
    String encode = "UTF-8";
    List<String> paths = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        staticPath = filterConfig.getInitParameter("StaticPath");
        String coder = filterConfig.getInitParameter("encoding");
        String urls = filterConfig.getInitParameter("URL");
        String[] us = urls.split(",");
        paths = Arrays.asList(us);

        if (coder != null && !coder.equals("")) {
            encode = coder;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(encode);

        String cUrl = request.getRequestURI();

        if (cUrl.startsWith(staticPath)) {
            //如果是静态资源则通过
            System.out.println("静态资源通过");
            filterChain.doFilter(request, response);
        } else {
            if (paths.contains(cUrl)) {
                //如果是登录的路径则通过
                System.out.println("默认路径通过:" + cUrl);
                filterChain.doFilter(request, response);
            } else {
                HttpSession session = request.getSession();
                if (session.getAttribute("user") != null) {
                    //如果已登录过的则通过
                    System.out.println("已经登录通过!");
                    filterChain.doFilter(request, response);
                } else {

                    System.out.println("没有登录运行我了");
                    response.sendRedirect("/index.do?code=1002");
                }
            }
        }
    }
}
