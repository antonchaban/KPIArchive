package oplab.lab3.weblab3;
import static java.lang.Math.*;

public class CalcFormOne implements CalcResult{
    @Override
    public double calculate(double a, double b, double c, double d) {
        return (2*(sin(a)/acos(-2*b))-sqrt(Math.log(c*abs(2*d))));
    }
}
