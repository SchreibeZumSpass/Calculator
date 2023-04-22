package dev.bakhytzhan.calculator;

import dev.bakhytzhan.calculator.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        App app = applicationContext.getBean(App.class);

        app.start();
    }

}
