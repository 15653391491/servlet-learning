package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FilterTest implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println(config);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        ------------------ 接收 -------------------
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");
//        ------------------ 处理 -------------------
        if (token == null) { // token过期处理
            Map<String, Object> con = new HashMap<>();
            con.put("code", 0);
            con.put("msg", "token已过期");
            JSONObject content = new JSONObject(con);
            servletResponse.setContentType("text/json; charset=utf-8");
            PrintWriter writer = servletResponse.getWriter();
            writer.println(content);
        } else {
            request.setAttribute("token", token);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
