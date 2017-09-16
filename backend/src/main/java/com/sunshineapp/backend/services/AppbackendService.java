package com.sunshineapp.backend.services;

import com.sunshineapp.appbackendintegration.dto.AppbackendDto;
import com.sunshineapp.backend.annotations.Backend;
import com.sunshineapp.backend.models.AppbackendSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Backend
public class AppbackendService {

    private static final Logger logger = LoggerFactory.getLogger(AppbackendService.class);
    private final AppbackendSender appbackendSender;

    @Inject
    public AppbackendService(@Backend AppbackendSender appbackendSender) {
        this.appbackendSender = appbackendSender;
    }

    public AppbackendDto get(String customerKey, String appKey) {
        // add auth check
        // add caching

        final ResponseEntity<AppbackendDto> responseAppbackend = appbackendSender.getAppbackend(appKey);

        return responseAppbackend.getBody();
    }

    public AppbackendDto update(String customerKey, AppbackendDto appbackendDto) {
        logger.debug("update customerKey={}, appbackendDto={}", customerKey, appbackendDto);

        // add auth check
        // add validation
        // add caching

        final ResponseEntity<AppbackendDto> responseAppbackend = appbackendSender.updateAppbackend(appbackendDto);

        return responseAppbackend.getBody();
    }

}
