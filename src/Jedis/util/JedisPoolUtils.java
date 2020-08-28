package Jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {
    private static JedisPool jedisPool1;
//    读取配置文件
    static{
    InputStream resourceAsStream = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
//    创建properties对象
    Properties properties = new Properties();
//    关联文件
    try {
        properties.load(resourceAsStream);
    } catch (IOException e) {
        e.printStackTrace();
    }
//    获得信息，设置到JedisPoolConfig中
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
    jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
//    初始化JedisPool
    jedisPool1 =new JedisPool(jedisPoolConfig,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));

}
//获得链接方法
    public static Jedis getJedis(){
        return jedisPool1.getResource();
    }

}
