package JDBC;

import com.alibaba.druid.pool.DruidDataSource;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化时连接上数据库，并作为软件和数据库的中介，执行sql语句
 *
 * @author Mingxiang
 */
public class DataBaseAgency {
    public static ScannerPlus scanner = new ScannerPlus();
    protected static String inputTip = "输入静态sql查询语句";//根据需求改变的提示语
    protected Connection connection;
    private Statement sqlStatement;
    private String passWard;
    private String user;
    private String databaseType;
    private String ipAddress;
    private String port;
    private String databaseName;

    public static void main(String[] args) throws SQLException {
        DataBaseAgency agency = new DataBaseAgency();
        agency.run();
    }

    /**
     * 登录
     * 录入用户的用户名和密码
     *
     * @param testMode 测试模式，若以测试模式登录，则会登录默认的测试数据库，否则登录用户指定的数据库
     */
    private void login(boolean testMode) {
        if (testMode) {
            user = "root";
            passWard = "200329";
            databaseType = "mysql";
            ipAddress = "localhost";
            port = "3306";
            databaseName = "university";
        } else {
            System.out.print("请输入账号：");
            user = scanner.nextLine();
            System.out.print("请输入密码：");
            passWard = scanner.nextLine();
            System.out.print("请输入数据库的类型（例如:mysql）");
            databaseType = scanner.nextLine();
            System.out.print("请输入数据库的ip地址：");
            ipAddress = scanner.nextLine();
            System.out.print("请输入数据库的端口号：");
            port = scanner.nextLine();
            System.out.print("请输入数据库的名字：");
            databaseName = scanner.nextLine();
        }

    }

    /**
     * 注册驱动
     */
    private void enroll() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得连接
     *
     * @throws SQLException sqlexception异常
     */
    private void initializeConnection() throws SQLException {
        String url = "jdbc:" + databaseType + "://" + ipAddress + ":" + port + "/" + databaseName;
        connection = DriverManager.getConnection(url, user, passWard);
        //也可以仅传入url参数，但url参数中要包括user和passward, 例如：jdbc:mysql://ip:port/name?user=root&passward=root
    }

    /**
     * 初始化Statement对象，这是用来发送sql语句到数据库的对象，并且会返回查询结果
     */
    protected void initializeStatement() throws SQLException {
        sqlStatement = connection.createStatement();
    }

    /**
     * 初始化，执行连接数据库的所有步骤，包括登录（输入账号密码）、注册驱动、获取连接、获取执行sql的Statement对象
     *
     * @throws SQLException sqlexception异常
     */
    public void initialize() throws SQLException {
        System.out.println("是否以测试模式登录？输入(0或1)：");
        login(scanner.nextSelectionByInt(0, 1) == 1);
        enroll();
        initializeConnection();
        initializeStatement();
    }

    /**
     * 用此时类中已经初始化好的statement对象执行sql查询语句，并输出查询结果
     *
     * @param sql sql
     * @throws SQLException sqlexception异常
     */
    public void executeSQL(String sql) throws SQLException {
        query(sql);
    }

    public void query(String sql) throws SQLException {
        ResultSet set = sqlStatement.executeQuery(sql);
        print(set);
    }

    public void insert(String sql) throws SQLException {

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

    public List<Map<Object, Object>> getTableData(ResultSet resultSet) throws SQLException {
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
    }

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


    public void run() throws SQLException {
        while (true) {
            try {
                initialize();
                System.out.println("数据库登录成功");
                break;
            } catch (SQLException e) {
                System.out.println("输入的数据库登录信息有误，请重新输入");
            }
        }
        while (true) {
            System.out.println(inputTip);
            String sql = scanner.nextLine();
            try {
                executeSQL(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                continue;
            }
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                close();//关闭连接和状态对象
                break;
            }

        }
    }

    protected void close() throws SQLException {
        sqlStatement.close();
        connection.close();
    }
}

class DataBaseAgency_PreparedStatement extends DataBaseAgency {
    static {
        inputTip = "请输入动态查询的sql语言（其中动态值的部分可以是?）";
    }

    public static void main(String[] args) throws SQLException {
        DataBaseAgency_PreparedStatement agency = new DataBaseAgency_PreparedStatement();
        agency.run();

    }

    /**
     * 初始化PreparedStatement对象，该对象是Statement对象的子对象，有效解决了注入攻击问题
     */
    @Override
    protected void initializeStatement() {
        //跳过提前初始化Statement对象的步骤，因为要使用PreparedStatement必须要传入sql语句
    }

    /**
     * 用此时类中已经初始化好的statement对象执行sql查询语句，并输出查询结果
     *
     * @param sql sql
     * @throws SQLException sqlexception异常
     */
    @Override
    public void executeSQL(String sql) throws SQLException {
        insert(sql);

    }

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

class TestDruidDataSources {
    public static void main(String[] args) throws SQLException {
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUsername("root");
        source.setPassword("200329");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setInitialSize(5);//初始化连接数量
        source.setMaxActive(5);//最大数量
        Connection connection = source.getConnection();
        connection.close();//这一步不是关闭连接，而是回收连接，将连接对象放回连接池

    }
}