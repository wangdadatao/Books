package com.datao.mapper;

import com.datao.pojo.User;
import com.datao.pojo.Zone;

/**
 * Created by 海涛 on 2016/4/20.
 */
public interface ZoneMapper {

    Zone findBalance(User user);

    void upZone(Zone zone);
}
