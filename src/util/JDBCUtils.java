package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;
    static {
//        创建properties对象
        Properties properties = new Properties();
//        寻找路径
        InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
//        加载文件
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        初始化链接池对象
        try {
            ds= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    获得连接池对象
    public static DataSource getDataSource(){
        return  ds;
    }
//    获得connection对象
    public static Connection getConnection2() throws SQLException {
        return ds.getConnection();
    }
}
