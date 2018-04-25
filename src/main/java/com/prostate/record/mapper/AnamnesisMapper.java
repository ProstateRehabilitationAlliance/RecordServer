package com.prostate.record.mapper;

import com.prostate.record.entity.Anamnesis;

public interface AnamnesisMapper {
    int insert(Anamnesis record);

    int insertSelective(Anamnesis record);
}