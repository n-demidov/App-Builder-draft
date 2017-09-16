package ru.sunshine.templatemanager.test;

import com.sunshineapp.templatemanager.TemplateManagerWorker;
import com.sunshineapp.templatemanager.templates.AndroidAppImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.sunshineapp.templatemanager.api.TemplateManager;
import com.sunshineapp.templatemanager.api.BuildIntent;
import com.sunshineapp.templatemanager.templates.ApplicationTemplate;
import com.sunshineapp.templatemanager.templates.tasks.Task;
import com.sunshineapp.templatemanager.utils.BeanConfiguration;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class ServicesTest {

    @Inject
    @TemplateManager
    private TemplateManagerWorker builder;

    @Test
    public void testBuilding() {
        AndroidAppImpl template = new AndroidAppImpl();
        template.setIconPath("124556/tmp/data/icon.png");
        BuildIntent<AndroidAppImpl> intent = new BuildIntent<>(template);
        builder.buildApplication(intent);
    }

    @Test
    public void testApplicationTemplate() {
        ApplicationTemplate mockTemplate = mock(ApplicationTemplate.class);
        Task taskMock1 = mock(Task.class);
        Task taskMock2 = mock(Task.class);
        Task taskMock3 = () -> System.out.println("Perform custom action");
        List<Task> taskList = new LinkedList<>();
        taskList.add(taskMock1);
        taskList.add(taskMock2);
        taskList.add(taskMock3);
        when(mockTemplate.createFlow()).thenReturn(taskList);
        assertEquals(3, mockTemplate.createFlow().size());
        mockTemplate.performTasks();
    }

    @Test
    public void testBuilder() {
        ApplicationTemplate applicationTemplate = mock(ApplicationTemplate.class);
        BuildIntent<ApplicationTemplate> intent = new BuildIntent<>(applicationTemplate);
        builder.buildApplication(intent);
    }

}
