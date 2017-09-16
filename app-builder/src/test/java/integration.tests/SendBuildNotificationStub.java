package integration.tests;

import com.sunshineapp.appbuilder.config.Endpoints;
import com.sunshineapp.jmsintegrationadapter.dto.BuildResultDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SendBuildNotificationStub {

    public static void main(String[] argv) throws IOException, TimeoutException {
        final RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BuildResultDto> entity = new HttpEntity<>(getBuildResultDto(), headers);
        ResponseEntity<String> response = restTemplate.exchange(Endpoints.BACKEND_BUILD_NOTIFICATION_ENDPOINT, HttpMethod.PUT, entity, String.class);

        System.out.println("sent to Backend. Response=" + response);
    }

    public static BuildResultDto getBuildResultDto() {
        final BuildResultDto buildResultDto = new BuildResultDto();

        buildResultDto.setAppKey("app-key-stub");
        buildResultDto.setSuccess(true);
        buildResultDto.setFileName("file-name-stub");

        return buildResultDto;
    }
}
