package com.dgut.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyUtil {
    private static  final  String DRIVER_NAME = "org.mariadb.jdbc.Driver";
    private static  final  String URL = "jdbc:mariadb://localhost:3306/sm";
    private static  final  String USER = "root";
    private static  final  String PASSWORD = "4674";

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if(conn == null){
                System.out.println("连接失败");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection con, PreparedStatement pstmt){
        try {
            if(pstmt!=null) {pstmt.close();}
            if(con!=null) {con.close();}
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
