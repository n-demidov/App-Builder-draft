package com.sunshineapp.appbackend.services;

import com.sunshineapp.appbackend.annotations.Appbackend;
import com.sunshineapp.appbackendintegration.dto.AppbackendDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
@Appbackend
public class AppBackendService {

    private static final String STUB_TEXT = "stub";
    private static final Logger logger = LoggerFactory.getLogger(AppBackendService.class);

    public AppbackendDto get(String appKey) {
        logger.debug("get appKey={}", appKey);

        return createStubAppbackendDto(appKey);
    }

    public AppbackendDto update(AppbackendDto appBackendDto) {
        logger.debug("update appBackendDto={}", appBackendDto);

        return appBackendDto;
    }

    public AppbackendDto delete(String appKey) {
        logger.debug("delete appKey={}", appKey);

        return createStubAppbackendDto(appKey);
    }

    private AppbackendDto createStubAppbackendDto(String appKey) {
        final AppbackendDto stubAppBackendDto = new AppbackendDto();

        stubAppBackendDto.setAppKey(appKey);
        stubAppBackendDto.setBody(STUB_TEXT + "_" + appKey);

        return stubAppBackendDto;
    }

}
