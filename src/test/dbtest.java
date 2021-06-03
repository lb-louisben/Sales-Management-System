package test;

import dbase.Login.DbaseConnect;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*-
 * @program: Sales-Management-System
 *
 * @description: test sb
 *
 * @author: LOUIS
 *
 * @creat: 2021-06-03-23:38
 * */
public class dbtest {

    @Test
    public void testDatabase() {
        Statement stmt;
        Connection conn = DbaseConnect.getConn();
        System.out.println(" 实例化Statement对象...");

        try {
            stmt = conn.createStatement();
            String sql = "SELECT id,name,password FROM dbase.usr ";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String password = rs.getString("password");

                System.out.println("id: " + id);
                System.out.println("name: " + name);
                System.out.println("password: " + password);
                System.out.println("");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
