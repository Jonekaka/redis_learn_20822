package service.Impl;

import Jedis.util.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Impl.ProvinceDaoImpl;
import dao.ProvinceDao;
import domain.Province;
import redis.clients.jedis.Jedis;
import service.ProvinceService;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService{
//    声明dao
    private ProvinceDao dao =new ProvinceDaoImpl();

    @Override
    public List<Province> findALL() {
        return  dao.findALL();
    }
//使用Jedis缓存
    @Override
    public String findAllJson()  {
//        先从redis中查询数据
//        获得redis客户端链接
        Jedis jedis = JedisPoolUtils.getJedis();
//        获得数据
        String province = jedis.get("province");
//        判断数据是否为空
        if(province==null || province.length()==0){
//            redis中没有数据
            System.out.println("redis中没有数据");
//            从数据库中查询
            List<Province> all = dao.findALL();
//            将list转化为json
            ObjectMapper objectMapper = new ObjectMapper();
            String s = null;
            try {
                s = objectMapper.writeValueAsString(all);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
//            将jsons数据存入redis
            jedis.set("province",s);
//            归还链接
            jedis.close();
        }
        else{
            System.out.println("redis中有数据，查询缓存");
        }
        return province;
    }
}
