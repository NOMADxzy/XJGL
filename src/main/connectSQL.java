package main;

import java.sql.*;

public class connectSQL {
    private static Connection Conn = null;

    public static Connection getConnection() {

        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.连接
            Conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=EXP", "sa", "19991207Xzy");

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
        return Conn;

    }
}