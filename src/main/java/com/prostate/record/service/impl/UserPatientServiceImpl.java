package com.prostate.record.service.impl;

import com.prostate.record.entity.UserPatient;
import com.prostate.record.mapper.pra.read.UserPatientReadMapper;
import com.prostate.record.mapper.pra.write.UserPatientWriteMapper;
import com.prostate.record.service.UserPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPatientServiceImpl implements UserPatientService {
    @Autowired
    private UserPatientWriteMapper userPatientWriteMapper;

    @Autowired
    private UserPatientReadMapper userPatientReadMapper;

    @Override
    public int insertSelective(UserPatient userPatient) {
        return userPatientWriteMapper.insertSelective(userPatient);
    }

    @Override
    public int updateSelective(UserPatient userPatient) {
        return 0;
    }

    @Override
    public UserPatient selectById(String id) {
        return null;
    }

    @Override
    public List<UserPatient> selectByParams(UserPatient userPatient) {
        return userPatientReadMapper.selectByParams(userPatient);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    /**
     * 创建用户患者关系
     *
     * @param patientId
     * @param token
     * @param patientSource
     * @return
     */
    @Override
    public int addUserPatient(String patientId, String token, String patientSource) {
        UserPatient userPatient = new UserPatient();
        userPatient.setPatientId(patientId);
        userPatient.setUserId(token);
        userPatient.setPatientSource(patientSource);
        return userPatientWriteMapper.insertSelective(userPatient);
    }
}
