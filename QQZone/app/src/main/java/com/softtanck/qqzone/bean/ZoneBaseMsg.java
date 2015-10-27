package com.softtanck.qqzone.bean;

import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO 说说基类
 * @date 10/27/2015
 */
public class ZoneBaseMsg {

    /**
     * 步长因子,防止adapter中混淆
     */
    protected static final int DEF_SPEED = 1000;

    /**
     * 普通说说
     */
    public static final int NOR_MSG = 0x1;

    /**
     * 语音说说
     */
    public static final int VOICE_MSG = 0x2;

    /**
     * 视频说说
     */
    public static final int VIDEO_MSG = 0x3;

    /**
     * 图片说说
     */
    public static final int IMG_MSG = 0x4;

    private String headUrl;//头像地址

    private String name;//名称

    private String time;//时间

    private boolean isVip;//是否为QQ会员

    private boolean isZoneVip;// 是否为黄钻

    private String summary;//空间摘要

    private List<Support> supports;//赞的人

    private int readTimes;//阅读次数

    private List<Comment> comments;//评论

    private String content;//说说内容

    private int MSG_TYPE;//说说类型

    private int phone;//手机类型

    public int getMsgType() {
        return MSG_TYPE;
    }

    /**
     * 设置消息类型
     *
     * @param code
     */
    public void setMsgType(int code) {
        MSG_TYPE += DEF_SPEED;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    public boolean isZoneVip() {
        return isZoneVip;
    }

    public void setIsZoneVip(boolean isZoneVip) {
        this.isZoneVip = isZoneVip;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Support> getSupports() {
        return supports;
    }

    public void setSupports(List<Support> supports) {
        this.supports = supports;
    }

    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
