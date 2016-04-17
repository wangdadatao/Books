package com.datao.util;

import com.datao.error.DataAccessException;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by 海涛 on 2016/4/16.
 * 读取配置文件
 */
public class ConfingProp {

    private static Properties properties = new Properties();

    static {
        try{
            properties.load(ConfingProp.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataAccessException("读取配置文件异常");
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

}
