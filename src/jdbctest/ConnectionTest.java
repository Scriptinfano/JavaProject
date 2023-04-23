package jdbctest;

import java.sql.*;

public class ConnectionTest {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String password = "200329";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName(driver);//类加载器加载mysql对于JDBC接口的实现类，注册驱动
        Connection con = DriverManager.getConnection(url, user, password);
        String sql = "use university;";
        Statement sta = con.createStatement();
        sta.executeUpdate(sql);
        String quary = "select SNo from s;";
        ResultSet set = sta.executeQuery(quary);
        sta.close();
        con.close();
    }
}