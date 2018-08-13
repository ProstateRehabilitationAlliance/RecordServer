package com.prostate.record.entity;

import java.util.Date;

public class FocusCountDoctor {
    private String id;

    private String doctorId;

    private String focusStatus;

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

    public String getFocusStatus() {
        return focusStatus;
    }

    public void setFocusStatus(String focusStatus) {
        this.focusStatus = focusStatus == null ? null : focusStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}