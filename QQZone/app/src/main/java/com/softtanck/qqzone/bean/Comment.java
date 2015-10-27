package com.softtanck.qqzone.bean;

/**
 * @author : Tanck
 * @Description : TODO 评论
 * @date 10/27/2015
 */
public class Comment {

    private String name;//评论人名字

    private int id;// 评论人id

    private String replyname;//回复的名字

    private int replyid;//回复的id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReplyname() {
        return replyname;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }

    public int getReplyid() {
        return replyid;
    }

    public void setReplyid(int replyid) {
        this.replyid = replyid;
    }
}
