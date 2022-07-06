package com.example.webproga;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloAntoha", value = "/hello-antoha")
public class HelloAntoha extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Hello Antoha</h1>");
            out.println("<img src=\"https://i.pinimg.com/originals/dc/0e/67/dc0e6750a6bf4c9af0fd7a2356666601.gif\" alt=\"hello\" width=\"150\" height=\"150\">");
            out.println("<br/><a href=\"index.html\">HOME</a>");
            out.println("<br/><a href=\"https://www.instagram.com/antoha.koala/\" target=\"_blank\">INSTAGRAM</a>");
            out.println("</body></html>");
        }
    }
}