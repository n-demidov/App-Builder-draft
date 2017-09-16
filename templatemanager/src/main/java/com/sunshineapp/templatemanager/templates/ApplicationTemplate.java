package com.sunshineapp.templatemanager.templates;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sunshineapp.templatemanager.templates.tasks.Task;

import java.util.List;

public abstract class ApplicationTemplate {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationTemplate.class);
    private List<Task> taskList;

    public ApplicationTemplate() {
        taskList = createFlow();
    }

    public abstract List<Task> createFlow();

    public void performTasks() {
        logger.debug("Starting building");
        taskList.forEach(Task::perform);
        logger.debug("Finishing building");
    }
}
