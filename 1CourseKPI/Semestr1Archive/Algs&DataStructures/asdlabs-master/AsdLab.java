import java.util.Scanner; // подключим сканер

public class AsdLab {
    public static void main(String[] args){
        char[] arrayX = GetBinaryArray("X");
        char[] arrayY = GetBinaryArray("Y");

        long ResultX = BinaryArrayToLong(arrayX);
        long ResultY = BinaryArrayToLong(arrayY);

        System.out.println("X: " + ResultX);
        System.out.println("Y: " + ResultY);

        if (ResultX % ResultY == 0)
        {
            System.out.print("Ділиться БЕЗ остачі");
        }
        else
        {
            System.out.print("Ділиться З остачею");
        }

    }

    private static long Power(int x, int n)
    // создадим метод поднесения в степень при использовании рекурсии
    {
        if (n == 0) return 1;

        return x*Power(x, n-1);
    }

    private static char[] GetBinaryArray (String variableName)
    /* Создадим метод который будет принимать и проверять является ли введенное значение бинарным
    кодом, и если оно таковым является пустим его дальше
     */
    {
        boolean StringOK;
        Scanner line = new Scanner(System.in);
        char[] arrayS;
        do {
            System.out.print(variableName + " bin: ");
            String s = line.next();
            arrayS = s.toCharArray(); // переведем полученые строки в массив символов

            int i = 0; // зададим значения переменным внутри этого метода
            StringOK = true;
            while (i < arrayS.length && StringOK)
            /* массив будет проверяться пока не окажется что он верный, ну а если не верный то цикл
            прекратится и по второму заданому условию и нужно будет вновь ввести значения
             */
            {
                if (!(arrayS[i] == '0' || arrayS[i] == '1'))
                    StringOK = false;
                i++;
            }
        }
        while (!StringOK);

        return arrayS; // метод возвращает наш бинарный массив, и никакой другой
    }

    private static long BinaryArrayToLong(char[] arrayS)
    //метод перевода бинарного массива в десятичный
    {
        long ResultS = 0;
        int z =0;
        for (int i = arrayS.length-1;i >= 0; i--)
        // создадим цикл перевода в десятичную систему счисления(начиная с последнего элемента массива)
        // длинна-1 необходима т.к первое значения массива 0
        {
            ResultS = ResultS + Character.getNumericValue(arrayS[i]) * Power(2, z);
            // переведем символы в цифры (т.к при считывании строки программа рассматривала их
            //  как символы и присваивала значения исходя из ASCII таблицы)
            z++; // увеличиваем степень и гоним цикл
        }
        return ResultS;
    }
}