package com.sunshineapp.backend.models;

import com.sunshineapp.appbackendintegration.config.CommonAppbackendApi;
import com.sunshineapp.appbackendintegration.dto.AppbackendDto;
import com.sunshineapp.backend.annotations.Backend;
import com.sunshineapp.backend.config.CustomOptions;
import com.sunshineapp.backend.exceptions.NoAccessToAppBackendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
@Backend
public class AppbackendSender {

    private static final Logger logger = LoggerFactory.getLogger(AppbackendSender.class);
    private static final Marker fatal = MarkerFactory.getMarker("FATAL");

    public ResponseEntity<AppbackendDto> getAppbackend(String appKey) {
        return execute(HttpMethod.GET, new HttpEntity(null), appKey);
    }

    public ResponseEntity<AppbackendDto> updateAppbackend(AppbackendDto appbackendDto) {
        logger.debug("updateAppbackend appbackendDto={}", appbackendDto);

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<AppbackendDto> httpEntity = new HttpEntity<>(appbackendDto, headers);

        return execute(HttpMethod.PUT, httpEntity, appbackendDto.getAppKey());
    }

    public ResponseEntity<AppbackendDto> deleteAppbackend(String appKey) {
        logger.debug("deleteAppbackend appKey={}", appKey);

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> httpEntity = new HttpEntity<>(appKey, headers);

        return execute(HttpMethod.DELETE, httpEntity, appKey);
    }

    private ResponseEntity<AppbackendDto> execute(HttpMethod httpMethod, HttpEntity<?> httpEntity, String appKey) {
        try {
            final RestTemplate restTemplate = new RestTemplate();

            return restTemplate.exchange(
                    CustomOptions.APPBACKEND_API,
                    httpMethod,
                    httpEntity,
                    AppbackendDto.class,
                    createParams(appKey));
        } catch (ResourceAccessException e) {
            logger.error(fatal, "execute: No access to AppBackend service, appKey={}", appKey, e);

            // send alerts to emails
            new AdminAlerts().alertAdmins("No access to AppBackend service");
            throw new NoAccessToAppBackendException(e);
        }
    }

    private Map<String, String> createParams(String appKey) {
        final Map<String, String> params = new HashMap<>();
        params.put(CommonAppbackendApi.APP_KEY, appKey);
        return params;
    }

}
