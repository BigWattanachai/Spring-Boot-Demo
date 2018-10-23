package com.ascend.springbootdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}

@Component
class InitCLR implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("CommandLineRunner running in the Application class...");
    }
}
