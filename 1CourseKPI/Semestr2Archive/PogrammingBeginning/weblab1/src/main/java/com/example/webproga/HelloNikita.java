package com.example.webproga;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloNikita", value = "/hello-nick")
public class HelloNikita extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Hello Nikita</h1>");
            out.println("<img src=\"https://i.pinimg.com/originals/c1/8a/2c/c18a2c7a37ee1f2c618a63bb1186909f.gif\" alt=\"hello\" width=\"150\" height=\"150\">");
            out.println("<br/><a href=\"index.html\">HOME</a>");
            out.println("<br/><a href=\"https://www.instagram.com/drugtvoyvrag/\" target=\"_blank\">INSTAGRAM</a>");
            out.println("</body></html>");
        }
    }

}
