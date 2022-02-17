package servlet;

import java.io.PrintWriter;
import java.lang.ClassNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import com.mysql.BaseSqlClass;

public class SqlTest extends HttpServlet {

    protected BaseSqlClass bs;

    @Override
    public void init() throws ServletException {
        String driver = getInitParameter("jdbcDriver");
        String url = getInitParameter("url");
        String user = getInitParameter("user");
        String password = getInitParameter("password");
        bs = new BaseSqlClass(driver, url, user, password);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        ------ 处理 ------
        Map<String, Object> result = new HashMap<>();
        result.put("data", bs.selectAll());
        result.put("count", bs.getCount());
        //        ------ 返回 ------
        resp.setContentType("text/json; charset=utf-8");
        JSONObject content = new JSONObject(result);
        PrintWriter writer = resp.getWriter();
        writer.println(content);
    }

    @Override
    public void destroy() {
        bs.close();
    }
}
