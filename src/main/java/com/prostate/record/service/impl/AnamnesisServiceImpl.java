package com.prostate.record.service.impl;

import com.prostate.record.entity.Anamnesis;
import com.prostate.record.mapper.pomr.read.AnamnesisReadMapper;
import com.prostate.record.mapper.pomr.write.AnamnesisWriteMapper;
import com.prostate.record.service.AnamnesisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AnamnesisServiceImpl implements AnamnesisService {

    @Autowired
    private AnamnesisWriteMapper anamnesisWriteMapper;

    @Autowired
    private AnamnesisReadMapper anamnesisReadMapper;

    @Override
    public int insertSelective(Anamnesis anamnesis) {
        return anamnesisWriteMapper.insertSelective(anamnesis);
    }

    @CacheEvict(value = "HealthRrecord", key = "'health_record_'+#anamnesis.patientId", condition = "true", beforeInvocation = true)
    @Override
    public int updateSelective(Anamnesis anamnesis) {
        return anamnesisWriteMapper.updateSelective(anamnesis);
    }

    @Override
    public Anamnesis selectById(String id) {
        return anamnesisReadMapper.selectById(id);
    }

    @Override
    public List<Anamnesis> selectByParams(Anamnesis anamnesis) {
        return anamnesisReadMapper.selectByParams(anamnesis);
    }

    @Override
    public int deleteById(String id) {
        return anamnesisWriteMapper.deleteById(id);
    }

    @Override
    public boolean checkRepeated(Anamnesis anamnesis) {
        int i = anamnesisReadMapper.checkRepeated(anamnesis);
        return i > 0 ? true : false;
    }

}
