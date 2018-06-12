package com.prostate.record.service;

import com.prostate.record.entity.Anamnesis;

public interface AnamnesisService extends BaseService<Anamnesis> {
    boolean checkRepeated(Anamnesis anamnesis);
}
