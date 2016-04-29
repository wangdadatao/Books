package com.datao.pojo;

/**
 * Created by 海涛 on 2016/3/17.
 */
public class Book {
    private Integer id;
    private String code;
    private String title;
    private String author;
    private String publishing;
    private Integer price;
    private String type;
    private String why;
    private Integer readingnum;
    private Integer wantnum;
    private Integer readednum;
    private Integer replynum;
    private String createtime;
    private String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getReadingnum() {
        return readingnum;
    }

    public void setReadingnum(Integer readingnum) {
        this.readingnum = readingnum;
    }

    public Integer getWantnum() {
        return wantnum;
    }

    public void setWantnum(Integer wantnum) {
        this.wantnum = wantnum;
    }

    public Integer getReadednum() {
        return readednum;
    }

    public void setReadednum(Integer readednum) {
        this.readednum = readednum;
    }

    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }
}
