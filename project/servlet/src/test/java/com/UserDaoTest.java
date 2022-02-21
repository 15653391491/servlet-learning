package com;

import mybatis.mapper.UserDao;
import mybatis.pojo.User;
import mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserDaoTest {
    @Test
    public void test() {
        SqlSession session = MybatisUtils.getSqlSession();
        UserDao account = session.getMapper(UserDao.class);
        HashMap<String, Object> params = new HashMap<>();
//        params.put("account", "gybond1995@foxmail.com");
//        params.put("platform","CODING");
        List<User> result = account.selectUserByAccount(params);
        result = result.subList(0,2);
//        List<User> result = account.getUserList();
        for (User user : result) {
            System.out.println(user);
        }
    }
}
