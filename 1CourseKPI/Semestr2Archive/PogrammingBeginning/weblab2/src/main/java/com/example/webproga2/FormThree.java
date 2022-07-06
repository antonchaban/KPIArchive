package com.example.webproga2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.*;

@WebServlet("/form3")
public class FormThree extends HttpServlet {

    private double getResult(double a, double b, double c, double d)
    {
        return (((exp(c)+2*Math.log(a))/sqrt(pow(c, b)))*abs(asin(d)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String a3Val = req.getParameter("a3Val");
        String b3Val = req.getParameter("b3Val");
        String c3Val = req.getParameter("c3Val");
        String d3Val = req.getParameter("d3Val");

        try(PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            try {
                double a = Double.parseDouble(a3Val);
                double b = Double.parseDouble(b3Val);
                double c = Double.parseDouble(c3Val);
                double d = Double.parseDouble(d3Val);

                double result = getResult(a, b, c, d);
                if (Double.isNaN(result))
                    throw new IllegalArgumentException();
                if (Double.isInfinite(result))
                    throw new ArithmeticException();

                out.println("<img src=\"pic3.png\" alt=\"formula 3\"/>");
                out.println("<h2>Answer for a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " :</h2>");
                out.println("<h3>" + result + "</h3></br>");
                final int cookiesLen = 4;

                Cookie[] eqData = new Cookie[cookiesLen];

                eqData[0] = new Cookie("a3Val",a3Val);
                eqData[1] = new Cookie("b3Val",b3Val);
                eqData[2] = new Cookie("c3Val",c3Val);
                eqData[3] = new Cookie("d3Val",d3Val);
                for (int i = 0; i < cookiesLen; i++)
                {
                    //2 Days
                    eqData[i].setMaxAge(172800);

                    resp.addCookie(eqData[i]);
                }
            }
            catch (NumberFormatException e) {
                out.println("<h1>Error! Received data is not a number!</h1>");
            }
            catch (IllegalArgumentException e){
                out.println("<h1>Error! Result is not a number!</h1>");
            }
            catch (ArithmeticException e) {
                out.println("<h1>Error! Division by zero!</h1>");
            }
            finally {
                out.println("<a href=\"page3.jsp\"><h2>Back</h2></a>");
                out.println("<a href=\"index.html\"><h2>Home</h2></a>");
                out.println("</body></html>");
            }
        }
    }
}