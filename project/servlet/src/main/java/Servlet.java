import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ------------- 接收 -------------------
        Enumeration<String> names = req.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            params.put(name, req.getParameter(name));
        }
//        ------------ 验证 ----------------
//        ------------ 处理 ----------------
        Map<String, Object> con = new HashMap<>();
        con.put("data", params);
        con.put("code", 1);
        con.put("msg", "ok");
//        ------------ 返回 ----------------
        resp.setContentType("text/json; charset=utf-8");
        JSONObject content = new JSONObject(con);
        PrintWriter writer = resp.getWriter();
        writer.println(content);
    }
}
