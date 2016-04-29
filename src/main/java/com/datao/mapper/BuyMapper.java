package com.datao.mapper;

import com.datao.pojo.Buy;

/**
 * Created by 海涛 on 2016/4/21.
 */
public interface BuyMapper {

    //查询是否购买了该书籍并返回该书籍
    Buy findByUserIdandBookId(Integer userid, Integer bookid);

    //删除已购买的书籍
    void delBuyByBuy(Buy buy);
}
