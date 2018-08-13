package com.prostate.record.controller;

import com.prostate.record.entity.InquiryCountDoctor;
import com.prostate.record.service.InquiryCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "inquiryCount")
public class InquiryCountController extends BaseController {

    @Autowired
    private InquiryCountDoctorService inquiryCountDoctorService;


    /**
     * 问诊 转诊 完成 触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addOrderSuccess")
    public Map addOrderSuccess(String userId) {
        InquiryCountDoctor inquiryCountDoctor = new InquiryCountDoctor();

        inquiryCountDoctor.setDoctorId(userId);
        inquiryCountDoctor.setInquiryStatus("ORDER_SUCCESS");
        inquiryCountDoctorService.insertSelective(inquiryCountDoctor);

        return insertSuccseeResponse();
    }
}
