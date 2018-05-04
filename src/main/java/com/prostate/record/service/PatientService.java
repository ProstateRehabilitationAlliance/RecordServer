package com.prostate.record.service;

import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService extends BaseService<Patient> {

    List<PatientBean> selectByParamss(PatientBean patientBean);

    /**
     * 根据身份证查询患者
     * 校验身份证号是否可用
     * @param idCard
     * @return
     */
    Patient selectByIdCard(String idCard);

    PatientBean selectPatientDetailById(String id);

    String selectCountByParams(PatientBean patientBean);
}
