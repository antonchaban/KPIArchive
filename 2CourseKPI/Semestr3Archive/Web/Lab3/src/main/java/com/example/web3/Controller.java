package com.example.web3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

@WebServlet(name = "Controller", value = "/controller/*")
public class Controller extends HttpServlet {
    String str = "Default text to show";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/web3";
    private static final String ID = "root";
    private static final String PASS = "admin";
    User user = new User(false);
    Connection conn;
    Statement st;


    public Controller() throws SQLException {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String query = "select * from data where id = 1;";

        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(DB_URL, ID, PASS);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                boolean isEditor = rs.getBoolean("isEditor");
                str = rs.getString("textToShow");
                user.setEditor(isEditor);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        config.getServletContext().setAttribute("isOpened", false);
        config.getServletContext().setAttribute("user", user);
        config.getServletContext().setAttribute("textToShow", str);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
        try {
            switch (pathInfo) {
                case "/whoAmI":
                    whoAmI(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/newText":
                    newText(request, response);
                    break;
                case "/notBar":
                    notBar(request, response);
                    break;
                case "/":
                default:
                    viewer(request, response);
                    break;
            }
        } catch (RuntimeException | SQLException | ClassNotFoundException ex) {
            System.out.println("ERROR");
            System.out.println(ex.getMessage());
        }

    }

    private void notBar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean status = (boolean) request.getServletContext().getAttribute("isOpened");
        if (status){
            request.getSession().setAttribute("isOpened", false);
            request.getServletContext().setAttribute("isOpened", false);
            response.sendRedirect(".");
        }
        else {
            request.getSession().setAttribute("isOpened", true);
            request.getServletContext().setAttribute("isOpened", true);
            System.out.println(request.getSession().getAttribute("isOpened"));
            response.sendRedirect(".");
        }
    }

    private void newText(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String str = request.getParameter("notifText");
        PreparedStatement pstmt = conn.prepareStatement("UPDATE data SET textToShow = ? WHERE id = 1;");
        pstmt.setString(1, str);
        pstmt.executeUpdate();
        request.getSession().setAttribute("textToShow", str);
        request.getRequestDispatcher("/WEB-INF/jsp/editor.jsp").forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        user.setEditor(false);
        String query = "UPDATE data SET isEditor = false WHERE id = 1;";
        st.execute(query);
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(request, response);
    }

    private void whoAmI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String whoAmI = request.getParameter("action");
        String query = "UPDATE data SET isEditor = true WHERE id = 1;";
        if (Objects.equals(whoAmI, "As editor")){
            user.setEditor(true);
        }
        request.getSession().setAttribute("user", user);
        st.execute(query);
        request.getRequestDispatcher("/WEB-INF/jsp/editor.jsp").forward(request, response);
    }

    private void viewer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        if (!user.isEditor()){
            request.getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(request, response);
        }else request.getRequestDispatcher("/WEB-INF/jsp/editor.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
