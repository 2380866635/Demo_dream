package com.content;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

public class DemoRedis {
   /* @Test
    public void TestRedisClient(){
        ClassPathXmlApplicationContext ctx = new
                ClassPathXmlApplicationContext("spring/applicationContext-redis.xml");
        JedisClient bean = ctx.getBean(JedisClient.class);
        bean.set("Text","測試");
        String text = bean.get("Text");
        System.out.println(text);

    }
*/
 /*   @Test
    public void testRedis() {
        *//*创建一个Jedis对象 相当于jdbs中的connection*//*
        Jedis jedis = new Jedis("192.168.1.12", 6379);
        jedis.set("dss", "张三");
        String dss = jedis.get("dss");
        System.out.println(dss);
        jedis.close();
    }
    @Test
    public void testRedisCluster(){
        HashSet<HostAndPort> nodes= new HashSet<>();
        nodes.add(new HostAndPort("192.168.1.12",7001));
        nodes.add(new HostAndPort("192.168.1.12",7002));
        nodes.add(new HostAndPort("192.168.1.12",7003));
        nodes.add(new HostAndPort("192.168.1.12",7004));
        nodes.add(new HostAndPort("192.168.1.12",7005));
        nodes.add(new HostAndPort("192.168.1.12",7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("dream_credis","256");
        String demo_cluster = jedisCluster.get("dream_credis");
        System.out.println(demo_cluster);
        jedisCluster.close();
    }*/
}