package com.prostate.record.controller;

import com.prostate.record.beans.PatientBean;
import com.prostate.record.cache.redis.RedisSerive;
import com.prostate.record.entity.BaseEntity;
import com.prostate.record.entity.Doctor;
import com.prostate.record.entity.Patient;
import com.prostate.record.service.PatientService;
import com.prostate.record.utlis.IdCardUtil;
import com.prostate.record.validator.phoneValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
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

        resultMap = new LinkedHashMap<>();
        if(patient.getPatientName()==null||"".equals(patient.getPatientName())){
            resultMap.put("code","20001");
            resultMap.put("msg","参数不能为空");
            resultMap.put("result",null);
            return resultMap;
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patient.setCreateDoctor(doctor.getId());
        patient.setPatientNumber("PRA"+ System.currentTimeMillis());
        patient.setPatientAge(IdCardUtil.getAgeByIdCard(patient.getPatientCard()));
        patientService.insertSelective(patient);
        resultMap.put("code","20000");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",patient);
        return resultMap;
    }

    /**
     * 查询患者列表
     * @param token
     * @param pageNo
     * @return
     */
    @PostMapping(value = "getPatientList")
    public Map getPatientList(String token,String pageNo,String patientName,String number){
        log.info("#########医生端查询患者列表########");
        resultMap = new LinkedHashMap<>();
        //参数校验
        if (pageNo==null||"".equals(pageNo)){
            resultMap.put("code","20001");
            resultMap.put("msg","PARAM_EMPTY");
            resultMap.put("result",null);
            return resultMap;
        }
        Doctor doctor = redisSerive.getDoctor(token);
        //创建查询条件
        PatientBean patientBean = new PatientBean();
        patientBean.setCreateDoctor(doctor.getId());
        patientBean.setPageNo(Integer.parseInt(pageNo)* BaseEntity.PAGE_SIZE);
        patientBean.setPageSize((Integer.parseInt(pageNo)+1)*BaseEntity.PAGE_SIZE);
        if(patientName!=null&&!"".equals(patientName)){
            patientBean.setPatientName(patientName);
        }
        if(number!=null&&!"".equals(number)){
            if(phoneValidation.regexCheck(number)){
                patientBean.setPatientPhone(number);
            }else {
                patientBean.setPatientNumber(number);
            }
        }

        //查询数据
        List<PatientBean> patientBeanList = patientService.selectByParamss(patientBean);

        //查询结果不为空时请求响应
        if (patientBeanList!=null&&patientBeanList.size()>0){
            resultMap.put("code","20000");
            resultMap.put("msg","SUCCESS");
            resultMap.put("result",patientBeanList);
            return resultMap;
        }
        //查询结果为空时请求响应
        resultMap.put("code","20002");
        resultMap.put("msg","RESULT_EMPTY");
        resultMap.put("result",null);
        return resultMap;
    }
}































