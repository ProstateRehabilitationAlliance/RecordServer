package com.prostate.record.mapper.pra.read;

import com.prostate.record.entity.InquiryCountDoctor;
import com.prostate.record.mapper.BaseReadMapper;

public interface InquiryCountDoctorReadMapper  extends BaseReadMapper<InquiryCountDoctor> {

    int getInquiryCount(String doctorId);
}