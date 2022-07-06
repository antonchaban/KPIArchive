package com.example.weblab4.controller;

import com.example.weblab4.data.DataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TaskThree", value = "/TaskThree")
public class TaskThree extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students", DataSource.allStudents());
        request.getRequestDispatcher("WEB-INF/jsp/students.jsp").forward(request,response);
    }

}
