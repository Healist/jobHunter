package com.healist.jobhunter.dbhelper;

import java.sql.*;
import java.util.List;

/**
 * Created by Healist on 2016/11/6.
 */
public class DBHelper  {
    public static final String driver_class = "com.mysql.jdbc.Driver";
    public static final String driver_url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8";
    public static final String user = "root";
    public static final String password = "zd8273061";

    private static Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rst = null;

    public DBHelper(){
        try {
            conn = DBHelper.getConnInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static synchronized Connection getConnInstance() {
        if(conn == null){
            try {
                Class.forName(driver_class);
                conn = DriverManager.getConnection(driver_url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("连接数据库成功");
        }
        return conn;
    }

    public void close() {
        try {
            if (conn != null) {
                DBHelper.conn.close();
            }
            if (pst != null) {
                this.pst.close();
            }
            if (rst != null) {
                this.rst.close();
            }
            System.out.println("关闭数据库成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeQuery(String sql, List<String> sqlValues) {
        int col = 0;
        try {
            pst = conn.prepareStatement(sql);
            if (sqlValues != null && sqlValues.size() > 0) {
                setSqlValues(pst, sqlValues);
            }
            rst = pst.executeQuery();
            if(rst.next()) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col;
    }

    public int executeUpdate(String sql, List<String> sqlValues) {
        int result = -1;
        try {
            pst = conn.prepareStatement(sql);
            if (sqlValues != null && sqlValues.size() > 0) {
                setSqlValues(pst, sqlValues);
            }
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void setSqlValues(PreparedStatement pst, List<String> sqlValues) {
        for (int i = 0; i < sqlValues.size(); i++) {
            try {
                pst.setObject(i + 1, sqlValues.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
