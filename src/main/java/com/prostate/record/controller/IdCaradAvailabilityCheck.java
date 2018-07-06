package com.prostate.record.controller;

import com.prostate.record.entity.Patient;
import com.prostate.record.service.PatientService;
import com.prostate.record.util.IdCardVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
    @RequestMapping(value = "idCard")
public class IdCaradAvailabilityCheck extends BaseController {

    @Autowired
    private PatientService patientService;


    /**
     * 身份证校验接口
     *
     * @param idCard
     * @return
     */
    @PostMapping
    @RequestMapping(value = "check")
    public Map check(String idCard) {
        resultMap = new LinkedHashMap<>();

        if ("".equals(idCard) || idCard == null) {
            return emptyParamResponse();
        }
        try {
            String tipInfo = IdCardVerification.IDCardValidate(idCard);
            if (IdCardVerification.VALIDITY.equals(tipInfo)) {
                Patient patient = patientService.selectByIdCard(idCard);
                if (patient == null) {
                    resultMap.put("code", "20000");
                    resultMap.put("msg", tipInfo);
                    resultMap.put("result", null);
                    return resultMap;
                }
                resultMap.put("code", "20003");
                resultMap.put("msg", "PATIENT_ALREADY_EXISTS");
                resultMap.put("result", null);
                return resultMap;
            }
            resultMap.put("code", "20002");
            resultMap.put("msg", tipInfo);
            resultMap.put("result", null);
            return resultMap;
        } catch (ParseException e) {
            e.printStackTrace();
            resultMap.put("code", "50001");
            resultMap.put("msg", "身份证校验异常");
            resultMap.put("result", null);
            return resultMap;
        }
    }
}
