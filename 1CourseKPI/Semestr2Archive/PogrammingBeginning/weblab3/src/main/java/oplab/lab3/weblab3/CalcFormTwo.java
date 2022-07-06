package oplab.lab3.weblab3;
import static java.lang.Math.*;

public class CalcFormTwo implements CalcResult{
    @Override
    public double calculate(double a, double b, double c, double d) {
        return (2*(sqrt(tan(abs(a+c))))+(Math.log(b)/pow(c,d)));
    }
}
