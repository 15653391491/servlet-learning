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

public class SqlTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String driver = getInitParameter("jdbcDriver");
        String url = getInitParameter("url");
        String user = getInitParameter("user");
        String password = getInitParameter("password");
        Map<String, Object> result = new HashMap<>();
        try {
            //            --- 链接mysql ---
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stream = con.createStatement();
            //            --- 计数 ---
            String countSql = "select count(id) from tb_account";
            ResultSet countRe = stream.executeQuery(countSql);
            countRe.next();
            int count = countRe.getInt("count(id)");
            Object[] accountList = new Object[count];
            //            --- 查询 ---
            String sql = "select * from tb_account;";
            ResultSet re = stream.executeQuery(sql);
            int i = 0;
            while (re.next()) {
                String account = re.getString("account");
                String userpd = re.getString("password1");
                Map<String, String> info = new HashMap<>();
                info.put("account", account);
                info.put("password", userpd);
                System.out.println(info);
                accountList[i] = info;
                i++;
            }
            result.put("data", accountList);
            result.put("count", count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/json; charset=utf-8");
        JSONObject content = new JSONObject(result);
        PrintWriter writer = resp.getWriter();
        writer.println(content);
    }
}
