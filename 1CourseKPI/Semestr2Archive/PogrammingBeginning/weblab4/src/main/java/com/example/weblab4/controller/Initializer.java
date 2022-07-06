package com.example.weblab4.controller;

import com.example.weblab4.data.DataSource;
import com.example.weblab4.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Initializer", value = "/Initializer")
public class Initializer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String val = request.getParameter("task");
        if (val.equals("task1")) {
            response.sendRedirect("/TaskOne");
        }
        if (val.equals("task2")) {
            response.sendRedirect("/TaskTwo");
        }
        if (val.equals("task3")) {
            response.sendRedirect("/TaskThree");
        }
    }
}
