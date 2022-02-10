import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            System.out.println(req.getParameter(name));
        }
        PrintWriter writer = resp.getWriter();
        writer.println("1111222211");
    }
}
