package com.prostate.record.cache.redis;

import com.prostate.record.entity.Doctor;
import com.prostate.record.entity.WechatUser;
import com.prostate.record.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MaxCoder
 *
 * @date 2017.04.09
 *
 * Redis 服务
 */
@Service
public class RedisSerive {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JsonUtil<Doctor> jsonUtil;

    public void insert(String key,String val) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key,val);
    }

    public String get(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public Doctor getDoctor(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return jsonUtil.jsonStrToObject(valueOperations.get(key));
    }

    public Doctor getDoctor() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        System.out.println("REDISGET======="+valueOperations.get(token));
        return jsonUtil.jsonStrToObject(valueOperations.get(token));
    }

    public boolean remove(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return stringRedisTemplate.delete(key);
    }
    public WechatUser getWechatUser(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return jsonUtil.jsonStrToWechatUser(valueOperations.get(key));
    }
}
