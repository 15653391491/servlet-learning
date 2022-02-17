package com;

import java.sql.*;

import com.mysql.BaseSqlClass;

public class MysqlTest {
    private static final String user = "root";
    private static final String password = "mysql";
    private static final String url = "jdbc:mysql://124.70.44.128:3306/account";

    MysqlTest() {

    }

    public static void main(String[] args) {
        BaseSqlClass bs = new BaseSqlClass("com.mysql.cj.jdbc.Driver", url, user, password);
        System.out.println(bs.getCount());
    }
}
