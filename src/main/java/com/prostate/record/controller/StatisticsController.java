package com.prostate.record.controller;

import com.prostate.record.service.ClickCountDoctorService;
import com.prostate.record.service.FocusCountDoctorService;
import com.prostate.record.service.InquiryCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "statistics")
public class StatisticsController extends BaseController {


    @Autowired
    private ClickCountDoctorService clickCountDoctorService;

    @Autowired
    private FocusCountDoctorService focusCountDoctorService;

    @Autowired
    private InquiryCountDoctorService inquiryCountDoctorService;

    @GetMapping(value = "getDoctorCount")
    public Map getDoctorCount(String doctorId) {

        int clickCount = clickCountDoctorService.getClickCount(doctorId);

        int focusCount = focusCountDoctorService.getFocusCount(doctorId);

        int inquiryCount = inquiryCountDoctorService.getInquiryCount(doctorId);


        Map<String, String> countMap = new HashMap<>();
        //数据校验
        clickCount = clickCount >= 0 ? clickCount : 0;
        focusCount = focusCount >= 0 ? focusCount : 0;
        inquiryCount = inquiryCount >= 0 ? inquiryCount : 0;

        //返回对象赋值
        countMap.put("clickCount", String.valueOf(clickCount));
        countMap.put("focusCount", String.valueOf(focusCount));
        countMap.put("inquiryCount", String.valueOf(inquiryCount));

        return querySuccessResponse(countMap);
    }


}
