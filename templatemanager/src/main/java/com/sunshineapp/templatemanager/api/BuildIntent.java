package com.sunshineapp.templatemanager.api;

import com.sunshineapp.templatemanager.templates.ApplicationTemplate;

public class BuildIntent<T extends ApplicationTemplate> {

    private T template;

    public BuildIntent(T template) {
        this.template = template;
    }

    public void build() {
        template.performTasks();
    }

}
