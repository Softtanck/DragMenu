package com.softtanck.qqzone.bean;

import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO 图片说说
 * @date 10/27/2015
 */
public class ImageMsg extends ZoneBaseMsg {

    private List<String> imgUrls;//图片地址集合

    public ImageMsg() {
        setMsgType(IMG_MSG);//图片说说
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
