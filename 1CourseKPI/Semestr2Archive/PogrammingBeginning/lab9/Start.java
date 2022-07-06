package ua.kpi.fict.acts.it03.proga.lab9;

public class Start {
    class Inner{
        void msg(){
            System.out.println("a");
        }
    }

    public static void main(String[] args) {
        Inner a = new Start().new Inner();
        a.msg();
    }
}