package com.prostate.record.mapper.pomr.write;

import com.prostate.record.beans.PatientAnamnesisBean;
import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Patient;
import com.prostate.record.mapper.BaseWriteMapper;

import java.util.List;


public interface PatientWriteMapper extends BaseWriteMapper<Patient> {


    List<PatientBean> selectByParamss(Patient e);

    Patient selectByIdCard(String idCard);

    PatientBean selectPatientDetailById(String id);

    String selectCountByParams(PatientBean patientBean);

    PatientAnamnesisBean selectPatientInfoById(String id);
}