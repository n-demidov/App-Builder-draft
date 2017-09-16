package com.sunshineapp.jmsintegrationadapter.mom;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MomWrapper {

    private Connection connection;
    private Channel channel;

    public Channel connect() throws IOException, TimeoutException {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(MomProperties.SERVER_URL);
        factory.setPort(MomProperties.SERVER_PORT);
        factory.setAutomaticRecoveryEnabled(true);
        factory.setNetworkRecoveryInterval(MomProperties.NETWORK_RECOVERY_INTERVAL);  // attempt recovery every 10 seconds

        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(MomProperties.TASK_QUEUE_NAME, MomProperties.IS_DURABLE_QUEUE, false, false, null);

        return channel;
    }

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

}
