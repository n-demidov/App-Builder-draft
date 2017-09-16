package com.sunshineapp.appbackendintegration.dto;

public class AppbackendDto {

    private String appKey;
    private String body;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "AppbackendDto{" +
                "appKey='" + appKey + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
