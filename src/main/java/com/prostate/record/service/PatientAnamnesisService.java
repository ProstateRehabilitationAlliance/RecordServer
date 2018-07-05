package com.prostate.record.service;

import com.prostate.record.beans.PatientBean;
import org.springframework.stereotype.Service;

@Service
public interface PatientAnamnesisService {

    PatientBean getHealthRrecord(String patientId);
}
