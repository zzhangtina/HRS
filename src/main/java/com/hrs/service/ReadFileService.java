package com.hrs.service;

import com.hrs.model.Readings;

public interface ReadFileService {
    //ReadingData[] readFile(byte[] data)throws Throwable;
    Readings[] readFile(String data)throws Throwable;

}
