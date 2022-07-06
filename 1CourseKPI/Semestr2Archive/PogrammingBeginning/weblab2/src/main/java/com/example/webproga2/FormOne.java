package com.example.webproga2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.*;

@WebServlet("/form1")
public class FormOne extends HttpServlet {

    private double getResult(double a, double b, double c, double d)
    {
        return (2*(sin(a)/acos(-2*b))-sqrt(Math.log(c*abs(2*d))));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String a1Val = req.getParameter("a1Val");
        String b1Val = req.getParameter("b1Val");
        String c1Val = req.getParameter("c1Val");
        String d1Val = req.getParameter("d1Val");

        try(PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            try {
                double a = Double.parseDouble(a1Val);
                double b = Double.parseDouble(b1Val);
                double c = Double.parseDouble(c1Val);
                double d = Double.parseDouble(d1Val);
                double result = getResult(a, b, c, d);
                if (Double.isNaN(result))
                    throw new IllegalArgumentException();
                if (Double.isInfinite(result))
                    throw new ArithmeticException();

                out.println("<img src=\"pic1.png\" alt=\"formula 1\"/>");
                out.println("<h2>Answer for a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " :</h2>");
                out.println("<h3>" + result + "</h3></br>");
                final int cookiesLen = 4;
                Cookie[] eqData = new Cookie[cookiesLen];

                eqData[0] = new Cookie("a1Val",a1Val);
                eqData[1] = new Cookie("b1Val",b1Val);
                eqData[2] = new Cookie("c1Val",c1Val);
                eqData[3] = new Cookie("d1Val",d1Val);
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
                out.println("<a href=\"page1.jsp\"><h2>Back</h2></a>");
                out.println("<a href=\"index.html\"><h2>Home</h2></a>");
                out.println("</body></html>");
            }
        }
        }
    }