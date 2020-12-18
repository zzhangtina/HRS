package com.hrs.service;

import com.hrs.enums.FILETYPE;

public class ReadFileServiceFactory {
   public ReadFileService getService(String fileType){
       switch (FILETYPE.fromValue(fileType)) {
           case JSON:
               return new ReadJSONFileService();
           case YAML:
               return new ReadYMALFileService();
           default:
               return null;
       }
   }
}
