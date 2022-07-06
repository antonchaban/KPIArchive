package ua.kpi.fict.acts.it03.proga.Lab6;
import java.util.ArrayList;
import java.util.List;

public class MultiThreads implements Task{
    @Override
    public void calc(int nMax){
        int [] arrThreadsCounts = {2,4,8,16,32};
        long timeStart =0L;
        System.out.println("Результат многопоточности: ");
        for(int thCount : arrThreadsCounts) {
            timeStart = System.currentTimeMillis();
            long res = calculateArithmeticProgress(nMax, thCount);
            long time = System.currentTimeMillis()-timeStart;
            System.out.println("Результат: " + res +
                    " кол-во потоков " + thCount +
                    " расчитан за " + time + " мс"
            );
        }
    }

    private long calculateArithmeticProgress(int n, int threadsCount){
        long res = 0;
        int nMax = 0;
        int nMin = 0;

        //кол-во множителей для одного потока
        int m = n/threadsCount;
        int mLast = n/threadsCount + n%threadsCount;

        List<ArithmeticThread> threads = new ArrayList<ArithmeticThread>(threadsCount);

        ArithmeticThread at;
        for(int i = 0; i < threadsCount; i++){
            nMin = nMax + 1;
            nMax = (nMin - 1) + (i==threadsCount-1 ? mLast : m);

            at = new ArithmeticThread(nMin, nMax);
            threads.add(at);
            at.start();
        }

        for(ArithmeticThread t : threads){
            try {
                t.join();
                res += t.result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
