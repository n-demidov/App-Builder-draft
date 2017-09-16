package com.sunshineapp.appbuilder.model.workers;

import com.sunshineapp.appbuilder.model.annotations.AppBuilder;
import com.sunshineapp.jmsintegrationadapter.dto.BuildResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import javax.inject.Named;
import com.sunshineapp.appbuilder.config.Endpoints;

@Named
@AppBuilder
public class BackendNotifier {

    private static final Logger logger = LoggerFactory.getLogger(BackendNotifier.class);
    private final static Marker fatal = MarkerFactory.getMarker("FATAL");

    public void sendBuildResult(BuildResultDto buildResultDto) {
        logger.debug("sendBuildResult buildResultDto={}", buildResultDto);

        try {
            final ResponseEntity<String> response = send(buildResultDto);
            logger.debug("sent to Backend, buildResultDto={}", response);
        } catch (RestClientException e) {
            logger.error(fatal, "sendBuildResult buildResultDto={}, error", buildResultDto, e);
        }
    }

    private ResponseEntity<String> send(BuildResultDto buildResultDto) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity httpEntity = createHttpEntity(buildResultDto);

        return restTemplate.exchange(
                Endpoints.BACKEND_BUILD_NOTIFICATION_ENDPOINT,
                HttpMethod.PUT,
                httpEntity,
                String.class);
    }

    private HttpEntity createHttpEntity(BuildResultDto buildResultDto) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(buildResultDto, headers);
    }

}
