package com.prostate.record.mapper.pra.read;

import com.prostate.record.entity.ClickCountDoctor;
import com.prostate.record.mapper.BaseReadMapper;

public interface ClickCountDoctorReadMapper extends BaseReadMapper<ClickCountDoctor> {

    int getClickCount();
}