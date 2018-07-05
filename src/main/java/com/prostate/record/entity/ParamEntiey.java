package com.prostate.record.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Arrays;

public class ParamEntiey {

    @Length(min = 32,max = 32,message = "患者ID格式不正确")
    public String patientId;

    public String[] anamnesisAllergyDrugIds;

    public String[] anamnesisEatingDrugIds;

    public String[] anamnesisIllnessIds;

    public String[] anamnesisSurgicalHistoryIds;

    public String[] otherIds;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String[] getAnamnesisAllergyDrugIds() {
        return anamnesisAllergyDrugIds;
    }

    public void setAnamnesisAllergyDrugIds(String[] anamnesisAllergyDrugIds) {
        this.anamnesisAllergyDrugIds = anamnesisAllergyDrugIds;
    }

    public String[] getAnamnesisEatingDrugIds() {
        return anamnesisEatingDrugIds;
    }

    public void setAnamnesisEatingDrugIds(String[] anamnesisEatingDrugIds) {
        this.anamnesisEatingDrugIds = anamnesisEatingDrugIds;
    }

    public String[] getAnamnesisIllnessIds() {
        return anamnesisIllnessIds;
    }

    public void setAnamnesisIllnessIds(String[] anamnesisIllnessIds) {
        this.anamnesisIllnessIds = anamnesisIllnessIds;
    }

    public String[] getOtherIds() {
        return otherIds;
    }

    public void setOtherIds(String[] otherIds) {
        this.otherIds = otherIds;
    }

    public String[] getAnamnesisSurgicalHistoryIds() {
        return anamnesisSurgicalHistoryIds;
    }

    public void setAnamnesisSurgicalHistoryIds(String[] anamnesisSurgicalHistoryIds) {
        this.anamnesisSurgicalHistoryIds = anamnesisSurgicalHistoryIds;
    }

    @Override
    public String toString() {
        return "ParamEntiey{" +
                "patientId='" + patientId + '\'' +
                ", anamnesisAllergyDrugIds=" + Arrays.toString(anamnesisAllergyDrugIds) +
                ", anamnesisEatingDrugIds=" + Arrays.toString(anamnesisEatingDrugIds) +
                ", anamnesisIllnessIds=" + Arrays.toString(anamnesisIllnessIds) +
                ", anamnesisSurgicalHistoryIds=" + Arrays.toString(anamnesisSurgicalHistoryIds) +
                ", otherIds=" + Arrays.toString(otherIds) +
                '}';
    }
}
