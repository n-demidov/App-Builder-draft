package com.sunshineapp.backend.controllers;

import com.sunshineapp.backend.annotations.Backend;
import com.sunshineapp.backend.config.BackendApi;
import com.sunshineapp.backend.models.dto.AppDto;
import com.sunshineapp.backend.services.AppService;
import com.sunshineapp.backend.services.BuildTasksSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;
import java.util.List;

@RestController
public class AppController extends AbstractController {

    private final AppService appService;

    @Inject
    public AppController(@Backend AppService appService,
                         @Backend BuildTasksSender buildTasksSender) {
        this.appService = appService;
    }

    @RequestMapping(method = RequestMethod.POST, value = BackendApi.APPS_API)
    public ResponseEntity<String> create(@PathVariable String customerKey) {
        final String result = appService.create(customerKey);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET, value = BackendApi.APPS_API)
    public List<AppDto> getAll(@PathVariable String customerKey) {
        return appService.getAll(customerKey);
    }

    @RequestMapping(method = RequestMethod.GET, value = BackendApi.APP_API)
    public AppDto get(@PathVariable String customerKey, @PathVariable String appKey) {
        return appService.get(customerKey, appKey);
    }

    @RequestMapping(method = RequestMethod.PUT, value = BackendApi.APP_API)
    public AppDto update(@PathVariable String customerKey, @PathVariable String appKey, @RequestBody AppDto appDto) {
        appDto.setAppKey(appKey);
        return appService.update(customerKey, appDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = BackendApi.APP_API)
    public AppDto delete(@PathVariable String customerKey, @PathVariable String appKey) {
        return appService.delete(customerKey, appKey);
    }

}
