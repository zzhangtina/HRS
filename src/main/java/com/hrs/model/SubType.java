package com.hrs.model;

import java.util.Optional;

public class SubType {
    private  String subType;
    private  String condition;
    private  Integer value;

    public  SubType(){}

    public SubType(String type, String condition, Integer value){
        this.subType =  type;
        this.condition = condition;
        this.value = value;
    }

    public String getType() {
        return subType;
    }

    public String getCondition() {
        return condition;
    }

    public Integer getValue() {
        return value;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return new StringBuilder().append(subType).append(condition).append(value).toString();
    }
}
