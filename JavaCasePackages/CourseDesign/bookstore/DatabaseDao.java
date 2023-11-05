package CourseDesign.bookstore;

import java.sql.*;
import java.util.ArrayList;

/**
 * Dao层，负责上层应用和数据库的数据交换
 *
 * @author Mingxiang
 */
public class DatabaseDao {

    private final Connection connection;
    private final Statement sqlStatement;

    private ArrayList<CallableStatement> callableStatements;

    public DatabaseDao(String passWard,
                       String user,
                       String ipAddress,
                       String port,
                       String databaseName) throws SQLException {
        //注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //获取连接
        String url = "jdbc:mysql://" + ipAddress + ":" + port + "/" + databaseName;
        connection = DriverManager.getConnection(url, user, passWard);
        //也可以仅传入url参数，但url参数中要包括user和passward, 例如：jdbc:mysql://ip:port/name?user=root&passward=root

        //获取执行sql语句的对象
        sqlStatement = connection.createStatement();

        //初始化存储过程执行对象池
        callableStatements.add(connection.prepareCall("{call input_book(?,?,?,?)}"));
        callableStatements.add(connection.prepareCall("{call input_merchant(?,?)}"));
        callableStatements.add(connection.prepareCall("{call register_user(?,?)}"));

    }

    /**
     * 执行查询语句并输出查询结果
     *
     * @param sql sql
     * @throws SQLException sqlexception异常
     */
    public ResultSet query(String sql) throws SQLException {
        return sqlStatement.executeQuery(sql);

    }


    public void update(String sql) throws SQLException {
        sqlStatement.executeUpdate(sql);
    }


}
