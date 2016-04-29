package com.datao.mapper;

import com.datao.pojo.Book;

import java.util.List;

/**
 * Created by 海涛 on 2016/4/20.
 */
public interface BookMapper {

    List<Book> findLotsBook(List list);
}
