package com.softtanck.qqzone.bean;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 10/27/2015
 */
public class VoiceMsg extends ZoneBaseMsg {
    private String voiceUrl;//语音地址

    private int voiceTime;//语音时间

    public VoiceMsg() {
        setMsgType(VOICE_MSG);//语音说说
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public int getVoiceTime() {
        return voiceTime;
    }

    public void setVoiceTime(int voiceTime) {
        this.voiceTime = voiceTime;
    }
}
