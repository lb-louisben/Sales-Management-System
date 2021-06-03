package dbase.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*-
 * @program: Sales-Management-System
 *
 * @description: connect with mysql
 *
 * @author: LOUIS
 *
 * @creat: 2021-06-02-15:26
 * */

public class DbaseConnect {
    //    public static Connection conn;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dbase";//这里是连接User数据库哦
    static final String USER = "root";//用户
    static final String PASSWORD = "Hwb..//0987";//密码

    public static Connection getConn() {
        Connection conn;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
}
