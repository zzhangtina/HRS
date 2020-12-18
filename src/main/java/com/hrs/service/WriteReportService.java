package com.hrs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WriteReportService {
    private static final Logger log = LogManager.getLogger(WriteReportService.class.getName());
    public static List<Report> writeReport(Map<String, List<SubType>> rules, Readings[] readings)throws Exception{
        List<Report> list = new ArrayList<>();
        Arrays.stream(readings).forEach(e->{
            int id = e.getId();
            Reading[] reading = e.getReading();
            Report report = new Report();
            report.setId(id);
            Map<String,List<SubType>> listMap = new HashMap<>();
            if(reading!=null) {
                Arrays.stream(reading).forEach(i -> {
                    for (Map.Entry<String, List<SubType>> rule : rules.entrySet()) {
                        log.info("Key : " + rule.getKey() + " Value : " + rule.getValue());
                        List<SubType> subTypeList = new ArrayList<>();
                        if (rule.getKey().equalsIgnoreCase(i.getType())) {
                            log.info(rule.getKey());
                            for (SubType subtype : rule.getValue()) {
                                log.info(subtype.toString());
                                for (Map.Entry<String, Integer> map : i.getMap().entrySet()) {
                                    log.info("Key : " + map.getKey() + " Value : " + map.getValue());
                                    if (subtype.getType().equalsIgnoreCase(map.getKey())) {
                                        SubType sType = new SubType();
                                        if (subtype.getCondition().equalsIgnoreCase(">")) {
                                            if (map.getValue() > subtype.getValue()) {
                                                sType.setSubType(subtype.getType());
                                                sType.setCondition(subtype.getCondition() + subtype.getValue());
                                                sType.setValue(map.getValue());
                                            } else {
                                                sType.setSubType(subtype.getType());
                                                sType.setCondition("not triggered");
                                                sType.setValue(map.getValue());
                                            }
                                            subTypeList.add(sType);
                                            break;
                                        } else if (subtype.getCondition().equalsIgnoreCase(">=")) {
                                            if (map.getValue() >= subtype.getValue()) {
                                                sType.setSubType(subtype.getType());
                                                sType.setCondition(subtype.getCondition() + subtype.getValue());
                                                sType.setValue(map.getValue());
                                            } else {
                                                sType.setSubType(subtype.getType());
                                                sType.setCondition("not triggered");
                                                sType.setValue(map.getValue());
                                            }
                                            subTypeList.add(sType);
                                            break;
                                        } else if (subtype.getCondition().equalsIgnoreCase("<")) {
                                            if (map.getValue() < subtype.getValue()) {
                                                sType.setSubType(subtype.getType());
                                                sType.setCondition("not triggered");
                                                sType.setValue(map.getValue());
                                            } else {
                                                sType.setSubType(subtype.getType());
                                                sType.setCondition(subtype.getCondition() + subtype.getValue());
                                                sType.setValue(map.getValue());
                                            }
                                            subTypeList.add(sType);
                                            break;

                                        }
                                    }
                                }
                                listMap.put(rule.getKey(), subTypeList);
                            }
                        }
                    }

                });
            }
        report.setListMap(listMap);
        list.add(report);
        });

        return list;
        }

    public static String generateJSONReport(List<Report> list){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try{
            mapper.writeValue(new File("src/main/resources/result.json"), list);
            jsonString = mapper.writeValueAsString(list);
            log.info("Data Sync Success");
            RestService.sentRseult(jsonString);
        }catch (IOException e){
            log.info(e.getMessage());
        }
       return jsonString;
    }

}

