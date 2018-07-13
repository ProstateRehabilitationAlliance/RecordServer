package com.prostate.record.controller;

import com.prostate.record.cache.redis.RedisSerive;
import com.prostate.record.entity.Doctor;
import com.prostate.record.entity.DoctorPatient;
import com.prostate.record.entity.Patient;
import com.prostate.record.service.DoctorPatientService;
import com.prostate.record.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 医患关系 Controller
 */
@Slf4j
@RestController
@RequestMapping(value = "doctorPatient")
public class DoctorPatientController extends BaseController {
    @Autowired
    private DoctorPatientService doctorPatientService;
    @Autowired
    private RedisSerive redisSerive;

    @Autowired
    private PatientService patientService;
    /**
     * 添加医患关系
     *
     * @param cacheId
     * @param token
     * @return
     */
    @PostMapping(value = "add")
    public Map add(String cacheId, String token) {
        //参数校验
        if (cacheId == null || cacheId.length() != 6) {
            return emptyParamResponse();
        }
        String patientId = redisSerive.get(cacheId);
        if (null == patientId || "".equals(patientId)) {
            return requestFailedResponse("二维码已过期!");
        }
        //获取当前登陆的医生
        Doctor doctor = redisSerive.getDoctor(token);

//        DoctorPatient doctorPatient = new DoctorPatient();
//        doctorPatient.setPatientId(patientId);
//        doctorPatient.setDoctorId(doctor.getId());

        Patient patient =new Patient();
        patient.setId(patientId);
        patient.setCreateDoctor(doctor.getId());
        int i = patientService.updateSelective(patient);

        //添加结果校验
        if (i >= 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }
}
