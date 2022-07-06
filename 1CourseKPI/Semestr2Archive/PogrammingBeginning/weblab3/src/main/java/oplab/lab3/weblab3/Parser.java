package oplab.lab3.weblab3;

public class Parser {
   private double result;
    public Double doParse(String aVal, String bVal, String cVal, String dVal, String formVal) {
        try {
            double a = Double.parseDouble(aVal);
            double b = Double.parseDouble(bVal);
            double c = Double.parseDouble(cVal);
            double d = Double.parseDouble(dVal);

            if (formVal.equals("form3"))
            {
                CalcFormThree calc3 = new CalcFormThree();
                result = calc3.calculate(a, b, c, d);
                if (Double.isNaN(result))
                    throw new IllegalArgumentException();
                if (Double.isInfinite(result))
                    throw new ArithmeticException();
            }
            if (formVal.equals("form2"))
            {
                CalcFormTwo calc2 = new CalcFormTwo();
                result = calc2.calculate(a, b, c, d);
                if (Double.isNaN(result))
                    throw new IllegalArgumentException();
                if (Double.isInfinite(result))
                    throw new ArithmeticException();
            }
            if (formVal.equals("form1"))
            {
                CalcFormOne calc1 = new CalcFormOne();
                result = calc1.calculate(a, b, c, d);
                if (Double.isNaN(result))
                    throw new IllegalArgumentException();
                if (Double.isInfinite(result))
                    throw new ArithmeticException();
            }


        } catch (ArithmeticException | IllegalArgumentException e) {
           return null;
        }
        return result;
    }
}
