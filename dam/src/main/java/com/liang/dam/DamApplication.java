package com.liang.dam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DamApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DamApplication.class, args);


        String aaa = "///a//aaaa";

        System.out.println(aaa.endsWith("///"));

        System.out.println(aaa.replaceAll("/.[/]*$","/"));

        Runnable run = () -> System.out.println("1111");


        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        };

        //new Thread(run).start();


    }

}
