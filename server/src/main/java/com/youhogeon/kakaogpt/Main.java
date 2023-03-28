package com.youhogeon.kakaogpt;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.youhogeon.kakaogpt.server.ServerRunner;

public class Main {

    public static void main(String[] args) {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

        ServerRunner serverRunner = ac.getBean(ServerRunner.class);
        serverRunner.run();

        serverRunner.close();
        ac.close();
    }

}