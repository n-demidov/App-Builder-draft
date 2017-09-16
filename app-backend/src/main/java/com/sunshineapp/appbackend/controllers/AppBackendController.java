package com.sunshineapp.appbackend.controllers;

import com.sunshineapp.appbackend.annotations.Appbackend;
import com.sunshineapp.appbackend.services.AppBackendService;
import com.sunshineapp.appbackendintegration.config.CommonAppbackendApi;
import com.sunshineapp.appbackendintegration.dto.AppbackendDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class AppBackendController {

    private final AppBackendService appBackendController;

    @Inject
    public AppBackendController(@Appbackend AppBackendService appBackendService) {
        this.appBackendController = appBackendService;
    }

    @RequestMapping(method = RequestMethod.GET, value = CommonAppbackendApi.APPBACKEND_API)
    public AppbackendDto get(@PathVariable String appKey) {
        return appBackendController.get(appKey);
    }

    @RequestMapping(method = RequestMethod.PUT, value = CommonAppbackendApi.APPBACKEND_API)
    public AppbackendDto update(@PathVariable String appKey, @RequestBody AppbackendDto appBackendDto) {
        appBackendDto.setAppKey(appKey);
        return appBackendController.update(appBackendDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = CommonAppbackendApi.APPBACKEND_API)
    public AppbackendDto delete(@PathVariable String appKey) {
        return appBackendController.delete(appKey);
    }

}
