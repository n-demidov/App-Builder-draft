package com.sunshineapp.templatemanager;

import com.sunshineapp.templatemanager.api.TemplateManager;
import org.springframework.stereotype.Component;
import com.sunshineapp.templatemanager.api.BuildIntent;
import com.sunshineapp.templatemanager.templates.ApplicationTemplate;

@Component
@TemplateManager
public class TemplateManagerWorker {

    public void buildApplication(BuildIntent<? extends ApplicationTemplate> intent) {
        intent.build();
    }

}
