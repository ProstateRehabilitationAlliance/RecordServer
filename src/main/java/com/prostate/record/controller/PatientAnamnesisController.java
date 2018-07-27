package com.prostate.record.controller;

import com.prostate.record.beans.PatientAnamnesisBean;
import com.prostate.record.cache.redis.RedisSerive;
import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.ParamEntiey;
import com.prostate.record.entity.Patient;
import com.prostate.record.entity.WechatUser;
import com.prostate.record.service.AnamnesisService;
import com.prostate.record.service.PatientAnamnesisService;
import com.prostate.record.service.PatientService;
import com.prostate.record.service.UserPatientService;
import com.prostate.record.util.IdCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "patientAnamnesis")
public class PatientAnamnesisController extends BaseController {


    private PatientService patientService;

    private AnamnesisService anamnesisService;

    private RedisSerive redisSerive;

    private PatientAnamnesisService patientAnamnesisService;

    private UserPatientService userPatientService;

    @Autowired
    public PatientAnamnesisController(PatientService patientService, AnamnesisService anamnesisService, RedisSerive redisSerive, PatientAnamnesisService patientAnamnesisService, UserPatientService userPatientService) {
        this.patientService = patientService;
        this.anamnesisService = anamnesisService;
        this.redisSerive = redisSerive;
        this.patientAnamnesisService = patientAnamnesisService;
        this.userPatientService = userPatientService;
    }

    /**
     * 同时  创建 患者 和 患者病历
     *
     * @param patient
     * @param paramEntiey
     * @param token
     * @return
     */
    @PostMapping(value = "add")
    public Map addPatient(Patient patient, ParamEntiey paramEntiey, String token) {

        if (patient.getPatientName() == null || "".equals(patient.getPatientName())) {
            return emptyParamResponse();
        }

        patient.setCreateDoctor(token);
        patient.setPatientNumber("PRA" + System.currentTimeMillis());
        patient.setPatientAge(IdCardUtil.getAgeByIdCard(patient.getPatientCard()));

        int i = patientService.insertSelective(patient);


        if (i < 0) {
            return insertFailedResponse();
        }

        String[] anamnesisAllergyDrugIds = paramEntiey.getAnamnesisAllergyDrugIds();
        String[] anamnesisEatingDrugIds = paramEntiey.getAnamnesisEatingDrugIds();
        String[] anamnesisIllnessIds = paramEntiey.getAnamnesisIllnessIds();
        String[] anamnesisSurgicalHistoryIds = paramEntiey.getAnamnesisSurgicalHistoryIds();
        String[] otherIds = paramEntiey.getOtherIds();

        if (anamnesisAllergyDrugIds != null && anamnesisAllergyDrugIds.length > 0) {
            for (String anamnesisAllergyDrugId : anamnesisAllergyDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisAllergyDrugId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ebe7926a7c7");
                anamnesis.setCreateDoctor(token);
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisEatingDrugIds != null && anamnesisEatingDrugIds.length > 0) {
            for (String anamnesisEatingDrugId : anamnesisEatingDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisEatingDrugId);
                anamnesis.setAnamnesisTypeId("00163e4597b14fe787c86e22b7946790");
                anamnesis.setCreateDoctor(token);
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisIllnessIds != null && anamnesisIllnessIds.length > 0) {
            for (String anamnesisIllnessId : anamnesisIllnessIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisIllnessId);
                anamnesis.setAnamnesisTypeId("00106a226f04411b885e3f328acba4d7");
                anamnesis.setCreateDoctor(token);
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (otherIds != null && otherIds.length > 0) {
            for (String otherId : otherIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setAnamnesisRemark(otherId);
                anamnesis.setAnamnesisTypeId("0045a520eb9d4a3f93fbef4a2e9de0cf");
                anamnesis.setCreateDoctor(token);
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisSurgicalHistoryIds != null && anamnesisSurgicalHistoryIds.length > 0) {
            for (String anamnesisSurgicalHistoryId : anamnesisSurgicalHistoryIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisSurgicalHistoryId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ede7926a7c7");
                anamnesis.setCreateDoctor(token);
                anamnesisService.insertSelective(anamnesis);
            }
        }
        userPatientService.addUserPatient(patient.getId(), token, "门诊");
        return insertSuccseeResponse(patient.getId());

    }


    /**
     * 根据患者ID查询病历信息
     *
     * @param patientId
     * @deprecated
     */
    @PostMapping(value = "getHealthRrecord")
    public Map getHealthRrecord(String patientId) {
        //参数校验
        if (patientId == null || patientId.length() != 32) {
            return emptyParamResponse();
        }

        //查询
        PatientAnamnesisBean patientAnamnesisBean = patientAnamnesisService.getHealthRrecord(patientId);

        //查询结果校验
        if (patientAnamnesisBean != null) {
            return querySuccessResponse(patientAnamnesisBean);
        }
        return queryEmptyResponse();
    }


    /********************************微信端 接口**************************************/
    /**
     * 修改 患者 信息 病历 信息
     *
     * @param patient
     * @param paramEntiey
     */
    @PostMapping(value = "update")
    public Map updatePatient(Patient patient, ParamEntiey paramEntiey, String orderAnamnesisId, String orderAnamnesisRemark) {

        if (paramEntiey.getPatientId() == null || "".equals(paramEntiey.getPatientId())) {
            return emptyParamResponse();
        }

        //修改患者信息
        patient.setId(paramEntiey.getPatientId());
        int i = patientService.updateSelective(patient);


        if (i < 0) {
            return updateFailedResponse();
        }

        /**
         * 修改病史信息
         */
        String[] anamnesisAllergyDrugIds = paramEntiey.getAnamnesisAllergyDrugIds();
        String[] anamnesisEatingDrugIds = paramEntiey.getAnamnesisEatingDrugIds();
        String[] anamnesisIllnessIds = paramEntiey.getAnamnesisIllnessIds();
        String[] anamnesisSurgicalHistoryIds = paramEntiey.getAnamnesisSurgicalHistoryIds();
        boolean isRepeated;
        if (anamnesisAllergyDrugIds != null && anamnesisAllergyDrugIds.length > 0) {
            for (String anamnesisAllergyDrugId : anamnesisAllergyDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisAllergyDrugId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ebe7926a7c7");
                isRepeated = anamnesisService.checkRepeated(anamnesis);
                if (isRepeated) {
                    continue;
                }
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisEatingDrugIds != null && anamnesisEatingDrugIds.length > 0) {
            for (String anamnesisEatingDrugId : anamnesisEatingDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisEatingDrugId);
                anamnesis.setAnamnesisTypeId("00163e4597b14fe787c86e22b7946790");
                isRepeated = anamnesisService.checkRepeated(anamnesis);
                if (isRepeated) {
                    continue;
                }
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisIllnessIds != null && anamnesisIllnessIds.length > 0) {
            for (String anamnesisIllnessId : anamnesisIllnessIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisIllnessId);
                anamnesis.setAnamnesisTypeId("00106a226f04411b885e3f328acba4d7");
                isRepeated = anamnesisService.checkRepeated(anamnesis);
                if (isRepeated) {
                    continue;
                }
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisSurgicalHistoryIds != null && anamnesisSurgicalHistoryIds.length > 0) {
            for (String anamnesisSurgicalHistoryId : anamnesisSurgicalHistoryIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisSurgicalHistoryId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ede7926a7c7");
                isRepeated = anamnesisService.checkRepeated(anamnesis);
                if (isRepeated) {
                    continue;
                }
                anamnesisService.insertSelective(anamnesis);
            }
        }
        //修改其他病史信息
        if ((orderAnamnesisId == null || orderAnamnesisId.length() != 32) && (orderAnamnesisRemark == null || "".equals(orderAnamnesisRemark))) {
            return updateSuccseeResponse();
        } else if ((orderAnamnesisId == null || orderAnamnesisId.length() != 32) && orderAnamnesisRemark != null && !"".equals(orderAnamnesisRemark)) {
            Anamnesis anamnesis = new Anamnesis();
            anamnesis.setAnamnesisRemark(orderAnamnesisRemark);
            anamnesis.setPatientId(patient.getId());
            anamnesis.setAnamnesisTypeId("0045a520eb9d4a3f93fbef4a2e9de0cf");
            anamnesisService.insertSelective(anamnesis);
        } else {
            Anamnesis anamnesis = new Anamnesis();
            anamnesis.setPatientId(patient.getId());
            anamnesis.setId(orderAnamnesisId);
            anamnesis.setAnamnesisRemark(orderAnamnesisRemark);
            anamnesisService.updateSelective(anamnesis);
        }
        return updateSuccseeResponse();

    }


    /**
     * 微信 用户查询 病历信息
     *
     * @param token
     * @return
     */
    @PostMapping(value = "selete")
    public Map seletePatient(String token) {

        WechatUser wechatUser = redisSerive.getWechatUser(token);

        //查询
        PatientAnamnesisBean patientAnamnesisBean = patientAnamnesisService.getHealthRrecord(wechatUser.getId());

        //查询结果校验
        if (patientAnamnesisBean != null) {
            return querySuccessResponse(patientAnamnesisBean);
        }
        return queryEmptyResponse();
    }


    /**
     * 同时  创建 患者 和 患者病历
     *
     * @param patient
     * @param paramEntiey
     * @param token
     * @return
     */
    @PostMapping(value = "weChatAdd")
    public Map weChatAdd(Patient patient, ParamEntiey paramEntiey, String token) {

        if (patient.getPatientName() == null || "".equals(patient.getPatientName())) {
            return emptyParamResponse();
        }

        patient.setId(token);
        patient.setCreateDoctor(token);
        patient.setPatientNumber("PRA" + System.currentTimeMillis());
        patient.setPatientAge(IdCardUtil.getAgeByIdCard(patient.getPatientCard()));

        int i = patientService.insertSelectiveById(patient);


        if (i < 0) {
            return insertFailedResponse();
        }

        String[] anamnesisAllergyDrugIds = paramEntiey.getAnamnesisAllergyDrugIds();
        String[] anamnesisEatingDrugIds = paramEntiey.getAnamnesisEatingDrugIds();
        String[] anamnesisIllnessIds = paramEntiey.getAnamnesisIllnessIds();
        String[] anamnesisSurgicalHistoryIds = paramEntiey.getAnamnesisSurgicalHistoryIds();
        String[] otherIds = paramEntiey.getOtherIds();

        if (anamnesisAllergyDrugIds != null && anamnesisAllergyDrugIds.length > 0) {
            for (String anamnesisAllergyDrugId : anamnesisAllergyDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisAllergyDrugId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ebe7926a7c7");
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisEatingDrugIds != null && anamnesisEatingDrugIds.length > 0) {
            for (String anamnesisEatingDrugId : anamnesisEatingDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisEatingDrugId);
                anamnesis.setAnamnesisTypeId("00163e4597b14fe787c86e22b7946790");
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisIllnessIds != null && anamnesisIllnessIds.length > 0) {
            for (String anamnesisIllnessId : anamnesisIllnessIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisIllnessId);
                anamnesis.setAnamnesisTypeId("00106a226f04411b885e3f328acba4d7");
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (otherIds != null && otherIds.length > 0) {
            for (String otherId : otherIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setAnamnesisRemark(otherId);
                anamnesis.setAnamnesisTypeId("0045a520eb9d4a3f93fbef4a2e9de0cf");
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisSurgicalHistoryIds != null && anamnesisSurgicalHistoryIds.length > 0) {
            for (String anamnesisSurgicalHistoryId : anamnesisSurgicalHistoryIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patient.getId());
                anamnesis.setOrderId(anamnesisSurgicalHistoryId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ede7926a7c7");
                anamnesisService.insertSelective(anamnesis);
            }
        }

        return insertSuccseeResponse(patient.getId());

    }

}
