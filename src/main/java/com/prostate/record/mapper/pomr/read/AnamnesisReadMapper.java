package com.prostate.record.mapper.pomr.read;

import com.prostate.record.entity.Anamnesis;
import com.prostate.record.mapper.BaseReadMapper;

import java.util.List;

public interface AnamnesisReadMapper extends BaseReadMapper<Anamnesis> {

    int checkRepeated(Anamnesis anamnesis);

    List<Anamnesis> getByPatientId(String patientId);
}