package com.datao.service;

import com.datao.dao.BuyDAO;
import com.datao.dao.UserDAO;
import com.datao.dao.ZoneDAO;
import com.datao.mapper.BuyMapper;
import com.datao.mapper.ZoneMapper;
import com.datao.pojo.Book;
import com.datao.pojo.Buy;
import com.datao.pojo.User;
import com.datao.pojo.Zone;
import com.datao.error.DataAccessException;
import com.datao.mapper.BookMapper;
import com.datao.util.ConfingProp;
import com.datao.util.GetSqlSession;
import com.google.common.collect.Lists;
import com.qiniu.util.Auth;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by 海涛 on 2016/4/15.
 * 学生服务类
 */
public class UserService {


    UserDAO userDao = new UserDAO();
    ZoneDAO zoneDao = new ZoneDAO();
    BuyDAO buyDao = new BuyDAO();

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

    //生成七牛的token
    public String getToken() {
        //获得AK和SK
        String AK = ConfingProp.get("qiniu.AK");
        String SK = ConfingProp.get("qiniu.SK");
        //指定上传空间
        final String BUCKETNAME = "pictures";
        //穿件auth对象
        Auth auth = Auth.create(AK, SK);
        return auth.uploadToken(BUCKETNAME);
    }

    //查找购物车里所选择的书籍
    public List<Book> finLotsBook(String[] bookIds) {
        SqlSessionFactory sqlSessionFactory = GetSqlSession.getSqlSession();
        SqlSession session = sqlSessionFactory.openSession();
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        List<String> ids = Lists.newArrayList(bookIds);
        List<Book> books = bookMapper.findLotsBook(ids);

        session.commit();
        session.close();

        return books;
    }


    //产看该用户还有多少余额
    public Zone finBalance(User user) {

        Zone zone = zoneDao.findById(user.getId());

        return zone;
    }

    //实现扣钱
    public void deductMoney(User user, String id, String password) {

        SqlSessionFactory sqlSessionFactory = GetSqlSession.getSqlSession();
        SqlSession session = sqlSessionFactory.openSession();
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        String[] iDs = id.split(",");
        List<String> ids = Lists.newArrayList(iDs);


        password = DigestUtils.md5Hex(password + ConfingProp.get("user.password.salt"));

        if (user.getPassword().equals(password)) {
            List<Book> books = bookMapper.findLotsBook(ids);
            Integer price = 0;
            for (Book b : books) {
                price += b.getPrice();
            }

            Zone zone = zoneDao.findByUserId(user.getId());

            Integer balance = Integer.valueOf(zone.getMoney());
            if (balance < price) {
                throw new DataAccessException("余额不足,购买失败!");
            } else {
                balance = balance - price;
            }

            String nowTime = DateTime.now().toString("yyyy-MM-dd HH-mm-ss");

            //加入已购买的数据库
            for (Book b : books) {
                buyDao.addBuy(user.getId(), b.getId(), nowTime);
            }

            zone.setMoney(balance.toString());

            zoneDao.upInfor(zone);
        } else {
            throw new DataAccessException("密码错误!");
        }

        session.commit();
        session.close();
    }

    //删除已经买的书籍
    public void delBuyBook(User user, Integer borrowid) {
        SqlSession sqlSession = GetSqlSession.getSqlSession().openSession();

        BuyMapper buyMapper = sqlSession.getMapper(BuyMapper.class);

        Buy buy = buyMapper.findByUserIdandBookId(user.getId(), borrowid);

        if (buy != null) {
            buyMapper.delBuyByBuy(buy);
        }

        sqlSession.commit();
        sqlSession.close();
    }


    //根据用户id获得Zone
    public Zone getZone(User user) {
        SqlSession sqlSession = GetSqlSession.getSqlSession().openSession();
        ZoneMapper zoneMapper = sqlSession.getMapper(ZoneMapper.class);


        Zone zone = zoneMapper.findBalance(user);
        sqlSession.commit();
        sqlSession.close();
        return zone;
    }

    //设置用户头像
    public void setHeadImg(User user, String headImg) {
        SqlSession sqlSession = GetSqlSession.getSqlSession().openSession();
        ZoneMapper zoneMapper = sqlSession.getMapper(ZoneMapper.class);

        Zone zone = zoneMapper.findBalance(user);

        zone.setHeadimg(headImg);

        zoneMapper.upZone(zone);

        sqlSession.commit();
        sqlSession.close();
    }

    //设置密码
    public void setPassword(User user, String nowPassword, String newPasswrod) {
        nowPassword = DigestUtils.md5Hex(nowPassword + ConfingProp.get("user.password.salt"));

        if (user == null) {
            throw new DataAccessException("参数错误！");
        } else {
            if (user.getPassword().equals(nowPassword)) {
                newPasswrod = DigestUtils.md5Hex(newPasswrod + ConfingProp.get("user.password.salt"));
                user.setPassword(newPasswrod);
                userDao.upUser(user);
            } else {
                throw new DataAccessException("当前密码错误！");
            }
        }
    }
}
