package com.hrs.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.model.Readings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ReadJSONFileService implements ReadFileService{
    private static final Logger log = LogManager.getLogger(ReadJSONFileService.class.getName());
    public Readings[] readFile(String name) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Readings[] readings = objectMapper.readValue(new File(name), Readings[].class);
        return readings;
    }

}
