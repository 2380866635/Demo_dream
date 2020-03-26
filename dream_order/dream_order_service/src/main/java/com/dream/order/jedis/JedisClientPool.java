package com.dream.order.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * 单机版 redis
 */
@Component
public class JedisClientPool implements JedisClient {
  @Autowired
   private JedisPool jedisPool;
    @Override
    public String set(String key, String value) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作
        String set = jedis.set(key, value);
        //3.关闭jedis
        jedis.close();
        return set;
    }

    @Override
    public String get(String key) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作
        String get = jedis.get(key);
        //3.关闭jedis
        jedis.close();
        return get;
    }

    @Override
    public Boolean exists(String key) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作/
        Boolean exists = jedis.exists(key);

        //3.关闭jedis
        jedis.close();

        return exists;
    }

    @Override
    public Long expire(String key, int seconds) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作
        Long expire = jedis.expire(key, seconds);
        //3.关闭jedis
        jedis.close();
        return expire;
    }

    @Override
    public Long ttl(String key) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作
        Long ttl = jedis.ttl(key);
        //3.关闭jedis
        jedis.close();

        return ttl;
    }

    @Override
    public Long incr(String key) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作
        Long incr = jedis.incr(key);
        //3.关闭jedis
        jedis.close();

        return incr;
    }

    @Override
    public Long hset(String key, String field, String value) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作
        Long hset = jedis.hset(key, field, value);
        //3.关闭jedis
        jedis.close();
        return hset;
    }

    @Override
    public String hget(String key, String field) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作
        String hget = jedis.hget(key, field);
        //3.关闭jedis
        jedis.close();
        return hget;
    }

    @Override
    public Long hdel(String key, String... field) {
        //1得到jedis
        Jedis jedis = jedisPool.getResource();
        //2设置执行操作
        Long hdel = jedis.hdel(key, field);
        //3.关闭jedis
        jedis.close();
        return hdel;
    }
}
