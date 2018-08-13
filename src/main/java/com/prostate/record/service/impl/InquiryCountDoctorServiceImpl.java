package com.prostate.record.service.impl;

import com.prostate.record.entity.InquiryCountDoctor;
import com.prostate.record.mapper.pra.read.InquiryCountDoctorReadMapper;
import com.prostate.record.mapper.pra.write.InquiryCountDoctorWriteMapper;
import com.prostate.record.service.InquiryCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryCountDoctorServiceImpl implements InquiryCountDoctorService {


    @Autowired
    private InquiryCountDoctorReadMapper inquiryCountDoctorReadMapper;

    @Autowired
    private InquiryCountDoctorWriteMapper inquiryCountDoctorWriteMapper;

    @Override
    public int insertSelective(InquiryCountDoctor inquiryCountDoctor) {
        return inquiryCountDoctorWriteMapper.insertSelective(inquiryCountDoctor);
    }

    @Override
    public int updateSelective(InquiryCountDoctor inquiryCountDoctor) {
        return 0;
    }

    @Override
    public InquiryCountDoctor selectById(String id) {
        return null;
    }

    @Override
    public List<InquiryCountDoctor> selectByParams(InquiryCountDoctor inquiryCountDoctor) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int getInquiryCount(String doctorId) {
        return inquiryCountDoctorReadMapper.getInquiryCount(doctorId);
    }
}
