package com.example.webproga;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloEmad", value = "/hello-emad")
public class HelloEmad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Hello Emad</h1>");
            out.println("<img src=\"https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/0694e716-558d-4dfe-84e2-b29168b0aea4/d5tkj5l-921c3e8c-f4f3-415f-89a2-798c38572972.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvMDY5NGU3MTYtNTU4ZC00ZGZlLTg0ZTItYjI5MTY4YjBhZWE0XC9kNXRrajVsLTkyMWMzZThjLWY0ZjMtNDE1Zi04OWEyLTc5OGMzODU3Mjk3Mi5wbmcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.JhKJX7vKrDra15-WUsFOYn94S5Urs6V4lLOIttK95H4\" alt=\"hello\" width=\"150\" height=\"150\">");
            out.println("<br/><a href=\"index.html\">HOME</a>");
            out.println("<br/><a href=\"https://www.instagram.com/emad_hamad171/\" target=\"_blank\">INSTAGRAM</a>");
            out.println("</body></html>");
        }
    }

}
