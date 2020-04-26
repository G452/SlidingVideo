package com.g2452.demo.bean;

/**
 * 作者：G
 * 时间：2020/4/23  13:57
 * 概述：
 */
public class VideoBean {
    private String videoUrl;
    private String videoImgUrl;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    public VideoBean(String videoUrl, String videoImgUrl) {
        this.videoUrl = videoUrl;
        this.videoImgUrl = videoImgUrl;
    }
}
