package com;

import mybatis.pojo.User;
import mybatis.utils.MybatisUtils;
import mybatis.mapper.UserDao;

import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

public class MysqlTest {
    public static void main(String[] args) {
        SqlSession session = MybatisUtils.getSqlSession();
        UserDao userMapper = session.getMapper(UserDao.class);
        try {
            List<User> users = userMapper.getUserList();
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
