package com.datao.dao;

import com.datao.entity.Book;
import com.datao.entity.Buy;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by 海涛 on 2016/3/23.
 */
public class BuyDAO {
    //添加借书数据
    public void addBorrow(Integer userid,Integer bookId){
        String sql = "insert into buy(userid,bookid) values(?,?)";
        DBhelper.updater(sql,userid,bookId);
    }

    //查询某用户所借阅的所有书籍
    public List<Book> findByStuId(Integer userid){
        String sql ="SELECT b.* FROM book b INNER JOIN buy br on br.bookid = b.id WHERE br.userid =?";
        return DBhelper.query(sql,new BeanListHandler<Book>(Book.class),userid);
    }

    //查询某个学生的借书情况
    public List<Buy> userBooks(Integer userid){
        String sql = "select * from buy where userid=?";
        return DBhelper.query(sql,new BeanListHandler<Buy>(Buy.class),userid);
    }

    //删除学生的一条借书记录
    public void delBorrow(Integer buyid){
        String sql = "delete from buy where id=?";
        DBhelper.updater(sql,buyid);
    }
}
