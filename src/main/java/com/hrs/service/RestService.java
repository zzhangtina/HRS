package com.hrs.service;

import com.hrs.ReadingApplication;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestService {
    private static final Logger log = LogManager.getLogger(RestService.class.getName());
    public static void sentRseult(String payload){
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("http://hrs.com");
            StringEntity params = new StringEntity(payload);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
    }
}
