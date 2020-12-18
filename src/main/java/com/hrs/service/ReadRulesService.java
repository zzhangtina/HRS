package com.hrs.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.model.Rules;
import com.hrs.model.SubType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReadRulesService {
    private static final Logger log = LogManager.getLogger(ReadRulesService.class.getName());
    public static Map<String,List<SubType>> readFile() throws IOException {
        //File file = new File("C:\\Users\\yxs44\\project\\java-test-master\\project\\target\\rules.json");
        InputStream is = ReadRulesService.class.getClassLoader().getResourceAsStream("rules.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode rootNode = objectMapper.readTree(is);
        Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
        Map<String, List<SubType>> subTypeEntry = new HashMap<>();
        while(fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String   fieldName  = field.getKey();
            JsonNode fieldValue = field.getValue();
            Iterator<Map.Entry<String, JsonNode>> subFileds = fieldValue.fields();
            List<SubType> subTypeList = new ArrayList<>();
            while(subFileds.hasNext()){
                Map.Entry<String,JsonNode> subFiled = subFileds.next();
                SubType subType = new SubType(subFiled.getKey(),subFiled.getValue().get(0).asText(),subFiled.getValue().get(1).intValue());
                subTypeList.add(subType);
                log.info(subFiled.getKey()+subFiled.getValue().get(0).asText()+subFiled.getValue().get(1));
            }
            subTypeEntry.put(fieldName,subTypeList);
            subTypeEntry.forEach((k,v)->{log.info("key: "+k+", value:"+v);});
        }
        log.info(rootNode.toString());
        return subTypeEntry;
    }
}
