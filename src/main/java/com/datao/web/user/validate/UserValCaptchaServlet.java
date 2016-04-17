package com.datao.web.user.validate;

import com.datao.service.UserService;
import com.datao.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/16.
 * 验证验证码是否正确
 */
@WebServlet("/user/valcaptcha.do")
public class UserValCaptchaServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String captcha = req.getParameter("captcha");
        UserService userService = new UserService();

        if (StringUtils.isNotEmpty(captcha)) {
            HttpSession session = req.getSession();
            String capt = (String) session.getAttribute("captcha");
            if (capt == null) {
                sendText(resp, "false");
            } else {
                if (capt.equals(captcha)) {
                    sendText(resp, "true");
                } else {
                    sendText(resp, "false");
                }
            }
        } else {
            sendText(resp, "false");
        }
    }
}
