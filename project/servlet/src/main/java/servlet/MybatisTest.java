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
import java.util.HashMap;
import java.util.List;

public class MybatisTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> result = new HashMap<>();
        SqlSession session = MybatisUtils.getSqlSession();
        UserDao userMapper = session.getMapper(UserDao.class);
        List<User> users = userMapper.getUserList();
        result.put("data", users);
        session.close();
        //
        //        ------ 返回 ------
        resp.setContentType("text/json; charset=utf-8");
        JSONObject content = new JSONObject(result);
        PrintWriter writer = resp.getWriter();
        writer.println(content);
    }
}
