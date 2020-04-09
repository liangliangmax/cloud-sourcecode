package com.liang.text;

import java.lang.reflect.Field;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws Exception {
        BB bb = new BB();

        System.out.println(ReflectionUtils.hasField(bb,"name"));
        System.out.println(ReflectionUtils.hasField(bb,"clazz"));

        if(ReflectionUtils.hasField(bb,"name")){
            ReflectionUtils.setFieldValue(bb,"name","aaaaaa");
        }

        System.out.println(bb.getName());

        if(ReflectionUtils.hasField(bb,"schoolDate")){
            ReflectionUtils.setFieldValue(bb,"schoolDate",new Date());
        }

        System.out.println(bb.getSchoolDate());

    }
}
