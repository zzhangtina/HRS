package com.hrs.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Rules {
    private final Map<String, List<SubType>> rules;

    public Rules(Map<String,List<SubType>> listMap){
        this.rules = listMap;
    }

    public Map<String, List<SubType>> getRules() {
        return rules;
    }
}
