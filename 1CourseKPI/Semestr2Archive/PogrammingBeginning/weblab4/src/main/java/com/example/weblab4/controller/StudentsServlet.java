package com.example.weblab4.controller;

import com.example.weblab4.data.DataSource;
import com.example.weblab4.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "StudentsServlet", value = "/StudentsServlet")
public class StudentsServlet extends HttpServlet {
    DataSource dataSource = new DataSource();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
       // ArrayList<Student> sesParams = dataSource.addStudents();
      //  session.setAttribute("parameters", sesParams);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(request, response);
    }


}
