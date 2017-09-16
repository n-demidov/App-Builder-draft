package com.sunshineapp.templatemanager.templates.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CLITask implements Task {

    private static final Logger logger = LoggerFactory.getLogger(CLITask.class);
    private String[] command;

    public CLITask(String... command) {
        this.command = command;
    }

    @Override
    public void perform() {
        logger.debug(String.format("Execute command = %s", Arrays.toString(command)));
        executeCommand();
        logger.debug("Command was executing successful");
    }

    private void executeCommand() {
        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                logger.debug(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
