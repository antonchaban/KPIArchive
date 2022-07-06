package ua.kpi.fict.acts.it03.proga.Lab6;

public class ArithmeticThread extends Thread {
    int nMin;
    int nMax;
    long result;

    public ArithmeticThread(int nMin, int nMax){
        this.nMin = nMin;
        this.nMax = nMax;
    }

    @Override
    public void run() {
        long res=0;
        for(int i = nMin; i <= nMax; i++){
            res += 4L*i;
        }
        result = res;
    }

    public long getResult() {
        return result;
    }
}
