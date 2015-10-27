package com.softtanck.qqzone.bean;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 10/27/2015
 */
public class VideoMsg extends ZoneBaseMsg {
    private String videoUrl;//视频地址

    private int videoTime;//视频地址

    public VideoMsg() {
        setMsgType(VIDEO_MSG);//视频说说
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(int videoTime) {
        this.videoTime = videoTime;
    }
}
