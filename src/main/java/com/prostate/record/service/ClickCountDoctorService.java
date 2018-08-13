package com.prostate.record.service;

import com.prostate.record.entity.ClickCountDoctor;
import org.springframework.stereotype.Service;

@Service
public interface ClickCountDoctorService extends BaseService<ClickCountDoctor> {
    int getClickCount(String doctorId);
}
