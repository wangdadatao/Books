package com.datao.dao;

import com.datao.entity.Picture;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by 海涛 on 2016/3/26.
 */
public class PictureDAO {
    //根据userID查找图片
    public List<Picture> finByStuID(Integer userid) {
        String sql = "select * from pictures where userid=?";
        return DBhelper.query(sql, new BeanListHandler<Picture>(Picture.class), userid);
    }

    //根据图片路径查找对象
    public Picture findByPhoto(String photo) {
        String sql = "select * from pictures where photo=?";
        return DBhelper.query(sql, new BeanHandler<Picture>(Picture.class), photo);
    }

    //添加图片
    public void addPicture(Picture p) {
        String sql = "insert into pictures(userid,photo) values(?,?)";
        DBhelper.updater(sql, p.getStuid(), p.getPhoto());
    }

    //删除图片
    public void delPicture(String photo) {
        String sql = "delete from pictures where photo=?";
        DBhelper.updater(sql, photo);
    }
}
