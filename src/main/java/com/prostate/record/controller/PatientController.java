package com.prostate.record.controller;

import com.prostate.record.beans.PatientBean;
import com.prostate.record.cache.redis.RedisSerive;
import com.prostate.record.entity.BaseEntity;
import com.prostate.record.entity.Doctor;
import com.prostate.record.entity.Patient;
import com.prostate.record.entity.WechatUser;
import com.prostate.record.feignService.StaticServer;
import com.prostate.record.service.PatientService;
import com.prostate.record.util.IdCardUtil;
import com.prostate.record.validator.phoneValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者用户 Controller
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "patient")
public class PatientController extends BaseController {


    private PatientService patientService;

    private RedisSerive redisSerive;

    private StaticServer staticServer;

    @Autowired
    public PatientController(PatientService patientService, RedisSerive redisSerive,StaticServer staticServer) {
        this.patientService = patientService;
        this.redisSerive = redisSerive;
        this.staticServer = staticServer;
    }

    /**
     * 添加患者 信息
     * @param patient
     * @param token
     * @return
     */
    @PostMapping(value = "addPatient")
    public Object addPatient(@Valid Patient patient, String token) {
        resultMap = new LinkedHashMap<>();
        if (patient.getPatientName() == null || "".equals(patient.getPatientName())) {

            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patient.setCreateDoctor(doctor.getId());
        patient.setPatientNumber("PRA" + System.currentTimeMillis());
        patient.setPatientAge(IdCardUtil.getAgeByIdCard(patient.getPatientCard()));
        if (patient.getId() == null || "".equals(patient.getId()) || patient.getId().length() < 32) {
            int i = patientService.insertSelective(patient);
            if (i >= 0) {
                return insertSuccseeResponse();
            }
            return insertFailedResponse();
        }
        int i = patientService.updateSelective(patient);
        if (i >= 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();

    }


    /**
     * 根据ID查询患者基本信息
     *
     * @param patientId
     * @param
     * @return
     */
    @PostMapping(value = "getPatientDetailById")
    public Map getPatientDetailById(String patientId) {

        if (patientId == null || "".equals(patientId)) {
            return emptyParamResponse();
        }
        PatientBean patientBean = patientService.selectPatientDetailById(patientId);
        if (patientBean != null) {
            return querySuccessResponse(patientBean);
        }
        return queryEmptyResponse();

    }

    /**
     * 查询患者列表
     *
     * @param token
     * @param pageNo
     * @return
     */
    @PostMapping(value = "getPatientList")
    public Map getPatientList(String token, String pageNo, String patientName, String number) {
        log.info("#########医生端查询患者列表########");
        //参数校验
        if (pageNo == null || "".equals(pageNo)) {
            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);
        //创建查询条件
        PatientBean patientBean = new PatientBean();
        patientBean.setCreateDoctor(doctor.getId());
        patientBean.setPageNo(Integer.parseInt(pageNo) * BaseEntity.PAGE_SIZE);
        patientBean.setPageSize((Integer.parseInt(pageNo) + 1) * BaseEntity.PAGE_SIZE);
        if (patientName != null && !"".equals(patientName)) {
            patientBean.setPatientName(patientName);
        }
        if (number != null && !"".equals(number)) {
            if (phoneValidation.regexCheck(number)) {
                patientBean.setPatientPhone(number);
            } else {
                patientBean.setPatientNumber(number);
            }
        }

        //查询数据
        String count = patientService.selectCountByParams(patientBean);
        List<PatientBean> patientBeanList = patientService.selectByParamss(patientBean);
        //查询结果不为空时请求响应
        if (patientBeanList != null && patientBeanList.size() > 0) {
            return querySuccessResponse(patientBeanList, count);
        }
        //查询结果为空时请求响应
        return queryEmptyResponse();
    }

/*****************************************微信端 接口 **************************************************/
    /**
     * 根据微信token查询患者基本信息
     *
     * @param
     * @param
     * @return
     */
    @PostMapping(value = "getPatientDetailByToken")
    public Map getPatientDetailByToken(String token) {
        WechatUser wechatUser = redisSerive.getWechatUser(token);
        PatientBean patientBean = patientService.selectPatientDetailById(wechatUser.getId());
        if (patientBean != null) {
            return querySuccessResponse(patientBean);
        }
        return queryEmptyResponse();
    }


    /**
     * 修改患者信息
     * @param patient
     * @param token
     * @return
     */
    @PostMapping(value = "update")
    public Object updatePatient(Patient patient, String token) {
        resultMap = new LinkedHashMap<>();
        if (patient.getId() == null || "".equals(patient.getId())) {
            return emptyParamResponse();
        }
        int i = patientService.updateSelective(patient);
        if (i >= 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();

    }
}































