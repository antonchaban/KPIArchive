package com.example.weblab4.controller;

import com.example.weblab4.data.DataSource;
import com.example.weblab4.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TaskOne", value = "/TaskOne")
public class TaskOne extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("universities", DataSource.uniks);
        request.getRequestDispatcher("WEB-INF/jsp/Universities.jsp").forward(request,response);
    }
}
