package com.hrs.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;


import java.util.HashMap;
import java.util.Map;

public class Reading {
    private String type;
    private Map<String,Integer> map = new HashMap<>();

    public String getType() {
        return type;
    }

    @JsonAnySetter
    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Integer> getMap() {
        return map;
    }
    @JsonAnySetter
    public void setMap(String type,int value) {
        this.map.put(type,value);
    }


}
