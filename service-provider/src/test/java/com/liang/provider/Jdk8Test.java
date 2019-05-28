package com.liang.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Jdk8Test {


    public static void main(String[] args) {

        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("aaa","fff");


    }
}
