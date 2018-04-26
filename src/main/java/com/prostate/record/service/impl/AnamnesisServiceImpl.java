package com.prostate.record.service.impl;

import com.prostate.record.entity.Anamnesis;
import com.prostate.record.mapper.AnamnesisMapper;
import com.prostate.record.service.AnamnesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnamnesisServiceImpl implements AnamnesisService {

    @Autowired
    private AnamnesisMapper anamnesisMapper;

    @Override
    public int insertSelective(Anamnesis anamnesis) {
        return anamnesisMapper.insertSelective(anamnesis);
    }

    @Override
    public int updateSelective(Anamnesis anamnesis) {
        return anamnesisMapper.updateSelective(anamnesis);
    }

    @Override
    public Anamnesis selectById(String id) {
        return anamnesisMapper.selectById(id);
    }

    @Override
    public List<Anamnesis> selectByParams(Anamnesis anamnesis) {
        return anamnesisMapper.selectByParams(anamnesis);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
