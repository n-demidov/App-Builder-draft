package com.sunshineapp.jmsintegrationadapter.dto;

public class BuildTaskDto extends BaseDto {

    private String version;
    private String appKey;
    private String filePath;
    private String template;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BuildTaskDto{" +
                "version='" + version + '\'' +
                ", appKey='" + appKey + '\'' +
                ", filePath='" + filePath + '\'' +
                ", template='" + template + '\'' +
                '}';
    }

}
