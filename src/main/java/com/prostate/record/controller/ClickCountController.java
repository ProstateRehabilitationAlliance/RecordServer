package com.prostate.record.controller;

import com.prostate.record.entity.ClickCountDoctor;
import com.prostate.record.service.ClickCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "clickCount")
public class ClickCountController extends BaseController {
    @Autowired
    private ClickCountDoctorService clickCountDoctorService;

    /**
     * 医生 查看医生主页触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addDoctorClick")
    public Map addDoctorClick(String userId) {
        ClickCountDoctor clickCountDoctor = new ClickCountDoctor();

        clickCountDoctor.setDoctorId(userId);
        clickCountDoctor.setClickStatus("DOCTOR_CLICK");
        clickCountDoctorService.insertSelective(clickCountDoctor);

        return insertSuccseeResponse();
    }

    /**
     * 患者 查看医生主页触发
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "addPatientClick")
    public Map addPatientClick(String userId) {
        ClickCountDoctor clickCountDoctor = new ClickCountDoctor();

        clickCountDoctor.setDoctorId(userId);
        clickCountDoctor.setClickStatus("PATIENT_CLICK");
        clickCountDoctorService.insertSelective(clickCountDoctor);

        return insertSuccseeResponse();
    }

}
