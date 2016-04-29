package com.datao.web.book;

import com.datao.pojo.Book;
import com.datao.service.BookService;
import com.datao.web.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 海涛 on 2016/4/17.
 * 书
 */
@WebServlet("/book/showbook/do")
public class ShowBookServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        BookService bookService = new BookService();

        if (StringUtils.isNumeric(bookId)) {
            Book book = bookService.findBookByid(bookId);
            if(book == null){
                resp.sendError(404,"你要查看的书籍不存在或者已被管理员删除");
            }else{
                req.setAttribute("book",book);
                forward(req,resp,"book/showbook.jsp");
            }
        } else {
            resp.sendError(404, "你要查看的书籍不存在或者已被管理员删除");
        }
    }
}
