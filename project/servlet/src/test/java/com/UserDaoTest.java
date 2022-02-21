package com;

import mybatis.mapper.UserDao;
import mybatis.pojo.User;
import mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void test() {
        //        获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //        执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        //        关闭sqlSession
        sqlSession.close();
    }
}
