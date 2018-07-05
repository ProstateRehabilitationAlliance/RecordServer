package com.prostate.record.service.impl;

import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.Patient;
import com.prostate.record.feignService.StaticServer;
import com.prostate.record.mapper.pomr.read.AnamnesisReadMapper;
import com.prostate.record.mapper.pomr.read.PatientReadMapper;
import com.prostate.record.service.PatientAnamnesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientAnamnesisServiceImpl implements PatientAnamnesisService {

    @Autowired
    private StaticServer staticServer;

    @Autowired
    private PatientReadMapper patientReadMapper;

    @Autowired
    private AnamnesisReadMapper anamnesisReadMapper;


    @Cacheable(value = "HealthRrecord", key = "#patientId",unless="#result == null")
    @Override
    public PatientBean getHealthRrecord(String patientId) {

        //查询患者信息
        Patient patient = patientReadMapper.selectById(patientId);
        while (patient == null) {
            return null;
        }
        PatientBean patientBean = new PatientBean();
        patient.setDetailAddress(staticServer.getCityDetail(patient.getCityId()).get("result").toString() + patient.getDetailAddress());
        patientBean.setPatient(patient);

        patientBean.setBloodGroup(staticServer.getBloodGroupById(patient.getBloodGroupId()).get("result").toString());
        patientBean.setEducationName(staticServer.getEducationById(patient.getEducationId()).get("result").toString());
        patientBean.setNationName(staticServer.getNationById(patient.getNationId()).get("result").toString());
        patientBean.setProfessionName(staticServer.getProfessionById(patient.getProfessionId()).get("result").toString());

        //查询病历信息
        List<Anamnesis> anamnesisList = anamnesisReadMapper.getByPatientId(patientId);
        while (anamnesisList == null) {
            return patientBean;
        }
        List<String> orderStringList = new ArrayList<>();
        //查询 病历中 疾病 信息
        for (Anamnesis anamnesis : anamnesisList) {
            switch (anamnesis.getAnamnesisTypeId()) {
                case "0007fe67fa7c4c4195018ebe7926a7c7":
                    orderStringList.add(staticServer.getAnamnesisAllergyDrugById(anamnesis.getOrderId()).get("result").toString());
                    break;
                case "00163e4597b14fe787c86e22b7946790":
                    orderStringList.add(staticServer.getAnamnesisEatingDrugById(anamnesis.getOrderId()).get("result").toString());
                    break;
                case "00106a226f04411b885e3f328acba4d7":
                    orderStringList.add(staticServer.getAnamnesisIllnessById(anamnesis.getOrderId()).get("result").toString());
                    break;
                case "0007fe67fa7c4c4195018ede7926a7c7":
                    orderStringList.add(staticServer.getSurgicalHistoryById(anamnesis.getOrderId()).get("result").toString());
                    break;
                case "0045a520eb9d4a3f93fbef4a2e9de0cf":
                    orderStringList.add(anamnesis.getAnamnesisRemark());
                    break;
                default:
                    break;
            }
        }
        patientBean.setOrderStringList(orderStringList);
        return patientBean;
    }
}
