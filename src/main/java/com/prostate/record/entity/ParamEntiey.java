package com.prostate.record.entity;

public class ParamEntiey {

    public String patientId;

    public String[] anamnesisAllergyDrugIds;

    public String[] anamnesisEatingDrugIds;

    public String[] anamnesisIllnessIds;

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
}
