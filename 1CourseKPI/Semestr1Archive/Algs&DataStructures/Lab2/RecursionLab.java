import java.util.Scanner;

public class RecursionLab {
    public static void main(String[] args) {
        int printed = GetNaturalInt();
        int resultinterative = NumSum(printed);
        int resultRec = NumSumRec(printed);


        System.out.println("Інтеративно:"+resultinterative);
        System.out.println("Рекурсивно:"+resultRec);
    }


    private static int NumSumRec(int n)
    {
        if (n <= 9) return n;

        return (n%10)+NumSumRec(n/10);
    }

    private static int NumSum(int n)
    {
        int res = 0;
        int remainder;
        while (n>9)
        {
            remainder = n%10;
            res += remainder;
            n = n/10;
        }

        return res+n;
    }


    private static int GetNaturalInt()
    {
        int s;
        do {
            Scanner natural = new Scanner(System.in);
            System.out.print("Natural number: ");
            s = natural.nextInt();
        } while (s <= 0);
        return s;
    }
}

