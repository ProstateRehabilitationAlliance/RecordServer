package com.prostate.record.controller;

import com.prostate.record.entity.UserPatient;
import com.prostate.record.service.UserPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "userPatient")
public class UserPatientController extends BaseController {

    @Autowired
    private UserPatientService userPatientService;

    /**
     * 添加医生 患者 关系
     *
     * @param patientId
     * @param userId
     * @param patientSource
     * @return
     */
    @PostMapping(value = "addUserPatient")
    public Map<String, Object> addUserPatient(String patientId, String userId, String patientSource) {
        UserPatient userPatient = new UserPatient();
        userPatient.setUserId(userId);
        userPatient.setPatientId(patientId);

        List<UserPatient> userPatientList = userPatientService.selectByParams(userPatient);

        if (userPatientList.isEmpty()) {
            int i = userPatientService.addUserPatient(patientId, userId, patientSource);
            if (i > 0) {
                return insertSuccseeResponse("添加成功");
            }
            return insertFailedResponse("添加失败");
        }
        return insertalreadyExistedResponse("记录已经存在");
    }
}
