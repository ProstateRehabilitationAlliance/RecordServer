package com.prostate.record.service.impl;

import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Patient;
import com.prostate.record.mapper.PatientMapper;
import com.prostate.record.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public int insertSelective(Patient patient) {
        return patientMapper.insertSelective(patient);
    }

    @Override
    public int updateSelective(Patient patient) {
        return 0;
    }

    @Override
    public Patient selectById(String id) {
        return patientMapper.selectById(id);
    }

    @Override
    public List<Patient> selectByParams(Patient patient) {
        return patientMapper.selectByParams(patient);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public List<PatientBean> selectByParamss(PatientBean patientBean) {
        return patientMapper.selectByParamss(patientBean);
    }

    @Override
    public Patient selectByIdCard(String idCard) {
        return patientMapper.selectByIdCard(idCard);
    }

    @Override
    public PatientBean selectPatientDetailById(String id) {
        return patientMapper.selectPatientDetailById(id);
    }

    @Override
    public String selectCountByParams(PatientBean patientBean) {
        return patientMapper.selectCountByParams(patientBean);
    }
}
