package com.dream.sso.jedis;

public interface JedisClient {
    /**新增键值对*/
    String set(String key, String value);
    /**根据Key得到value*/
    String get(String key);
    /*判断key是否存在*/
    Boolean exists(String key);
    /*设置Key的过期时间  单位秒*/
    Long expire(String key, int seconds);
    /*获取剩余的key时间*/
    Long ttl(String key);
    /*key 对应的value值加*/
    Long incr(String key);
    /*一个key对应一个value这个value 是一个map一个map又是一个键值对*/
    Long hset(String key, String field, String value);
    /*获取hash为key的数据field的value*/
    String hget(String key, String field);
    /*从hash中删除多个  或一个field
    * String 。。。field可以是多个String 参数
    * hdel("demo","field1")
    * hdel("demo","field1","field2">>>)
    * hdel（"demo"）会首先调用Longhdel（String key）方法
    * 如果方法不存在则会调用Long hdel（String key,String...field）
    * */
    Long hdel(String key, String... field);

}
