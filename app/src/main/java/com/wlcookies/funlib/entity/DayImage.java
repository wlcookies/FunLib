package com.wlcookies.funlib.entity;

public class DayImage {
    private String url; // 图片字段
    private String copyrightlink; // 搜索引擎内容
    private String title; // 标题
    private String copyright; // 版权

    public DayImage(String url, String copyrightlink, String title, String copyright) {
        this.url = url;
        this.copyrightlink = copyrightlink;
        this.title = title;
        this.copyright = copyright;
    }

    public DayImage() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyrightlink() {
        return copyrightlink;
    }

    public void setCopyrightlink(String copyrightlink) {
        this.copyrightlink = copyrightlink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
