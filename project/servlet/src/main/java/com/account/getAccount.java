package com.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mybatis.mapper.UserDao;
import mybatis.pojo.User;
import mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class getAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        ------ 接收 ------
        Enumeration<String> params = req.getParameterNames();
        int page = Integer.parseInt(req.getParameter("page"));
        int limit = Integer.parseInt(req.getParameter("limit"));
        //        ------ 处理 ------
        SqlSession session = MybatisUtils.getSqlSession();
        UserDao userMapper = session.getMapper(UserDao.class);
        HashMap<String, Object> user = new HashMap<>();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            user.put(name, req.getParameter(name));
        }
        List<User> users = userMapper.selectUserByAccount(user);
        int count = users.size();
        int endIndex = count > page * limit + limit ? page * limit + limit : count;
        users =
        if (page * limit + limit <= count) {
            users = users.subList(page * limit, page * limit + limit); // 分页
        } else {

        }

        session.close();
        //        ------ 返回 ------
        HashMap<String, Object> result = new HashMap<>();
        result.put("data", users);
        result.put("count", count);
        result.put("page", page);
        result.put("limit", limit);
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("data", result);
        ret.put("code", 1);
        ret.put("message", "success");
        resp.setContentType("text/json; charset=utf-8");
        JSONObject content = new JSONObject(ret);
        PrintWriter writer = resp.getWriter();
        writer.println(content);
    }
}
