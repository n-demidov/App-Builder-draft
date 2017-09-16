package com.sunshineapp.backend.controllers;

import com.sunshineapp.backend.config.BackendApi;
import com.sunshineapp.jmsintegrationadapter.dto.BuildResultDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildNotificationController extends AbstractController {

    private static final String BUILD_NOTIFICATION_WAS_RECEIVED_MSG = "Build notification was received.";

    @RequestMapping(method = RequestMethod.PUT, value = BackendApi.BUILD_NOTIFICATION)
    public String buildNotification(@RequestBody BuildResultDto buildResultDto) {
        System.out.println("Build response was received from AppBuilder: " + buildResultDto);

        return BUILD_NOTIFICATION_WAS_RECEIVED_MSG;
    }

}
