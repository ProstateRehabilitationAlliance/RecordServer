package com.prostate.record.service;

import com.prostate.record.entity.FocusCountDoctor;
import org.springframework.stereotype.Service;

@Service
public interface FocusCountDoctorService extends BaseService<FocusCountDoctor> {
    int getFocusCount(String doctorId);
}
