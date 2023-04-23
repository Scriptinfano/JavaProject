package JDBC;

import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

import java.sql.*;

/**
 * 初始化时连接上数据库，并作为软件和数据库的中介，执行sql语句
 *
 * @author Mingxiang
 */
public class DataBaseAgency {
    public static ScannerPlus scanner = new ScannerPlus();
    protected static String inputTip = "输入静态sql查询语句";
    protected Connection connection;
    protected Statement sqlStatement;
    private String passWard;
    private String user;
    private String databaseType;
    private String ipAddress;
    private String port;
    private String databaseName;

    public static void main(String[] args) {
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

    public void initialize() throws SQLException {
        System.out.println("是否以测试模式登录？输入(0或1)：");
        login(scanner.nextSelectionByInt(0, 1) == 1);
        enroll();
        initializeConnection();
        initializeStatement();
    }

    /**
     * 输入sql查询语言，输出查询结果
     *
     * @param sql sql
     * @throws SQLException sqlexception异常
     */
    public void outputQuery(String sql) throws SQLException {
        if (sqlStatement == null) {
            sqlStatement = connection.prepareStatement(sql);
            //TODO 为何此时sqlStatement无法执行setObject
        }
        ResultSet set = sqlStatement.executeQuery(sql);
        ResultSetMetaData metaData = set.getMetaData();
        int columnSize = metaData.getColumnCount();
        int[] types = new int[columnSize];
        for (int i = 0; i < columnSize; i++) {
            types[i] = metaData.getColumnType(i + 1);
        }
        boolean hasData = false;
        while (set.next()) {
            hasData = true;
            //注意数据库中列的序号是从1开始的
            for (int i = 1; i <= columnSize; i++) {
                int type = types[i - 1];
                if (type == Types.VARCHAR || type == Types.NVARCHAR || type == Types.CHAR)
                    System.out.print(set.getString(i) + " ");
                else if (type == Types.DOUBLE || type == Types.FLOAT)
                    System.out.print(set.getDouble(i) + " ");
                else if (type == Types.INTEGER)
                    System.out.print(set.getInt(i) + " ");
                else if (type == Types.DECIMAL)
                    System.out.print(set.getBigDecimal(i) + " ");
                else if (type == Types.NULL)
                    System.out.print("NULL");
                else
                    throw new SQLException("读取到了未经辨识的数据类型列");
            }

            System.out.println();
        }
        if (!hasData) System.out.println("查询出的数据表中没有数据");
    }

    public void run() {
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
                outputQuery(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                continue;
            }
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                break;
            }

        }
    }
}

class DataBaseAgency_PreparedStatement extends DataBaseAgency {
    static {
        inputTip = "请输入动态查询的sql语言（其中动态值的部分可以是?）";
    }

    public static void main(String[] args) {
        DataBaseAgency_PreparedStatement agency = new DataBaseAgency_PreparedStatement();
        agency.run();
    }

    /**
     * 初始化PreparedStatement对象，该对象是Statement对象的子对象，有效解决了注入攻击问题
     */
    @Override
    protected void initializeStatement() throws SQLException {
        //跳过提前初始化Statement对象的步骤，因为要使用PreparedStatement必须要传入sql语句
    }
}
