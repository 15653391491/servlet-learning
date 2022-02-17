package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlTest {
    private static final String user = "root";
    private static final String password = "mysql";
    private static final String url = "jdbc:mysql://124.70.44.128:3306/account";

    MysqlTest() {

    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stream = con.createStatement();
            String sql = "select * from tb_account;";
//            ResultSet re = stream.executeQuery(sql);
            String countSql = "select count(id) from tb_account";
            ResultSet count = stream.executeQuery(countSql);
            count.next();
            System.out.println(count.getString("count(id)"));
//            System.out.println(re.getRow());
//            while (re.next()) {
//                String account = re.getString("account");
//                String userpd = re.getString("password1");
//                System.out.println("账号: " + account);
//                System.out.println("密码: " + userpd);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
