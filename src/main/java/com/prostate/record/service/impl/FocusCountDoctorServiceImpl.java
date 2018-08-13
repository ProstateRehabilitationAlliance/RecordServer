package com.prostate.record.service.impl;

import com.prostate.record.entity.FocusCountDoctor;
import com.prostate.record.mapper.pra.read.FocusCountDoctorReadMapper;
import com.prostate.record.mapper.pra.write.FocusCountDoctorWriteMapper;
import com.prostate.record.service.FocusCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FocusCountDoctorServiceImpl implements FocusCountDoctorService {


    @Autowired
    private FocusCountDoctorReadMapper focusCountDoctorReadMapper;

    @Autowired
    private FocusCountDoctorWriteMapper focusCountDoctorWriteMapper;

    @Override
    public int insertSelective(FocusCountDoctor focusCountDoctor) {
        return focusCountDoctorWriteMapper.insertSelective(focusCountDoctor);
    }

    @Override
    public int updateSelective(FocusCountDoctor focusCountDoctor) {
        return 0;
    }

    @Override
    public FocusCountDoctor selectById(String id) {
        return null;
    }

    @Override
    public List<FocusCountDoctor> selectByParams(FocusCountDoctor focusCountDoctor) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int getFocusCount(String doctorId) {
        return focusCountDoctorReadMapper.getFocusCount(doctorId);
    }
}
