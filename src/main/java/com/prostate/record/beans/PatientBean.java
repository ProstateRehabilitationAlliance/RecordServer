package com.prostate.record.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prostate.record.entity.Patient;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientBean extends Patient {


    public String bloodGroup;

    public List<OrderBean> orderBeanList;

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<OrderBean> getOrderBeanList() {
        return orderBeanList;
    }

    public void setOrderBeanList(List<OrderBean> orderBeanList) {
        this.orderBeanList = orderBeanList;
    }
}
