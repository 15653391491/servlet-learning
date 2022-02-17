package com.mysql;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BaseSqlClass {
    protected String Driver;
    protected String Url;
    protected String User;
    protected String Password;
    protected String TableName;
    protected Statement stream;
    protected Connection connection;

    /**
     * 0@param driver   驱动
     *
     * @param url      地址
     * @param user     账号
     * @param password 密码
     */
    public BaseSqlClass(String driver, String url, String user, String password) {
        Driver = driver;
        Url = url;
        User = user;
        Password = password;
        TableName = "tb_account";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            stream = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 行数
     */
    public int getCount() {
        int count = 0;
        try {
            String sql = "select count(id) from " + TableName + ";";
            ResultSet re = stream.executeQuery(sql);
            re.next();
            count = re.getInt("count(id)");
            re.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询全集
     *
     * @return 查询结果
     */
    public Object[] selectAll() {
        Object[] con = new Object[getCount()];
        String sql = "select * from " + TableName + ";";
        try {
            ResultSet re = stream.executeQuery(sql);
            con = resultTraverse(re);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 查询集序列化
     *
     * @param re 查询集
     * @return 序列化结果
     */
    protected Object[] resultTraverse(ResultSet re) {
        int count = 0;
        try {
            re.last();
            count = re.getRow();
            re.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Object[] con = new Object[count];
        try {
            int index = 0;
            while (re.next()) {
                Map<String, Object> info = new HashMap<>();
                ResultSetMetaData rsm = re.getMetaData();
                int columnNum = rsm.getColumnCount();
                for (int i = 1; i <= columnNum; i++) {
                    String column = rsm.getColumnName(i);
                    String value = re.getString(column);
                    info.put(column, value);
                }
                con[index] = info;
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     *
     */
    public void close() {
        try {
            stream.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
