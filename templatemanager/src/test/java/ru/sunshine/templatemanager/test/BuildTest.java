package ru.sunshine.templatemanager.test;


import com.sunshineapp.templatemanager.templates.tasks.CLITask;
import com.sunshineapp.templatemanager.templates.tasks.Task;
import org.junit.AfterClass;
import org.junit.Test;

public class BuildTest {

    @Test
    public void testBuildApp() {
        Task cliTask = new CLITask("cmd.exe", "/c", "cd sandbox && git clone https://bitbucket.org/app-sunshine/base-android-template");
        cliTask.perform();
        Task build = new CLITask("cmd.exe", "/c", "cd sandbox/base-android-template && gradlew.bat assembleDebug");
        build.perform();
        Task clean = new CLITask("cmd.exe", "/c", "cd sandbox && rmdir base-android-template /s /q");
        clean.perform();
    }
}
