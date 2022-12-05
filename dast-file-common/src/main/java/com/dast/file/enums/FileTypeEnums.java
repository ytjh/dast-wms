package com.dast.file.enums;

/**
 * 文件类型枚举
 */
public enum  FileTypeEnums {

    FILE_EXCLE("0", "excel"),
    FILE_DOC("1","word文档"),
    FILE_PDF("2","PDF"),
    FILE_AVI("3","AVI视频"),
    FILE_JPG("5","图片"),
    FILE_MP4("4","MP4");

    public final String code;
    public final String msg;
    FileTypeEnums(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static FileTypeEnums getEnum(String code) {
        FileTypeEnums[] delEnums = values();
        for (FileTypeEnums typeEnums : delEnums) {
            if (typeEnums.code == code) {
                return typeEnums;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
