package ua.kpi.fict.acts.it03.proga.Lab6;

public class ByFormula implements Task{
    @Override
    public void calc(int nMax) {
        long timeStart = System.currentTimeMillis();
        long res = (2 * 6 + (nMax - 1)*6L)*nMax/2;
        long time = System.currentTimeMillis()-timeStart;
        System.out.println("Результат по формуле: " + res +
                " расчитан за " + time + " мс"
        );
    }
}
