package com.prostate.record.service;

import com.prostate.record.entity.UserPatient;
import org.springframework.stereotype.Service;

@Service
public interface UserPatientService extends BaseService<UserPatient> {
    int addUserPatient(String patientId, String token,String patientSource);
}
