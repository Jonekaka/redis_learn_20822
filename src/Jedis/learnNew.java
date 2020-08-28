package Jedis;


import Jedis.util.JedisPoolUtils;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

class learnNew {
    @Test
    public void getJedis1() {
//        快速入门
        //    获得redis链接
//        如果空参，默认"localhost",6379，本机配置
        Jedis jedis=new Jedis("localhost",6379);
        //    操作数据
        jedis.set("username","lisi");
        System.out.println(jedis.get("username"));
        //    关闭
        jedis.close();
    }
//    其他数据格式类似命令行操作，string数据结构操作如下
    public void stringTest(){
        //        如果空参，默认"localhost",6379，本机配置
        Jedis jedis=new Jedis();
        //    操作数据
        jedis.set("username","lisi");
        System.out.println(jedis.get("username"));
//        使用setex()指定存入setex中键值对的存活时间,比如短信验证码一类
        jedis.setex("activecode",20,"hehe");
        //    关闭
        jedis.close();
    }
    @Test
//    hash数据结构操作
    public void hashTest(){
        //        如果空参，默认"localhost",6379，本机配置
        Jedis jedis=new Jedis();
        //    操作数据
        jedis.hset("user","username0","lisi");
        jedis.hset("user","username1","lisi");
        jedis.hset("user","username2","lisi");
//        获得hash
        String hget = jedis.hget("user", "username0");
        System.out.println(hget);
//        获得hash中所有的map数据
        Map<String, String> user = jedis.hgetAll("user");
        Set<String> strings = user.keySet();
        for(String key:strings){
            String s = user.get(key);
            System.out.println(s);
        }
//        关闭
        jedis.close();
    }
//    list数据结构操作
    @Test
    public void listTest(){
        Jedis jedis = new Jedis();
//        存入元素，a,b并不是一个整体存入，而是一个个，所以内部排序是b,a,b,a
        jedis.lpush("mylist","a","b");
        jedis.lpush("mylist","a","b");
        jedis.rpush("mylist","c");
//        范围获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);
//        弹出,返回的是弹出的元素，rpop
        String mylist1 = jedis.lpop("mylist");
        System.out.println(mylist1);
        //        范围获取
        List<String> mylist2 = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist2);
//        关闭
        jedis.close();

    }
//    set数据结构操作
    @Test
    public void setTest(){
//        获得链接
        Jedis jedis = new Jedis();
//        操作
        jedis.sadd("myset","java","php","c++");
//        set获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        jedis.close();
    }
//    sortedset数据结构操作
    @Test
    public void sortedset(){
//        获得链接
        Jedis jedis = new Jedis();
//        操作
        jedis.zadd("mysortedset",15,"后羿");
        jedis.zadd("mysortedset",12,"孙悟空");
        jedis.zadd("mysortedset",13,"猪八戒");
//        获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
//        关闭链接
        jedis.close();
    }
//    使用连接池
    @Test
    public void poolTest(){
//        创建一个配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
//        创建jedis连接池对象
        JedisPool jlocalhost = new JedisPool(jedisPoolConfig, "localhost", 6379);
//        获得链接
        Jedis resource = jlocalhost.getResource();
//        使用
        resource.set("hhee","hheiei");
        System.out.println(resource.get("hhee"));
//        关闭，归还到连接池
        resource.close();
    }
//    连接池工具类使用
    @Test
    public void utilsTest(){
//        通过连接池工具类获得
        Jedis jedis= JedisPoolUtils.getJedis();
//        使用
        //    操作数据
        jedis.set("username","lisi");
        System.out.println(jedis.get("username"));
        //    关闭
        jedis.close();
    }
}
