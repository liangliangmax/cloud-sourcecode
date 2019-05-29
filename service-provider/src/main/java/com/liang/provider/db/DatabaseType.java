package com.liang.provider.db;

public enum  DatabaseType {

    DAM("dam", "dam"),
    NEUABC("neuabc", "neuabc");

    DatabaseType(String name, String value){
        this.name = name;
        this.value = value;
    }

    private String name;
    private String value;

    public String getName(){
        return name;
    }

    public String getValue(){
        return value;
    }
}
