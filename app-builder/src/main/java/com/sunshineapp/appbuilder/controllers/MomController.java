package com.sunshineapp.appbuilder.controllers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sunshineapp.appbuilder.model.annotations.AppBuilder;
import com.sunshineapp.appbuilder.model.workers.MomWorkerFacade;
import com.sunshineapp.jmsintegrationadapter.dto.BuildTaskDto;
import com.sunshineapp.jmsintegrationadapter.mom.MomProperties;
import com.sunshineapp.jmsintegrationadapter.mom.MomWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Named
@AppBuilder
public class MomController {

    private static final boolean AUTO_ACKNOWLEDGE = false;
    private static final int PREFETCH_COUNT  = 1;

    private static final Logger logger = LoggerFactory.getLogger(MomController.class);
    private final MomWorkerFacade momWorkerFacade;
    private final ConversionService conversionService;

    @Inject
    public MomController(@AppBuilder MomWorkerFacade momWorkerFacade,
                         ConversionService conversionService) {
        this.momWorkerFacade = momWorkerFacade;
        this.conversionService = conversionService;
    }

    public void init() throws IOException, InterruptedException, TimeoutException {
        logger.info("init: connecting to MOM server...");
        final MomWrapper momWrapper = new MomWrapper();
        final Channel channel = momWrapper.connect();

        channel.basicQos(PREFETCH_COUNT);   // accept only one unack-ed message at a time

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    logger.info("received: consumerTag={}, envelope={}", consumerTag, envelope);
                    final BuildTaskDto buildTaskDto = conversionService.convert(body, BuildTaskDto.class);

                    momWorkerFacade.doWork(buildTaskDto);
                } finally {
                    logger.info("done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        channel.basicConsume(MomProperties.TASK_QUEUE_NAME, AUTO_ACKNOWLEDGE, consumer);
        logger.info("init: successfully connected to MOM server and waiting for messages.");
    }

}
