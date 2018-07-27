package com.prostate.record.controller;

import com.prostate.record.cache.redis.RedisSerive;
import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.Doctor;
import com.prostate.record.entity.ParamEntiey;
import com.prostate.record.feignService.StaticServer;
import com.prostate.record.service.AnamnesisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "anamnesis")
public class AnamnesisController extends BaseController {

    @Autowired
    private AnamnesisService anamnesisService;

    @Autowired
    private RedisSerive redisSerive;

    @Autowired
    private StaticServer staticServer;


    /**
     * 单独添加病史信息
     *
     * @param token
     * @param paramEntiey
     * @return
     */
    @PostMapping(value = "addAnamnesis")
    public Map addAnamnesis(String token, @Valid ParamEntiey paramEntiey) {
        log.info("============患者档案既往病史添加========");
        resultMap = new LinkedHashMap<>();
        //参数校验
        if (paramEntiey == null) {
            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);

        //遍历添加病史项
        String patientId = paramEntiey.getPatientId();
        if (patientId == null && "".equals(patientId)) {
            return emptyParamResponse();
        }
        String[] anamnesisAllergyDrugIds = paramEntiey.getAnamnesisAllergyDrugIds();
        String[] anamnesisEatingDrugIds = paramEntiey.getAnamnesisEatingDrugIds();
        String[] anamnesisIllnessIds = paramEntiey.getAnamnesisIllnessIds();
        String[] otherIds = paramEntiey.getOtherIds();

        if (anamnesisAllergyDrugIds != null && anamnesisAllergyDrugIds.length > 0) {
            for (String anamnesisAllergyDrugId : anamnesisAllergyDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setOrderId(anamnesisAllergyDrugId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ebe7926a7c7");
                anamnesis.setCreateDoctor(doctor.getId());
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisEatingDrugIds != null && anamnesisEatingDrugIds.length > 0) {
            for (String anamnesisEatingDrugId : anamnesisEatingDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setOrderId(anamnesisEatingDrugId);
                anamnesis.setAnamnesisTypeId("00163e4597b14fe787c86e22b7946790");
                anamnesis.setCreateDoctor(doctor.getId());
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisIllnessIds != null && anamnesisIllnessIds.length > 0) {
            for (String anamnesisIllnessId : anamnesisIllnessIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setOrderId(anamnesisIllnessId);
                anamnesis.setAnamnesisTypeId("00106a226f04411b885e3f328acba4d7");
                anamnesis.setCreateDoctor(doctor.getId());
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (otherIds != null && otherIds.length > 0) {
            for (String otherId : otherIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setAnamnesisRemark(otherId);
                anamnesis.setAnamnesisTypeId("0045a520eb9d4a3f93fbef4a2e9de0cf");
                anamnesis.setCreateDoctor(doctor.getId());
                anamnesisService.insertSelective(anamnesis);
            }
        }
        return insertSuccseeResponse();
    }

    /****************************微信端接口 ***************************/
    @PostMapping(value = "delete")
    public Map deleteAnamnesis(String token, String id,String patientId) {
        if (id == null || "".equals(id)) {
            return emptyParamResponse();
        }
        int i = anamnesisService.deleteById(id,patientId);
        if (i >= 0) {
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }

}
