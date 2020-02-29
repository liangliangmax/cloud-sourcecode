package com.liang.mongodbtut.test;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        String str = "aaabbcdeaa";

        String[] strings = str.split("");

        LinkedList<String> linkedList = new LinkedList();
        LinkedList<String> linkedList2 = new LinkedList();

        for(String string:strings){
            linkedList.add(string);
        }

        for(String s:linkedList){
            if(linkedList2.size()==0){
                linkedList2.add(s);
            }
            if(!s.equalsIgnoreCase(linkedList2.getLast())){
                linkedList2.add(s);
            }
        }

        linkedList2.stream().forEach(System.out::print);
    }
}
