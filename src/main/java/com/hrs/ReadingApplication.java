package com.hrs;

import com.hrs.model.Readings;
import com.hrs.model.Report;
import com.hrs.model.SubType;
import com.hrs.service.ReadFileServiceFactory;
import com.hrs.service.ReadRulesService;
import com.hrs.service.WriteReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ReadingApplication {
    private static final Logger log = LogManager.getLogger(ReadingApplication.class.getName());
    public static void main(String[] args){
        int index = args[0].indexOf(".");
        String service = args[0].substring(index+1);
        try {
            Map<String, List<SubType>> rules = ReadRulesService.readFile();
            ReadFileServiceFactory readFileService = new ReadFileServiceFactory();
            Readings[] readings = readFileService.getService(service).readFile(args[0]);
            List<Report> list = WriteReportService.writeReport(rules,readings);
            String result = WriteReportService.generateJSONReport(list);
            log.info("Result: "+result);
        }catch (Throwable throwable){
            log.info(throwable.getMessage());
        }

    }
}
