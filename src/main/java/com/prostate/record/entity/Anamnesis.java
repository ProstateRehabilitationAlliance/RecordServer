package com.prostate.record.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Anamnesis implements Serializable {
    private String id;

    private String patientId;

    private String anamnesisTypeId;

    private String orderId;

    private String anamnesisRemark;

    private String createDoctor;

    private Date createTime;

    private String updateDoctor;

    private Date updateTime;

    private String deleteDoctor;

    private Date deleteTime;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getAnamnesisTypeId() {
        return anamnesisTypeId;
    }

    public void setAnamnesisTypeId(String anamnesisTypeId) {
        this.anamnesisTypeId = anamnesisTypeId == null ? null : anamnesisTypeId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getAnamnesisRemark() {
        return anamnesisRemark;
    }

    public void setAnamnesisRemark(String anamnesisRemark) {
        this.anamnesisRemark = anamnesisRemark == null ? null : anamnesisRemark.trim();
    }

    public String getCreateDoctor() {
        return createDoctor;
    }

    public void setCreateDoctor(String createDoctor) {
        this.createDoctor = createDoctor == null ? null : createDoctor.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateDoctor() {
        return updateDoctor;
    }

    public void setUpdateDoctor(String updateDoctor) {
        this.updateDoctor = updateDoctor == null ? null : updateDoctor.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteDoctor() {
        return deleteDoctor;
    }

    public void setDeleteDoctor(String deleteDoctor) {
        this.deleteDoctor = deleteDoctor == null ? null : deleteDoctor.trim();
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }


    @Override
    public String toString() {
        return ",id:" + id
                + ",patientId:" + patientId
                + ",anamnesisTypeId:" + anamnesisTypeId
                + ",orderId:" + orderId
                + ",anamnesisRemark:" + anamnesisRemark
                + ",createDoctor:" + createDoctor
                + ",createTime:" + createTime
                + ",updateDoctor:" + updateDoctor
                + ",updateTime:" + updateTime
                + ",deleteDoctor:" + deleteDoctor
                + ",deleteTime:" + deleteTime
                + ",delFlag:" + delFlag;

    }
}