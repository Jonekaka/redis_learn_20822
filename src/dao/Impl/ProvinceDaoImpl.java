package dao.Impl;

import dao.ProvinceDao;
import domain.Province;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {


    @Override

    public List<Province> findALL() {
        //    声明成员变量
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
//        定义sql
        String sql="select * from province ";
//        执行sql
        List<Province> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        System.out.println(query);
        return query;
    }
    @Test
    public void findALL1() {
        //    声明成员变量
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
//        定义sql
        String sql="select * from province ";
//        执行sql
        List<Province> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        System.out.println(query);
    }
    @Test
    public  void aaa(){
        System.out.println("aaa");
    }

}
