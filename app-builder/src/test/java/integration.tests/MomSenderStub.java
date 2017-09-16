package integration.tests;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.sunshineapp.jmsintegrationadapter.converters.BuildTaskDtoConverter2Bytes;
import com.sunshineapp.jmsintegrationadapter.dto.BuildTaskDto;
import com.sunshineapp.jmsintegrationadapter.mom.MomProperties;
import com.sunshineapp.jmsintegrationadapter.mom.MomWrapper;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MomSenderStub {

    private static final AMQP.BasicProperties MESSAGE_PROPERTIES = MessageProperties.PERSISTENT_TEXT_PLAIN;
    private static final String STUB_MESSAGE_TEMPLATE = "Hello from sunshineapp-stub message";

    private static final BuildTaskDtoConverter2Bytes buildTaskDtoConverter2Bytes = new BuildTaskDtoConverter2Bytes();

    public static void main(String[] argv) throws IOException, TimeoutException {
        int i = 5;
        while (i != 0) {
            final MomWrapper momWrapper = new MomWrapper();
            final Channel channel = momWrapper.connect();

            sendMessage(channel);

            momWrapper.close();
            i--;
        }
    }

    private static void sendMessage(Channel channel) throws IOException {
        final BuildTaskDto buildTaskTo = createStubBuildTaskDto();

        channel.basicPublish(
                "",
                MomProperties.TASK_QUEUE_NAME,
                MESSAGE_PROPERTIES,
                buildTaskDtoConverter2Bytes.convert(buildTaskTo));

        System.out.println(" [x] Sent '" + buildTaskTo + "'");
    }

    private static BuildTaskDto createStubBuildTaskDto() {
        final BuildTaskDto buildTaskTo = new BuildTaskDto();

        buildTaskTo.setAppKey(STUB_MESSAGE_TEMPLATE);

        return buildTaskTo;
    }

}
