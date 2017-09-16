package com.sunshineapp.appbuilder.config;
import com.sunshineapp.jmsintegrationadapter.backend.CommonBackendApi;

public class Endpoints {

    private static final String BACKEND_URI = "http://backend.docker/";
    public static final String BACKEND_BUILD_NOTIFICATION_ENDPOINT = BACKEND_URI + CommonBackendApi.BUILD_NOTIFICATION;

}
