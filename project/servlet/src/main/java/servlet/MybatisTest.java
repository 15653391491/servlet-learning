package servlet;

import mybatis.mapper.UserDao;
import mybatis.pojo.User;
import mybatis.utils.MybatisUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class MybatisTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        ------ 接收 ------
        Enumeration<String> params = req.getParameterNames();
        //        ------ 处理 ------
        HashMap<String, Object> result = new HashMap<>();

        SqlSession session = MybatisUtils.getSqlSession();
        UserDao userMapper = session.getMapper(UserDao.class);
        List<User> users;
        if (params.hasMoreElements()) {
            HashMap<String, Object> user = new HashMap<>();
            user.put("account", req.getParameter("account"));
            user.put("platform", req.getParameter("platform"));
            users = userMapper.selectUserByAccount(user);
        } else {
            users = userMapper.getUserList();
        }
        result.put("data", users);
        session.close();
        //        ------ 返回 ------
        resp.setContentType("text/json; charset=utf-8");
        JSONObject content = new JSONObject(result);
        PrintWriter writer = resp.getWriter();
        writer.println(content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        ------ 接收 ------
        String account = req.getParameter("username");
        String pwd = req.getParameter("password1");
        //        ------ 处理 ------=
        SqlSession session = MybatisUtils.getSqlSession();
        UserDao userMapper = session.getMapper(UserDao.class);
        HashMap<String, Object> result = new HashMap<>();
        try {
            userMapper.addAccount(account, pwd);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "error");
            result.put("code", 0);
            resp.setContentType("text/json; charset=utf-8");
            JSONObject content = new JSONObject(result);
            PrintWriter writer = resp.getWriter();
            writer.println(content);
            return;
        }
        //        ------ 返回 ------
        result.put("message", "success");
        result.put("code", 1);
        resp.setContentType("text/json; charset=utf-8");
        JSONObject content = new JSONObject(result);
        PrintWriter writer = resp.getWriter();
        writer.println(content);
    }
}
