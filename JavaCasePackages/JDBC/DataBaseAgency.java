package JDBC;

import java.sql.*;
import java.util.Properties;

/**
 * 初始化时连接上数据库，并作为软件和数据库的中介，执行sql语句
 *
 * @author Mingxiang
 */
public class DataBaseAgency {

    protected Connection connection;
    private final Statement sqlStatement;

    public DataBaseAgency(String passWard,
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

    /**
     * 传入数据表游标ResultSet，打印所有数据
     *
     * @param resultSet 结果集，也称游标，类似于迭代器
     * @throws SQLException sqlexception异常
     */
    protected void print(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnSize = metaData.getColumnCount();
        int[] types = new int[columnSize];
        for (int i = 0; i < columnSize; i++) {
            types[i] = metaData.getColumnType(i + 1);
        }
        boolean hasData = false;
        while (resultSet.next()) {
            hasData = true;
            //注意数据库中列的序号是从1开始的
            for (int i = 1; i <= columnSize; i++) {
                int type = types[i - 1];
                if (type == Types.VARCHAR || type == Types.NVARCHAR || type == Types.CHAR)
                    System.out.print(resultSet.getString(i) + " ");
                else if (type == Types.DOUBLE || type == Types.FLOAT)
                    System.out.print(resultSet.getDouble(i) + " ");
                else if (type == Types.INTEGER)
                    System.out.print(resultSet.getInt(i) + " ");
                else if (type == Types.DECIMAL)
                    System.out.print(resultSet.getBigDecimal(i) + " ");
                else if (type == Types.NULL)
                    System.out.print("NULL");
                else
                    throw new SQLException("读取到了未经辨识的数据类型列");
            }
            System.out.println();
        }
        if (!hasData) System.out.println("查询出的数据表中没有数据");
    }

    /*public List<Map<Object, Object>> getTableData(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnSize = metaData.getColumnCount();
        List<Map<Object, Object>> theList = new ArrayList<>();
        while (resultSet.next()) {
            Map<Object, Object> map = new HashMap<>();
            for (int i = 1; i <= columnSize; i++) {
                //建议使用getColumnLabel接口，可以解决列的别名问题
                map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }
            theList.add(map);
        }
        return theList;
    }*/

    /**
     * 在JDBC中如何创建数据库事务
     * 1、连接对象调用setAutoCommit(false)接口关闭自动提交
     * 2、用try-catch块包裹事务中要执行的代码
     * 3、在catch块中，connection对象调用rollback操作以便在事务中的代码执行出错时回滚事务
     * 4、在try-catch中的try块的最后一行，connection调用接口commit接口提交事务
     *
     * @throws SQLException sqlexception异常
     */
    public void testEvent() throws SQLException {
        try {
            connection.setAutoCommit(false);
            //在事务中执行的操作
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();//一旦事务中有一句sql执行失败，则将整个数据库的状态回滚到执行事务之前的状态
        }
        connection.setAutoCommit(true);//重新打开事务
    }

    protected void showMenu() {
        System.out.println("1、更新数据库");
        System.out.println("2、查询数据库");
        System.out.println("3、退出程序");
    }


    public void run() throws SQLException {
        /*try {
            initialize();
            System.out.println("数据库登录成功");
        } catch (SQLException e) {
            System.out.println("输入的数据库登录信息有误，请重新输入");
        }

        while (true) {
            showMenu();
            int select = scanner.nextSelectionByInt(1, 3);
            String sql = scanner.nextLine();
            switch (select) {
                case 1 -> {
                    update(sql);
                    continue;
                }
                case 2 -> {
                    query(sql);
                    continue;
                }
                case 3 -> close();//关闭连接和状态对象
            }
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                break;
            }
        }*/
    }


    protected void close() throws SQLException {
        sqlStatement.close();
        connection.close();
    }
}

/*
class DataBaseAgency_PreparedStatement extends DataBaseAgency {


    public void insert(String insertSql) throws SQLException {
        PreparedStatement sqlStatement = connection.prepareStatement(insertSql);
        ArrayList<Integer> list = detectPlaceHolder(insertSql);
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (j == 0)
                    sqlStatement.setObject(j + 1, "id" + i);
                else sqlStatement.setObject(j + 1, "name" + i);
            }
            //sqlStatement.executeUpdate();
            sqlStatement.addBatch();//暂时不执行sql语句，仅将values暂存到缓冲区
        }
        sqlStatement.executeBatch();//统一执行sql

    }


    private ArrayList<Integer> detectPlaceHolder(String sql) {
        ArrayList<Integer> theList = new ArrayList<>();
        for (int i = 0; i < sql.length(); i++) {
            if (sql.charAt(i) == '?') {
                theList.add(i);
            }
        }
        return theList;
    }
}
*/

class TestDruidDataSources {
    public static void main(String[] args) throws SQLException {
        /*DruidDataSource source = new DruidDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUsername("root");
        source.setPassword("200329");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setInitialSize(5);//初始化连接数量
        source.setMaxActive(5);//最大数量
        Connection connection = source.getConnection();
        connection.close();//这一步不是关闭连接，而是回收连接，将连接对象放回连接池*/
        Properties pro = new Properties();
        //pro.load(ClassLoader.getSystemClassLoader().getResourceAsStream(""));
        //DataBaseAgency db = new DataBaseAgency();
        //db.run();

    }
}