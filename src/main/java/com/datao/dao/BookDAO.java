package com.datao.dao;

import com.datao.pojo.Book;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by 海涛 on 2016/3/17.
 */
public class BookDAO {
    //查看所有书籍
    public List<Book> queryAll() {
        String sql = "select * from book";
        return DBhelper.query(sql, new BeanListHandler<Book>(Book.class));
    }

    //根据关键字查找书籍
    public List<Book> findByKey(String key) {
        String sql = "select * from book where title like '%" + key + "%' or publishing like '%" + key + "%' or author like '%" + key + "%'";
        return DBhelper.query(sql, new BeanListHandler<Book>(Book.class));
    }

    //新增书籍
    public void addBook(Book book) {
        String sql = "INSERT into book(code,title,author,publishing,price) VALUES(?,?,?,?,?)";
        DBhelper.updater(sql, book.getCode(), book.getTitle(), book.getAuthor(), book.getPublishing(), book.getPrice());
    }

    //删除书籍
    public void delBook(Integer i) {
        String sql = "delete from book where id=?";
        DBhelper.updater(sql, i);
    }

    //修改书籍
    public void upBook(Book book) {
        String sql = "update book set code=?,title=?,author=?,publishing=?,price=? where id=?";
        DBhelper.updater(sql, book.getCode(), book.getTitle(), book.getAuthor(), book.getPublishing(), book.getPrice(),
                book.getId());
    }

    //根据id查找书籍
    public Book findById(Integer id) {
        String sql = "select * from book where id=?";
        return DBhelper.query(sql, new BeanHandler<Book>(Book.class), id);
    }

    //产看书籍的数目
    public Long findCount(){
        String sql = "select count(*) from book";
        return DBhelper.query(sql, new ScalarHandler<Long>());
    }

    //根据页码查找书籍
    public List<Book> findByPage(int start,int size){
        String sql = "select * from book limit ?,?";
        return DBhelper.query(sql, new BeanListHandler<Book>(Book.class),start,size);
    }

}
