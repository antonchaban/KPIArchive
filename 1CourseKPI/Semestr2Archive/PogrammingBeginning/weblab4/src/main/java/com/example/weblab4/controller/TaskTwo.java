package com.example.weblab4.controller;

import com.example.weblab4.data.DataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TaskTwo", value = "/TaskTwo")
public class TaskTwo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("faculties", DataSource.getUnivFaculties());
        request.getRequestDispatcher("WEB-INF/jsp/Faculties.jsp").forward(request,response);
    }
}
