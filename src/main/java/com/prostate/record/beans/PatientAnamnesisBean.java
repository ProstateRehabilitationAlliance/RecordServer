package com.prostate.record.beans;

import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.Patient;

import java.util.List;

public class PatientAnamnesisBean extends Patient {

    private List<Anamnesis> anamnesisAllergyDrugList;

    private List<Anamnesis> anamnesisEatingDrugList;

    private List<Anamnesis> anamnesisIllnessList;

    private List<Anamnesis> anamnesisSurgicalHistoryList;

    private List<Anamnesis> otherList;

    public String bloodGroup;

    public String professionName;

    public String provinceId;

    public String citysId;


    public List<Anamnesis> getAnamnesisAllergyDrugList() {
        return anamnesisAllergyDrugList;
    }

    public void setAnamnesisAllergyDrugList(List<Anamnesis> anamnesisAllergyDrugList) {
        this.anamnesisAllergyDrugList = anamnesisAllergyDrugList;
    }

    public List<Anamnesis> getAnamnesisEatingDrugList() {
        return anamnesisEatingDrugList;
    }

    public void setAnamnesisEatingDrugList(List<Anamnesis> anamnesisEatingDrugList) {
        this.anamnesisEatingDrugList = anamnesisEatingDrugList;
    }

    public List<Anamnesis> getAnamnesisIllnessList() {
        return anamnesisIllnessList;
    }

    public void setAnamnesisIllnessList(List<Anamnesis> anamnesisIllnessList) {
        this.anamnesisIllnessList = anamnesisIllnessList;
    }

    public List<Anamnesis> getAnamnesisSurgicalHistoryList() {
        return anamnesisSurgicalHistoryList;
    }

    public void setAnamnesisSurgicalHistoryList(List<Anamnesis> anamnesisSurgicalHistoryList) {
        this.anamnesisSurgicalHistoryList = anamnesisSurgicalHistoryList;
    }

    public List<Anamnesis> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<Anamnesis> otherList) {
        this.otherList = otherList;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCitysId() {
        return citysId;
    }

    public void setCitysId(String citysId) {
        this.citysId = citysId;
    }
}
