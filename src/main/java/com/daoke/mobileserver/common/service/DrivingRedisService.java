package com.daoke.mobileserver.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * User: chenlong
 * Date: 2015/3/26
 * Time: 13:08
 */
@Service
public class DrivingRedisService {

    @Autowired
    private RedisTemplate<String, String> drivingRedisTemplate;

    /**
     * hash 根据变量名 域名 取值/ 根据变量名 域名 value设置值
     *
     * @param ruleCode  规则码
     * @param ruleKey    规则对应的KEY
     * @param ruleValue  规则的值
     * @return
     */
    public Long getHRuleValue(final String ruleCode, final String ruleKey) {
        return drivingRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = drivingRedisTemplate.getStringSerializer();
                //先取出判断是否已存在
                byte[] value = redisConnection.hGet(serializer.serialize(ruleCode), serializer.serialize(ruleKey));
                if (value == null) {
                    return 0L;
                }
                return Long.parseLong(serializer.deserialize(value));
            }
        });
    }

}
