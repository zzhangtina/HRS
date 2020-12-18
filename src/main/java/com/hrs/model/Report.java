package com.hrs.model;

import java.util.List;
import java.util.Map;

public class Report {
    private int id;

    Map<String, List<SubType>> listMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, List<SubType>> getListMap() {
        return listMap;
    }

    public void setListMap(Map<String, List<SubType>> listMap) {
        this.listMap = listMap;
    }
}
