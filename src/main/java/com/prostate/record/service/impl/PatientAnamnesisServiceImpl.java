package com.prostate.record.service.impl;

import com.prostate.record.beans.PatientAnamnesisBean;
import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.Patient;
import com.prostate.record.feignService.StaticServer;
import com.prostate.record.mapper.pomr.read.AnamnesisReadMapper;
import com.prostate.record.mapper.pomr.read.PatientReadMapper;
import com.prostate.record.service.PatientAnamnesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PatientAnamnesisServiceImpl implements PatientAnamnesisService {

    @Autowired
    private StaticServer staticServer;

    @Autowired
    private PatientReadMapper patientReadMapper;

    @Autowired
    private AnamnesisReadMapper anamnesisReadMapper;


    /**
     * @param patientId
     * @return
     */
    @Cacheable(value = "HealthRrecord", key = "#patientId", unless = "#result == null")
    @Override
    public PatientAnamnesisBean getHealthRrecord(String patientId) {

        //查询患者信息
        Patient patient = patientReadMapper.selectById(patientId);
        while (patient == null) {
            return null;
        }
        PatientAnamnesisBean patientAnamnesisBean = new PatientAnamnesisBean();
        patientAnamnesisBean.setPatient(patient);

        //查询城市信息
        String cityId = patient.getCityId();
        if (cityId != null && cityId.length() == 32) {
            LinkedHashMap cityDetailBean = (LinkedHashMap) staticServer.getCityDetail(patient.getCityId()).get("result");
            patientAnamnesisBean.setCityDetailBean(cityDetailBean);
        }
        //查询血型信息
        String bloodGroupId = patient.getBloodGroupId();
        if (bloodGroupId != null && bloodGroupId.length() == 32) {
            patientAnamnesisBean.setBloodGroup(staticServer.getBloodGroupById(bloodGroupId).get("result").toString());
        }

        //查询文化程度
        String educationId = patient.getEducationId();
        if (educationId != null && educationId.length() == 32) {
            patientAnamnesisBean.setEducationName(staticServer.getEducationById(educationId).get("result").toString());
        }
        //查询民主信息
        String nationId = patient.getNationId();
        if (nationId != null && nationId.length() == 32) {
            patientAnamnesisBean.setNationName(staticServer.getNationById(nationId).get("result").toString());
        }
        //查询职业信息
        String professionId = patient.getProfessionId();
        if (professionId != null && professionId.length() == 32) {
            patientAnamnesisBean.setProfessionName(staticServer.getProfessionById(professionId).get("result").toString());
        }

        //查询病历信息
        List<Anamnesis> anamnesisList = anamnesisReadMapper.getByPatientId(patientId);
        while (anamnesisList == null) {
            return patientAnamnesisBean;
        }

        List<Anamnesis> anamnesisAllergyDrugList = new ArrayList<>();
        List<Anamnesis> anamnesisEatingDrugList = new ArrayList<>();
        List<Anamnesis> anamnesisIllnessList = new ArrayList<>();
        List<Anamnesis> anamnesisSurgicalHistoryList = new ArrayList<>();
        List<Anamnesis> otherList = new ArrayList<>();
        //查询 病历中 疾病 信息
        for (Anamnesis anamnesis : anamnesisList) {
            switch (anamnesis.getAnamnesisTypeId()) {
                case "0007fe67fa7c4c4195018ebe7926a7c7":
                    anamnesis.setAnamnesisRemark(staticServer.getAnamnesisAllergyDrugById(anamnesis.getOrderId()).get("result").toString());
                    anamnesisAllergyDrugList.add(anamnesis);
                    break;
                case "00163e4597b14fe787c86e22b7946790":
                    anamnesis.setAnamnesisRemark(staticServer.getAnamnesisEatingDrugById(anamnesis.getOrderId()).get("result").toString());
                    anamnesisEatingDrugList.add(anamnesis);
                    break;
                case "00106a226f04411b885e3f328acba4d7":
                    anamnesis.setAnamnesisRemark(staticServer.getAnamnesisIllnessById(anamnesis.getOrderId()).get("result").toString());
                    anamnesisIllnessList.add(anamnesis);
                    break;
                case "0007fe67fa7c4c4195018ede7926a7c7":
                    anamnesis.setAnamnesisRemark(staticServer.getSurgicalHistoryById(anamnesis.getOrderId()).get("result").toString());
                    anamnesisSurgicalHistoryList.add(anamnesis);
                    break;
                case "0045a520eb9d4a3f93fbef4a2e9de0cf":
                    otherList.add(anamnesis);
                    break;
                default:
                    break;
            }
        }
        patientAnamnesisBean.setAnamnesisAllergyDrugList(anamnesisAllergyDrugList);
        patientAnamnesisBean.setAnamnesisEatingDrugList(anamnesisEatingDrugList);
        patientAnamnesisBean.setAnamnesisIllnessList(anamnesisIllnessList);
        patientAnamnesisBean.setAnamnesisSurgicalHistoryList(anamnesisSurgicalHistoryList);
        patientAnamnesisBean.setOtherList(otherList);

        return patientAnamnesisBean;
    }
}
