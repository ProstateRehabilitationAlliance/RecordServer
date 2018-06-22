package com.prostate.record.service.impl;


import com.prostate.record.entity.DoctorPatient;
import com.prostate.record.mapper.DoctorPatientMapper;
import com.prostate.record.service.DoctorPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorPatientServiceImpl implements DoctorPatientService {
    @Autowired
    private DoctorPatientMapper doctorPatientMapper;

    @Override
    public int insertSelective(DoctorPatient doctorPatient) {
        return doctorPatientMapper.insertSelective(doctorPatient);
    }

    @Override
    public int updateSelective(DoctorPatient doctorPatient) {
        return 0;
    }

    @Override
    public DoctorPatient selectById(String id) {
        return null;
    }

    @Override
    public List<DoctorPatient> selectByParams(DoctorPatient doctorPatient) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
