package database;

import com.alibaba.fastjson.JSON;

import com.facebook.presto.jdbc.PrestoArray;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-05-25 10:53
 */
public class PrestoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.facebook.presto.jdbc.PrestoDriver");
        Connection connection = DriverManager.getConnection("jdbc:presto://172.16.8.64:8080/hive/tag_engine_test",
            "root", null);
        Statement stmt = connection.createStatement();
        //        String sql = "show schemas";
        //        String sql = "show tables";
        //        String sql = "select count(1) from dl_book";
        //        String sql = "select * from dl_book limit 10";
        String sql = "select * from dl_book where auth_name = '柏琛'";
        //        String sql = "select * from dl_book where id = '445856'";
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();

        while (rs.next()) {
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<>();
                //循环列
                for (int i = 1; i <= columnCount; i++) {
                    //通过序号获取列名,起始值为1
                    String columnName = metaData.getColumnName(i);
                    Object object = rs.getObject(i);
                    if (object instanceof Array) {
                        Object[] objArray = (Object[])((PrestoArray)object).getArray();
                        List<String> lists = new ArrayList<>();
                        for (Object o : objArray) {
                            lists.add(o.toString());
                        }
                        object = String.join(",", lists);
                    }
                    map.put(columnName, object);
                }
                System.out.println(JSON.toJSONString(map));
            }
        }
        rs.close();
        connection.close();
    }
}
