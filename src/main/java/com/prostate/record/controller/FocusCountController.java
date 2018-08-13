package com.prostate.record.controller;


import com.prostate.record.entity.FocusCountDoctor;
import com.prostate.record.service.FocusCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "focusCount")
public class FocusCountController extends BaseController {

    @Autowired
    private FocusCountDoctorService focusCountDoctorService;

    /**
     * 医生 关注医生 触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addDoctorFocus")
    public Map addDoctorFocus(String userId) {
        FocusCountDoctor focusCountDoctor = new FocusCountDoctor();

        focusCountDoctor.setDoctorId(userId);
        focusCountDoctor.setFocusStatus("DOCTOR_FOCUS");
        focusCountDoctorService.insertSelective(focusCountDoctor);

        return insertSuccseeResponse();
    }

    /**
     * 患者 关注医生 触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addPatientFocus")
    public Map addPatientFocus(String userId) {
        FocusCountDoctor focusCountDoctor = new FocusCountDoctor();

        focusCountDoctor.setDoctorId(userId);
        focusCountDoctor.setFocusStatus("PATIENT_FOCUS");
        focusCountDoctorService.insertSelective(focusCountDoctor);

        return insertSuccseeResponse();
    }

    /**
     * 医生 取消关注医生 触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addDoctorUnFocus")
    public Map removeDoctorFocus(String userId) {

        FocusCountDoctor focusCountDoctor = new FocusCountDoctor();

        focusCountDoctor.setDoctorId(userId);
        focusCountDoctor.setFocusStatus("DOCTOR_UN_FOCUS");
        focusCountDoctorService.insertSelective(focusCountDoctor);

        return insertSuccseeResponse();
    }

    /**
     * 患者 取消关注医生 触发
     *-
     * @param userId
     * @return
     */
    @PostMapping(value = "addPatientUnFocus")
    public Map removePatientFocus(String userId) {
        FocusCountDoctor focusCountDoctor = new FocusCountDoctor();

        focusCountDoctor.setDoctorId(userId);
        focusCountDoctor.setFocusStatus("PATIENT_UN_FOCUS");
        focusCountDoctorService.insertSelective(focusCountDoctor);

        return insertSuccseeResponse();
    }
}
