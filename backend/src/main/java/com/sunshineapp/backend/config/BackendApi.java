package com.sunshineapp.backend.config;

import com.sunshineapp.jmsintegrationadapter.backend.CommonBackendApi;

public class BackendApi extends CommonBackendApi {

    public static final String CUSTOMERS_API = BASE_API + "customers";
    public static final String CUSTOMER_API = CUSTOMERS_API + "/{customerKey}";
    public static final String APPS_API = CUSTOMER_API + "/apps";
    public static final String APP_API = APPS_API + "/{appKey}";
    public static final String APP_BACKEND_API = APP_API + "/appbackend";

    protected BackendApi() {}

}
