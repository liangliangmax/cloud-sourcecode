package com.liang.provider;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class StringTest {


    public static void main(String[] args) throws NoSuchMethodException {
        String a = "\n";

        System.out.println(StringUtils.isEmpty(a));

        System.out.println(a);


        Method method = StringTest.class.getDeclaredMethod("test",String.class);

        Parameter[] parameters = method.getParameters();

        for (Parameter parameter:parameters){
            System.out.println(parameter);
        }
    }

    public void test(String aaa){

        System.out.println(aaa);
    }
}
