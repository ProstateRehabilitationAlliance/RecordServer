package com.prostate.record.controller;

import com.prostate.record.feignService.DoctorServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class TestController extends BaseController{

    @Autowired
    private DoctorServer doctorServer;

    @RequestMapping(value = "testGlobalException")
    public Map<String,Object> testGlobalException(){
        log.info(">>>>>>>>>>>>>>>GlobalException TEST");
        int i = 1/0;
        resultMap.put("error_code","000");
        resultMap.put("error_msg","SUCCESS");
        return resultMap;
    }

    @RequestMapping(value = "test")
    public Map<String,Object> testLog(){
        log.info(">>>>>>>>>>>>>>>LOG TEST");
        resultMap.put("error_code","000");
        resultMap.put("error_msg","SUCCESS");
        return resultMap;
    }

    @RequestMapping(value = "testFeign")
    public Map<String,Object> testFeign(){
        log.info(">>>>>>>>>>>>>>>Feign TEST");
        return doctorServer.getone();
    }

    @RequestMapping(value = "testConfig")
    public Map<String,Object> testConfig(){
        log.info(">>>>>>>>>>>>>>>Config TEST");
        resultMap.put("error_code","000");
        resultMap.put("error_msg","SUCCESS");
        return resultMap;
    }
}
