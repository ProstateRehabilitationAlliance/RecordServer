package com.prostate.record.service;

import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService extends BaseService<Patient> {

    List<PatientBean> selectByParamss(PatientBean patientBean);
}
