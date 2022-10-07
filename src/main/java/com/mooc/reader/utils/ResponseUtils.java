package com.mooc.reader.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtils {
    private String code;
    private String msg;
    private Map data = null;

    public ResponseUtils(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseUtils() {
        this.code = "0";
        this.msg = "success";
    }

    public ResponseUtils put(String key, Object value) {
        if (data == null) {
            data = new LinkedHashMap();
        }
        data.put(key, value);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public static ResponseUtils error(Exception e) {
        return new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
    }
}
