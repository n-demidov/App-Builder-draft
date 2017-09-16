package com.sunshineapp.backend.services;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.sunshineapp.backend.annotations.Backend;
import com.sunshineapp.backend.exceptions.SendMomException;
import com.sunshineapp.backend.models.AdminAlerts;
import com.sunshineapp.jmsintegrationadapter.dto.BuildTaskDto;
import com.sunshineapp.jmsintegrationadapter.mom.MomProperties;
import com.sunshineapp.jmsintegrationadapter.mom.MomWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.core.convert.ConversionService;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Named
@Backend
public class BuildTasksSender {

    private static final AMQP.BasicProperties MESSAGE_PROPERTIES = MessageProperties.PERSISTENT_TEXT_PLAIN;
    private static final String FILE_PATH_TEMPLATE = "/%s/";
    private static final String STUB_MESSAGE_TEMPLATE = "Hello from backend message";
    private static final String MOM_SENDING_ERROR_MSG = "Can't sent build task to MOM.";
    private static final Logger logger = LoggerFactory.getLogger(BuildTasksSender.class);
    private static final Marker fatal = MarkerFactory.getMarker("FATAL");

    private final ConversionService conversionService;

    @Inject
    public BuildTasksSender(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public void send(String userKey) {
        try {
            int i = 5;
            while (i != 0) {
                final MomWrapper momWrapper = new MomWrapper();
                final Channel channel = momWrapper.connect();

                sendMessage(channel, userKey);

                momWrapper.close();
                i--;
            }
        } catch (TimeoutException | IOException e) {
            logger.error(fatal, "send userKey={}", userKey, e);
            new AdminAlerts().alertAdmins(MOM_SENDING_ERROR_MSG);
            throw new SendMomException(MOM_SENDING_ERROR_MSG, e);
        }
    }

    private void sendMessage(Channel channel, String userKey) throws IOException {
        final BuildTaskDto buildTaskTo = createStubBuildTaskDto(userKey);

        channel.basicPublish(
                "",
                MomProperties.TASK_QUEUE_NAME,
                MESSAGE_PROPERTIES,
                conversionService.convert(buildTaskTo, byte[].class));

        logger.debug("sent userKey={}, buildTaskTo={}", userKey, buildTaskTo);
    }

    private BuildTaskDto createStubBuildTaskDto(String userKey) {
        final BuildTaskDto buildTaskTo = new BuildTaskDto();

        buildTaskTo.setAppKey(userKey);
        buildTaskTo.setFilePath(String.format(FILE_PATH_TEMPLATE, userKey));
        buildTaskTo.setTemplate(STUB_MESSAGE_TEMPLATE);

        return buildTaskTo;
    }

}
