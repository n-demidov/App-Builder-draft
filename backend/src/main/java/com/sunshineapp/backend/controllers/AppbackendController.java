package com.sunshineapp.backend.controllers;

import com.sunshineapp.appbackendintegration.dto.AppbackendDto;
import com.sunshineapp.backend.annotations.Backend;
import com.sunshineapp.backend.config.BackendApi;
import com.sunshineapp.backend.services.AppbackendService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;

@RestController
public class AppbackendController extends AbstractController {

    private final AppbackendService appbackendService;

    @Inject
    public AppbackendController(@Backend AppbackendService appbackendService) {
        this.appbackendService = appbackendService;
    }

    @RequestMapping(method = RequestMethod.GET, value = BackendApi.APP_BACKEND_API)
    public AppbackendDto get(@PathVariable String customerKey, @PathVariable String appKey) {
        return appbackendService.get(customerKey, appKey);
    }

    @RequestMapping(method = RequestMethod.PUT, value = BackendApi.APP_BACKEND_API)
    public AppbackendDto update(@PathVariable String customerKey, @PathVariable String appKey, @RequestBody AppbackendDto appbackendDto) {
        appbackendDto.setAppKey(appKey);
        return appbackendService.update(customerKey, appbackendDto);
    }

}
