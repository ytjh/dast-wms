package com.dast.file.constans;

import com.dast.file.enums.FileTypeEnums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 静态map
 */
public class FileCommonMap {

    public final  static Map<String,String> fileTypeMap = new ConcurrentHashMap();

    static {
        fileTypeMap.put("xlx", "0");
        fileTypeMap.put("xlxs","0");
        fileTypeMap.put("doc", "1");
        fileTypeMap.put("docx", "1");
        fileTypeMap.put("pdf", "2");
        fileTypeMap.put("avi", "3");
        fileTypeMap.put("mp4", "4");
        fileTypeMap.put("jpg", "5");
        fileTypeMap.put("png", "5");
    }
    public static String getFileType(String suffix){
        if (fileTypeMap.containsKey(suffix)){
            String msg = FileTypeEnums.getEnum(fileTypeMap.get(suffix)).getMsg();
            return msg;
        }
        return suffix;
    }
}
