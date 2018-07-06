package com.prostate.record.service;

import com.prostate.record.entity.Anamnesis;

import java.util.List;

public interface AnamnesisService extends BaseService<Anamnesis> {

    boolean checkRepeated(Anamnesis anamnesis);

    int deleteById(String id,String patientId);

}
