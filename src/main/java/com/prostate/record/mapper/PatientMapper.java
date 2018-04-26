package com.prostate.record.mapper;

import com.prostate.record.beans.PatientBean;
import com.prostate.record.entity.Patient;

import java.util.List;


public interface PatientMapper extends BaseMapper<Patient>{


    List<PatientBean> selectByParamss(Patient e);
}