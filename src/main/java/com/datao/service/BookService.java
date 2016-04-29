package com.datao.service;

import com.datao.dao.BookDAO;
import com.datao.pojo.Book;
import com.datao.util.Page;

import java.util.List;

/**
 * Created by 海涛 on 2016/3/26.
 */
public class BookService {

    private BookDAO bookDao = new BookDAO();

    //展示书籍  分页产看
    public Page<Book> showIndexTopic(String pageNo) {
        int pageSize = 20;

        int count = bookDao.findCount().intValue();
        Page<Book> page = new Page<>(pageNo, count, pageSize);
        List<Book> books = bookDao.findByPage(page.getStart(), page.getSize());

        page.setItems(books);
        return page;
    }

    //根据书籍iD查找书籍
    public Book findBookByid(String bookId) {
       return bookDao.findById(Integer.valueOf(bookId));
    }
}
