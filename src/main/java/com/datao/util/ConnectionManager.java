package com.datao.util;

import com.datao.error.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by 海涛 on 2016/3/17.
 */
public class ConnectionManager {
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        Properties properties = new Properties();

        try {
            properties.load(ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties"));

            dataSource.setDriverClassName(properties.getProperty("jdbc.Driver"));
            dataSource.setUrl(properties.getProperty("jdbc.url"));
            dataSource.setUsername(properties.getProperty("jdbc.username"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));

            dataSource.setInitialSize(5);
            dataSource.setMaxIdle(20);
            dataSource.setMinIdle(5);
            dataSource.setMaxWaitMillis(5000);

        }catch (Exception e){
            e.printStackTrace();
            throw new DataAccessException(e,"读取config.properties文件发生异常");
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}