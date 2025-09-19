package main;

import java.sql.*;

public class connectSQL {
    private static Connection Conn = null;

    public static Connection getConnection() {

        try {
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.连接 - 添加字符集参数解决中文乱码问题
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EXP?characterEncoding=UTF-8&serverTimezone=UTC", "root", "991030xzy");

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
        return Conn;

    }
}