package com.sunshineapp.backend.services;

import com.sunshineapp.appbackendintegration.dto.AppbackendDto;
import com.sunshineapp.backend.annotations.Backend;
import com.sunshineapp.backend.exceptions.AbstractBackendException;
import com.sunshineapp.backend.models.AdminAlerts;
import com.sunshineapp.backend.models.AppbackendSender;
import com.sunshineapp.backend.models.dto.AppDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@Backend
public class AppService {

    private static final String stubMessage = "stub";
    private static final int APPBACKEND_DELETION_SUCCESS_RESPOND = 200;
    private static final String CAN_NOT_DELETE_APPBACKEND = "Can't delete appbackend. Please, write to administrator.";
    private static final Logger logger = LoggerFactory.getLogger(AppService.class);
    private static final Marker fatal = MarkerFactory.getMarker("FATAL");

    private final BuildTasksSender buildTasksSender;
    private final AppbackendSender appbackendSender;

    @Inject
    public AppService(@Backend BuildTasksSender buildTasksSender,
                      @Backend AppbackendSender appbackendSender) {
        this.buildTasksSender = buildTasksSender;
        this.appbackendSender = appbackendSender;
    }

    public String create(String customerKey) {
        logger.debug("create customerKey={}", customerKey);

        buildTasksSender.send(customerKey);
        return "Build task was successfully sent for user " + customerKey;
    }

    public List<AppDto> getAll(String customerKey) {
        return Arrays.asList(createStubApp(customerKey), createStubApp(customerKey));
    }

    public AppDto get(String customerKey, String appKey) {
        return createStubApp(customerKey);
    }

    public AppDto update(String customerKey, AppDto appDto) {
        logger.debug("update customerKey={}", customerKey);

        return appDto;
    }

    public AppDto delete(String customerKey, String appKey) {
        logger.debug("delete customerKey={}", customerKey);

        // Сначала удаляем данные из AppBackend
        deleteAppbackend(appKey);

        return createStubApp(customerKey);
    }

    private void deleteAppbackend(String appKey) {
        final ResponseEntity<AppbackendDto> responseEntity = appbackendSender.deleteAppbackend(appKey);
        final int responseStatusCode = responseEntity.getStatusCodeValue();

        if (responseStatusCode != APPBACKEND_DELETION_SUCCESS_RESPOND) {
            logger.error(fatal,
                    "The response code from AppBackend isn't match with expected, appKey={}, responseCode={}, expectedCode={}",
                    appKey, responseStatusCode, APPBACKEND_DELETION_SUCCESS_RESPOND);

            new AdminAlerts().alertAdmins("Почему-то с AppBackend вернулся другой код результата.");
            throw new AbstractBackendException(CAN_NOT_DELETE_APPBACKEND);
        }
    }

    private AppDto createStubApp(String customerKey) {
        final AppDto mockApp = new AppDto();

        mockApp.setAppKey(stubMessage +  "_" + customerKey);
        mockApp.setFilePath(stubMessage);
        mockApp.setStatus(stubMessage);
        mockApp.setStatusDetails(stubMessage);

        return mockApp;
    }

}
