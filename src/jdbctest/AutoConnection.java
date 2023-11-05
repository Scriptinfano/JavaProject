package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AutoConnection {
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    private Connection con;

    public AutoConnection(String url, String user, String password) {
        try {
            Class.forName(driver);//类加载器加载mysql对于JDBC接口的实现类，注册驱动
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*String sql = "use university;";
        Statement sta = con.createStatement();
        sta.executeUpdate(sql);
        String quary = "select SNo from s;";
        ResultSet set = sta.executeQuery(quary);
        sta.close();
        con.close();*/
    }

    public Statement getStatement() throws SQLException {
        return con.createStatement();
    }
}


class TestAutoStatement {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String passward = "200329";
        Statement statement = new AutoConnection(url, user, passward).getStatement();
        String sql = "use university;";
        statement.executeUpdate(sql);
        String quary = "select SNo from s;";
        statement.executeQuery(quary);
    }
}