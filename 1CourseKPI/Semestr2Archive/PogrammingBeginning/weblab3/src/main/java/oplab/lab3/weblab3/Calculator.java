package oplab.lab3.weblab3;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/calc")
public class Calculator extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String aVal = req.getParameter("aVal");
        String bVal = req.getParameter("bVal");
        String cVal = req.getParameter("cVal");
        String dVal = req.getParameter("dVal");
        String formVal = req.getParameter("formula");
        Double result = null;
        Parser parser = new Parser();

        if (formVal.equals("form3")) {
            result = parser.doParse(aVal, bVal, cVal, dVal, formVal);
        }
        if (formVal.equals("form2")) {
            result = parser.doParse(aVal, bVal, cVal, dVal, formVal);
        }
        if (formVal.equals("form1")) {
            result = parser.doParse(aVal, bVal, cVal, dVal, formVal);
        }

        if (result != null) {
            final int cookiesLen = 4;
            Cookie[] eqData = new Cookie[cookiesLen];
            eqData[0] = new Cookie("aVal", aVal);
            eqData[1] = new Cookie("bVal", bVal);
            eqData[2] = new Cookie("cVal", cVal);
            eqData[3] = new Cookie("dVal", dVal);
            for (int i = 0; i < cookiesLen; i++) {
                //2 Days
                eqData[i].setMaxAge(172800);

                resp.addCookie(eqData[i]);
            }
        }
        HttpSession session = req.getSession();
        ArrayList<Map<String, String>> ps = (ArrayList<Map<String, String>>) session.getAttribute("parameters");
        Map<String, String> eqSesData = new LinkedHashMap<>();
        eqSesData.put("aVal", req.getParameter("aVal"));
        eqSesData.put("bVal", req.getParameter("bVal"));
        eqSesData.put("cVal", req.getParameter("cVal"));
        eqSesData.put("dVal", req.getParameter("dVal"));
        eqSesData.put("formula", req.getParameter("formula"));
        eqSesData.put("result", String.valueOf(result));

        if (ps != null)
            ps.add(eqSesData);
        else {
            ArrayList<Map<String, String>> sesParams = new ArrayList<>();

            sesParams.add(eqSesData);

            session.setAttribute("parameters", sesParams);
        }
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

}