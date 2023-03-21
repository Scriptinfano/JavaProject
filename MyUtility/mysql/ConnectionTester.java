package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTester {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String password = "200329";

    public static void main(String[] args) {
        Connection con;
        try {
            Class.forName(driver);//driver是一个字符串，代表JDBC驱动类的地址
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
                if (con.isValid(10)) {
                    Statement stat = con.createStatement();
                    String sql = "use test;create table count(num int);";
                    stat.execute(sql);
                    stat.close();

                    stat.close();

                }

                con.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");

        } catch (SQLException e) {
            System.out.println("数据库连接失败");
        }
    }
}
