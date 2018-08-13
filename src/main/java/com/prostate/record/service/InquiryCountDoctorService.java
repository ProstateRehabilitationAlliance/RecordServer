package com.prostate.record.service;

import com.prostate.record.entity.InquiryCountDoctor;
import org.springframework.stereotype.Service;

@Service
public interface InquiryCountDoctorService extends BaseService<InquiryCountDoctor> {
    int getInquiryCount(String doctorId);
}
