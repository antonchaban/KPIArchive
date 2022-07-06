package oplab.lab3.weblab3;

import static java.lang.Math.*;

public class CalcFormThree implements CalcResult{
    @Override
    public double calculate(double a, double b, double c, double d) {
        return (((exp(c)+2*Math.log(a))/sqrt(pow(c, b)))*abs(asin(d)));
    }
}
