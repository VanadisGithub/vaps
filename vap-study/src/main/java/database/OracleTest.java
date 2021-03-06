package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-05-25 16:58
 */
public class OracleTest {

    public static void main(String[] args) throws Exception {
        System.setProperty("user.timezone", "GMT +0");
        Class.forName("org.apache.kylin.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:kylin://172.16.100.242:7070/1001_21", "ADMIN",
            "KYLIN");

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM OFFICE_PRI.TB_TIME WHERE DT > ?";
        statement = connection.prepareStatement(sql);
        statement.setObject(1, Date.valueOf("2020-05-21"));
        //        statement.setObject(2, Date.valueOf("2020-05-21"));
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            for (int i = 1; i <= 3; ++i) {
                System.out.print(resultSet.getObject(i) + " ");
            }
            System.out.println();
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
