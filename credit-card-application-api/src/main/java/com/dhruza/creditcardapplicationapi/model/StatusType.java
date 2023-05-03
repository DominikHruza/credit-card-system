package com.dhruza.creditcardapplicationapi.model;

public enum StatusType {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String name;

    StatusType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static String joinValuesToString() {
        StringBuilder sb = new StringBuilder();
        for (var value : values()) {
            sb.append(value.getName()).append(", ");
        }

        return sb.toString();
    }
}
