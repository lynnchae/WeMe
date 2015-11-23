package com.daoke.mobileserver.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * User: chenlong
 * Date: 2015/3/26
 * Time: 13:08
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisTemplate<String, String> drivingRedisTemplate;

    /**
     * 根据key从redis里面取出value
     *
     * @param key   key
     */
    public String getSecret( String key) {

        try {
            final String key1 = key;
            String secret = redisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] key = serializer.serialize(key1 + ":appKeyInfo");
                    byte[] value = connection.hGet(key, serializer.serialize("secret"));
                    if (value == null) {
                        return null;
                    }
                    String secret = serializer.deserialize(value);
                    return secret;
                }
            });
            return secret;
        }catch(Exception e){
                e.printStackTrace();
        }
        return "";
    }


}
