package com.datao.dao;

import com.datao.pojo.Zone;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by 海涛 on 2016/3/21.
 */
public class ZoneDAO {

    //通过id查找资料
    public Zone findById(Integer id) {
        String sql = "select * from zone where id=?";
        return DBhelper.query(sql, new BeanHandler<Zone>(Zone.class), id);
    }

    //根据学生的user id 来添加学生资料
    public void upInfor(Zone z) {
        String sql = "update zone set petname=?, age=?, sex=?, phonenum=? ,email=?,headimg=?," +
                "money =?, autograph=?  where userid=?";
        DBhelper.updater(sql, z.getPetname(), z.getAge(), z.getSex(), z.getPhonenum(), z.getEmail(),
                z.getHeadimg(),z.getMoney(),z.getAutograph(),z.getUserid()
        );
    }

    //设置邮箱
    public void upEmail(Zone z) {
        String sql = "update zone set email=? where userid=?";
        DBhelper.updater(sql, z.getEmail(), z.getUserid());
    }

    //设置头像
    public void addHeadImg(Zone z) {
        String sql = "update zone set headimg=? where userid=?";
        DBhelper.updater(sql, z.getHeadimg(), z.getUserid());
    }

    //添加新的Zone
    public void addZone(Zone zone) {
        String sql = "insert into zone(userid, sex, petname,headimg) values(?,?,?,?)";
        DBhelper.updater(sql, zone.getUserid(), zone.getSex(), zone.getPetname(), zone.getHeadimg());
    }

    //根据userid查询
    public Zone findByUserId(Integer userid) {
        String sql = "select * from zone where userid = ?";
        return DBhelper.query(sql, new BeanHandler<Zone>(Zone.class), userid);
    }
}
