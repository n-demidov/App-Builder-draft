package com.sunshineapp.jmsintegrationadapter.mom;

public class MomProperties {

    public static final String SERVER_URL = "rabbitmq.docker";
    public static final int SERVER_PORT = 5672;
    public static final String TASK_QUEUE_NAME = "sunshineapp_queue";
    public static final boolean IS_DURABLE_QUEUE = true;
    public static final int NETWORK_RECOVERY_INTERVAL = 10000;

    private MomProperties() {}

}
