package com.prostate.record.service;

import com.prostate.record.beans.PatientAnamnesisBean;
import org.springframework.stereotype.Service;

@Service
public interface PatientAnamnesisService {

    PatientAnamnesisBean getHealthRrecord(String patientId);
}
