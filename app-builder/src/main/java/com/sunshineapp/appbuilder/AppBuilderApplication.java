package com.sunshineapp.appbuilder;

import com.sunshineapp.appbuilder.controllers.MomController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AppBuilderApplication {

    private static final String SPRING_CONTEXT_XML = "classpath:spring/spring-context-appbuilder.xml";

    public static void main(String[] argv) throws IOException, InterruptedException, TimeoutException {
        final ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {SPRING_CONTEXT_XML});
        final MomController momController = context.getBean(MomController.class);

        momController.init();
    }

}
