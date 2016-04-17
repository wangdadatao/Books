package com.datao.dao;

import com.datao.entity.Admin;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by 海涛 on 2016/3/16.
 */
public class AdminDAO {
    //通过名字查找对象
    public Admin findByName(String name) {
        String sql = "select * from admin where name=?";
        return DBhelper.query(sql, new BeanHandler<Admin>(Admin.class), name);
    }
}
