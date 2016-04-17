package com.datao.service;

import com.datao.dao.UserDAO;
import com.datao.dao.ZoneDAO;
import com.datao.entity.User;
import com.datao.entity.Zone;
import com.datao.error.DataAccessException;
import com.datao.util.ConfingProp;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by 海涛 on 2016/4/15.
 * 学生服务类
 */
public class UserService {
    UserDAO userDao = new UserDAO();
    ZoneDAO zoneDao = new ZoneDAO();

    public User userlog(String name, String password) {
        User user = userDao.findByName(name);
        if (user == null) {
            throw new DataAccessException("帐号或密码错误!");
        } else {
            password = DigestUtils.md5Hex(password + ConfingProp.get("user.password.salt"));
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new DataAccessException("帐号或密码错误!");
            }
        }
    }

    public void upUser(Integer userid, String petname, String sex, String age, String phonenum) {
        Zone zone = new Zone();
        if (StringUtils.isNotEmpty(petname) && StringUtils.isNotEmpty(petname)
                && StringUtils.isNotEmpty(sex) && StringUtils.isNumeric(age)) {

            zone.setUserid(userid);
            zone.setPetname(petname);
            zone.setSex(sex);
            zone.setAge(Integer.valueOf(age));
            zone.setPhonenum(phonenum);

            zoneDao.upInfor(zone);

        } else {
            throw new DataAccessException("参数错误!!!!");
        }
    }

    //通过username查找user
    public User findUserByName(String username) {
        return userDao.findByName(username);
    }

    //注册账户
    public void addUser(String name, String password) {
        User user = userDao.findByName(name);
        if (user == null) {
            password = DigestUtils.md5Hex(password + ConfingProp.get("user.password.salt"));

            user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setCreatetime(DateTime.now().toString("yyyy-HH-dd hh-ss-mm"));

            Integer id = userDao.addGetId(user);
            user = userDao.findById(id);

            Zone zone = new Zone();
            zone.setUserid(user.getId());
            zone.setSex("1");
            zone.setPetname(user.getName());
            zone.setHeadimg("ac2cbdc0-dc01-48e4-8232-05673f555622.jpg");

            zoneDao.addZone(zone);
        } else {
            throw new DataAccessException("该用户名已被占用!");
        }

    }
}
