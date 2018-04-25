package com.prostate.record.utlis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.prostate.record.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil<E> {

    /**
     * java普通对象和json字符串的互转
     */

    public String objectToJsonStr(Doctor doctor){
        return JSONObject.toJSONString(doctor);
    }

    public Doctor jsonStrToObject(String jsonStr){
        return JSON.parseObject(jsonStr, Doctor.class);

    }
}
