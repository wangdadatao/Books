package com.datao.web.safe;

import com.datao.web.BaseServlet;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 海涛 on 2016/3/22.
 * 产生验证码
 */
@WebServlet("/captcha.png")
public class CaptchaServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(98, 150, 226)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));

        //设置验证码的参数
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setMinLength(2);    //最小长度
        wordFactory.setMaxLength(2);    //最大长度
        service.setWordFactory(wordFactory);

        //设置类型
        response.setContentType("image/png");

        OutputStream outputStream = response.getOutputStream(); //获取输出流
        String captcha = EncoderHelper.getChallangeAndWriteImage(service, "png", outputStream);
        System.out.println("验证码为:" + captcha);

        //把验证码储存到session中
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha);

        outputStream.flush();
        outputStream.close();

    }
}
