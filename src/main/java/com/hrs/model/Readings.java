package com.hrs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Readings {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reading[] getReading() {
        return reading;
    }
    @JsonProperty("readings")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setReading(Reading[] reading) {
        if(reading != null){
        this.reading = reading;
        }else{
            this.reading = new Reading[]{};
        }
    }



    private int id;
    private Reading[] reading;

}
