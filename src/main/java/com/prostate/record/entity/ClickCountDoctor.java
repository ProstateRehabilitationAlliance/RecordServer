package com.prostate.record.entity;

import java.util.Date;

public class ClickCountDoctor {
    private String id;

    private String doctorId;

    private String clickStatus;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getClickStatus() {
        return clickStatus;
    }

    public void setClickStatus(String clickStatus) {
        this.clickStatus = clickStatus == null ? null : clickStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}