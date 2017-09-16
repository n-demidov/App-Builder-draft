package com.sunshineapp.appbuilder.model.workers;

import com.sunshineapp.appbuilder.model.annotations.AppBuilder;
import com.sunshineapp.jmsintegrationadapter.dto.BuildResultDto;
import com.sunshineapp.jmsintegrationadapter.dto.BuildTaskDto;
import com.sunshineapp.templatemanager.TemplateManagerWorker;
import com.sunshineapp.templatemanager.api.BuildIntent;
import com.sunshineapp.templatemanager.api.TemplateManager;
import com.sunshineapp.templatemanager.templates.AndroidAppImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@AppBuilder
public class MomWorkerFacade {

    private static final Logger logger = LoggerFactory.getLogger(MomWorkerFacade.class);
    private final TemplateManagerWorker templateManagerWorker;
    private final BackendNotifier backendNotifier;

    @Inject
    public MomWorkerFacade(@TemplateManager TemplateManagerWorker templateManagerWorker,
                           @AppBuilder BackendNotifier backendNotifier) {
        this.templateManagerWorker = templateManagerWorker;
        this.backendNotifier = backendNotifier;
    }

    public void doWork(BuildTaskDto buildTaskDto) {
        try {
            logger.debug("doWork buildTaskDto={}", buildTaskDto);
            Thread.sleep(7000);

            // TODO Разнести все на отдельные классы и сделать фасад для их объединения.

            buildApp();
            final String fileName = moveAppToStorage();
            notifyBackendSuccess(buildTaskDto.getAppKey(), fileName);
        } catch (InterruptedException e) {
            logger.error("doWork error", e);
            notifyBackendFailed(buildTaskDto.getAppKey(), e.getLocalizedMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void notifyBackendSuccess(String appKey, String fileName) {
        final BuildResultDto buildResultDto = new BuildResultDto();

        buildResultDto.setAppKey(appKey);
        buildResultDto.setSuccess(true);
        buildResultDto.setFileName(fileName);

        backendNotifier.sendBuildResult(buildResultDto);
    }

    private void notifyBackendFailed(String appKey, String errMessage) {
        final BuildResultDto buildResultDto = new BuildResultDto();

        buildResultDto.setAppKey(appKey);
        buildResultDto.setSuccess(false);
        buildResultDto.setErrorDescription(errMessage);

        backendNotifier.sendBuildResult(buildResultDto);
    }

    private void buildApp() {
        final AndroidAppImpl template = new AndroidAppImpl();
        template.setIconPath("124556/tmp/data/icon.png");
        final BuildIntent<AndroidAppImpl> intent = new BuildIntent<>(template);
        templateManagerWorker.buildApplication(intent);
    }

    private String moveAppToStorage() {
        return "stub-file-name";
    }

}
