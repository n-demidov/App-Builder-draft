package com.sunshineapp.templatemanager.templates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sunshineapp.templatemanager.templates.tasks.Task;

import java.util.Collections;
import java.util.List;

public class AndroidAppImpl extends ApplicationTemplate {

    private final static Logger logger = LoggerFactory.getLogger(AndroidAppImpl.class);
    private String iconPath;

    @Override
    public List<Task> createFlow() {
        //TODO Specific actions should be implemented
        logger.debug("Create flow for Android");
        return Collections.emptyList();
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
