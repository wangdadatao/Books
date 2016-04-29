package com.datao.dao;

import com.datao.pojo.User;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by 海涛 on 2016/3/19.
 */
public class UserDAO {
    //通过名字查找用户
    public User findByName(String name) {
        String sql = "select * from user where name=?";
        return DBhelper.query(sql, new BeanHandler<User>(User.class), name);
    }

    //注册用户,新添加用户
    public void addUser(User user) {
        String sql = "insert into user(name,password,createtime) values(?,?,?) ";
        DBhelper.updater(sql, user.getName(), user.getPassword(), user.getCreatetime());
    }

    //通过id查找对象
    public User findById(Integer id) {
        String sql = "select * from user where id=?";
        return DBhelper.query(sql, new BeanHandler<User>(User.class), id);
    }

    //添加数据,并返回该条数据的主键
    public Integer addGetId(User user) {
        String sql = "insert into user (name, password, createtime) values(?,?,?)";
        return DBhelper.insert(sql, user.getName(), user.getPassword(), user.getCreatetime()).intValue();
    }

    //用户重新设置密码
    public void upUser(User user) {
        String sql = "update user set password = ? where id= ?";
        DBhelper.updater(sql, user.getPassword(), user.getId());
    }
}
