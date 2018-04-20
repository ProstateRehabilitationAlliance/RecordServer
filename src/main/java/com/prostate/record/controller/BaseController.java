package com.prostate.record.controller;


import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {

    public static Map<String,Object> resultMap;

    public BaseController(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","200");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result","RESULT");
    }

}
