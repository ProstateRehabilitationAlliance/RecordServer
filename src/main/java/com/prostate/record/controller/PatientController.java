package com.prostate.record.controller;

import com.prostate.record.cache.redis.RedisSerive;
import com.prostate.record.entity.Doctor;
import com.prostate.record.entity.Patient;
import com.prostate.record.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "patient")
public class PatientController extends BaseController {


    @Autowired
    private PatientService patientService;

    @Autowired
    private RedisSerive redisSerive;

    @PostMapping(value = "addPatient")
    public Map addPatient(Patient patient, String token){

        log.info(patient.getPatientName());
        resultMap = new LinkedHashMap<>();
        if(patient.getPatientName()==null||"".equals(patient.getPatientName())){
            resultMap.put("code","20001");
            resultMap.put("msg","参数不能为空");
            resultMap.put("result",null);
            return resultMap;
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patient.setCreateDoctor(doctor.getId());
        patientService.insertSelective(patient);
        resultMap.put("code","20000");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",patient);
        return resultMap;
    }

}
