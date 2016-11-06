package com.healist.jobhunter.entity;

import java.util.Date;

/**
 * Created by Healist on 2016/11/6.
 */
public class jobInfo {
    private String title;
    private String date;
    private String where;
    private String url;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getWhere() {
        return where;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "jobInfo{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", where='" + where + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
