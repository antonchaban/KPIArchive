package com.example.webproga2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.*;

@WebServlet("/form2")
public class FormTwo extends HttpServlet {

    private double getResult(double a, double b, double c, double d)
    {
        return (2*(sqrt(tan(abs(a+c))))+(Math.log(b)/pow(c,d)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String a2Val = req.getParameter("a2Val");
        String b2Val = req.getParameter("b2Val");
        String c2Val = req.getParameter("c2Val");
        String d2Val = req.getParameter("d2Val");

        try(PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            try {
                double a = Double.parseDouble(a2Val);
                double b = Double.parseDouble(b2Val);
                double c = Double.parseDouble(c2Val);
                double d = Double.parseDouble(d2Val);
                double result = getResult(a, b, c, d);
                if (Double.isNaN(result))
                    throw new IllegalArgumentException();
                if (Double.isInfinite(result))
                    throw new ArithmeticException();

                out.println("<img src=\"pic2.png\" alt=\"formula 2\"/>");
                out.println("<h2>Answer for a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " :</h2>");
                out.println("<h3>" + result + "</h3></br>");
                final int cookiesLen = 4;

                Cookie[] eqData = new Cookie[cookiesLen];

                eqData[0] = new Cookie("a2Val",a2Val);
                eqData[1] = new Cookie("b2Val",b2Val);
                eqData[2] = new Cookie("c2Val",c2Val);
                eqData[3] = new Cookie("d2Val",d2Val);
                for (int i = 0; i < cookiesLen; i++)
                {
                    //2 Days
                    eqData[i].setMaxAge(172800);

                    resp.addCookie(eqData[i]);
                }
            } catch (NumberFormatException e) {
                out.println("<h1>Error! Received data is not a number!</h1>");
            }
            catch (IllegalArgumentException e){
                out.println("<h1>Error! Result is not a number!</h1>");
            }
            catch (ArithmeticException e) {
                out.println("<h1>Error! Division by zero!</h1>");
            }
            finally {
                out.println("<a href=\"page2.jsp\"><h2>Back</h2></a>");
                out.println("<a href=\"index.html\"><h2>Home</h2></a>");
                out.println("</body></html>");
            }
        }
    }
}