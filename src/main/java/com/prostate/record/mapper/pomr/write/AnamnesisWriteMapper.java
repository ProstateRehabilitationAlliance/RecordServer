package com.prostate.record.mapper.pomr.write;

import com.prostate.record.entity.Anamnesis;
import com.prostate.record.mapper.BaseWriteMapper;

public interface AnamnesisWriteMapper extends BaseWriteMapper<Anamnesis> {

    int checkRepeated(Anamnesis anamnesis);
}