package com.sunshineapp.jmsintegrationadapter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildResultDto extends BaseDto {

    private String appKey;
    private boolean success;
    private String fileName;
    private String errorDescription;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return "BuildResultDto{" +
                "appKey='" + appKey + '\'' +
                ", success=" + success +
                ", fileName='" + fileName + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                '}';
    }

}
