package com.prostate.record.mapper.pra.read;

import com.prostate.record.entity.FocusCountDoctor;
import com.prostate.record.mapper.BaseReadMapper;

public interface FocusCountDoctorReadMapper extends BaseReadMapper<FocusCountDoctor> {

    int getFocusCount(String doctorId);
}