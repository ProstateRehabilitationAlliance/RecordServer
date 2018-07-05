package com.prostate.record.service.impl;

import com.prostate.record.beans.PatientAnamnesisBean;
import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Patient;
import com.prostate.record.mapper.pomr.read.PatientReadMapper;
import com.prostate.record.mapper.pomr.write.PatientWriteMapper;
import com.prostate.record.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientWriteMapper patientWriteMapper;

    @Autowired
    private PatientReadMapper patientReadMapper;

    @Override
    public int insertSelective(Patient patient) {
        return patientWriteMapper.insertSelective(patient);
    }

    @CacheEvict(value = "HealthRrecord", key = "'health_record_'+#patient.id", condition = "true", beforeInvocation = true)
    @Override
    public int updateSelective(Patient patient) {
        return patientWriteMapper.updateSelective(patient);
    }

    @Override
    public Patient selectById(String id) {
        return patientReadMapper.selectById(id);
    }

    @Override
    public List<Patient> selectByParams(Patient patient) {
        return patientReadMapper.selectByParams(patient);
    }

    @CacheEvict(value = "HealthRrecord", key = "'health_record_'+#anamnesis.patientId", condition = "true", beforeInvocation = true)
    @Override
    public int deleteById(String id) {
        return patientWriteMapper.deleteById(id);
    }

    @Override
    public List<PatientBean> selectByParamss(PatientBean patientBean) {
        return patientReadMapper.selectByParamss(patientBean);
    }

    @Override
    public Patient selectByIdCard(String idCard) {
        return patientReadMapper.selectByIdCard(idCard);
    }

    @Override
    public PatientBean selectPatientDetailById(String id) {
        return patientReadMapper.selectPatientDetailById(id);
    }

    @Override
    public String selectCountByParams(PatientBean patientBean) {
        return patientReadMapper.selectCountByParams(patientBean);
    }

    @Override
    public PatientAnamnesisBean selectPatientInfoById(String id) {
        return patientReadMapper.selectPatientInfoById(id);
    }
}
