package com.prostate.record.controller;

import com.prostate.record.cache.redis.RedisSerive;
import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.Doctor;
import com.prostate.record.entity.ParamEntiey;
import com.prostate.record.entity.Patient;
import com.prostate.record.service.AnamnesisService;
import com.prostate.record.service.PatientService;
import com.prostate.record.util.IdCardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "patientAnamnesis")
public class PatientAnamnesisController extends BaseController {


    private final PatientService patientService;

    private final AnamnesisService anamnesisService;

    private final RedisSerive redisSerive;

    @Autowired
    public PatientAnamnesisController(PatientService patientService, AnamnesisService anamnesisService, RedisSerive redisSerive) {
        this.patientService = patientService;
        this.anamnesisService = anamnesisService;
        this.redisSerive = redisSerive;
    }

    @PostMapping(value = "add")
    public Map addPatient(Patient patient, ParamEntiey paramEntiey, String token) {

        if (patient.getPatientName() == null || "".equals(patient.getPatientName())) {
            return emptyParamResponse();
        }

        Doctor doctor = redisSerive.getDoctor(token);

        log.info("============患者基本信息添加========");

        patient.setCreateDoctor(doctor.getId());
        patient.setPatientNumber("PRA" + System.currentTimeMillis());
        patient.setPatientAge(IdCardUtil.getAgeByIdCard(patient.getPatientCard()));

        int i = patientService.insertSelective(patient);


        if (i < 0) {
            return insertFailedResponse();
        }
        log.info("============患者档案既往病史添加========");

        String[] anamnesisAllergyDrugIds = paramEntiey.getAnamnesisAllergyDrugIds();
        String[] anamnesisEatingDrugIds = paramEntiey.getAnamnesisEatingDrugIds();
        String[] anamnesisIllnessIds = paramEntiey.getAnamnesisIllnessIds();
        String[] otherIds = paramEntiey.getOtherIds();

        if (anamnesisAllergyDrugIds != null && anamnesisAllergyDrugIds.length > 0) {
            for (String anamnesisAllergyDrugId : anamnesisAllergyDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisAllergyDrugId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ebe7926a7c7");
                anamnesis.setCreateDoctor(doctor.getId());
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisEatingDrugIds != null && anamnesisEatingDrugIds.length > 0) {
            for (String anamnesisEatingDrugId : anamnesisEatingDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisEatingDrugId);
                anamnesis.setAnamnesisTypeId("00163e4597b14fe787c86e22b7946790");
                anamnesis.setCreateDoctor(doctor.getId());
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisIllnessIds != null && anamnesisIllnessIds.length > 0) {
            for (String anamnesisIllnessId : anamnesisIllnessIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisIllnessId);
                anamnesis.setAnamnesisTypeId("00106a226f04411b885e3f328acba4d7");
                anamnesis.setCreateDoctor(doctor.getId());
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (otherIds != null && otherIds.length > 0) {
            for (String otherId : otherIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setAnamnesisRemark(otherId);
                anamnesis.setAnamnesisTypeId("0045a520eb9d4a3f93fbef4a2e9de0cf");
                anamnesis.setCreateDoctor(doctor.getId());
                anamnesisService.insertSelective(anamnesis);
            }
        }

        return insertSuccseeResponse(patient.getId());

    }
}