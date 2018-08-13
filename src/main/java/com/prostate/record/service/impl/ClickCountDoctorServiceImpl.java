package com.prostate.record.service.impl;

import com.prostate.record.entity.ClickCountDoctor;
import com.prostate.record.mapper.pra.read.ClickCountDoctorReadMapper;
import com.prostate.record.mapper.pra.write.ClickCountDoctorWriteMapper;
import com.prostate.record.service.ClickCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClickCountDoctorServiceImpl implements ClickCountDoctorService {

    @Autowired
    private ClickCountDoctorReadMapper clickCountDoctorReadMapper;

    @Autowired
    private ClickCountDoctorWriteMapper clickCountDoctorWriteMapper;

    @Override
    public int insertSelective(ClickCountDoctor clickCountDoctor) {
        return 0;
    }

    @Override
    public int updateSelective(ClickCountDoctor clickCountDoctor) {
        return 0;
    }

    @Override
    public ClickCountDoctor selectById(String id) {
        return null;
    }

    @Override
    public List<ClickCountDoctor> selectByParams(ClickCountDoctor clickCountDoctor) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int getClickCount(String doctorId) {
        return clickCountDoctorReadMapper.getClickCount();
    }
}
