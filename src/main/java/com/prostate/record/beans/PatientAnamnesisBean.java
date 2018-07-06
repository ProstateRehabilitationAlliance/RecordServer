package com.prostate.record.beans;

import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.Patient;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public class PatientAnamnesisBean extends Patient implements Serializable {

    private List<Anamnesis> anamnesisAllergyDrugList;

    private List<Anamnesis> anamnesisEatingDrugList;

    private List<Anamnesis> anamnesisIllnessList;

    private List<Anamnesis> anamnesisSurgicalHistoryList;

    private List<Anamnesis> otherList;

    private String bloodGroup;

    private String professionName;

    private String EducationName;

    private String NationName;

    private String provinceId;

    private String citysId;

    private LinkedHashMap cityDetailBean;

    public void setPatient(Patient patient) {
        super.setId(patient.getId());
        super.setPatientNumber(patient.getPatientNumber());
        super.setPatientName(patient.getPatientName());
        super.setPatientCard(patient.getPatientCard());
        super.setPatientPhone(patient.getPatientPhone());
        super.setPatientBirthday(patient.getPatientBirthday());
        super.setPatientAge(patient.getPatientAge());
        super.setPatientSex(patient.getPatientSex());
        super.setPatientHeight(patient.getPatientHeight());
        super.setPatientWeight(patient.getPatientWeight());
        super.setDetailAddress(patient.getDetailAddress());
        super.setCreateTime(patient.getCreateTime());
        super.setBloodGroupId(patient.getBloodGroupId());
        super.setEducationId(patient.getEducationId());
        super.setNationId(patient.getNationId());
        super.setProfessionId(patient.getProfessionId());
    }


    public LinkedHashMap getCityDetailBean() {
        return cityDetailBean;
    }

    public void setCityDetailBean(LinkedHashMap cityDetailBean) {
        this.cityDetailBean = cityDetailBean;
    }

    public String getEducationName() {
        return EducationName;
    }

    public void setEducationName(String educationName) {
        EducationName = educationName;
    }

    public String getNationName() {
        return NationName;
    }

    public void setNationName(String nationName) {
        NationName = nationName;
    }

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
