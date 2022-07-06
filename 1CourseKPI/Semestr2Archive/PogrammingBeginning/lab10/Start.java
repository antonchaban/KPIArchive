package ua.kpi.fict.acts.it03.proga.lab10;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Start {
    public static void main(String[] args) {
/*        Wrapper wrapper = new Wrapper();
        Person person = new Person("Anton");
        String name = wrapper.doWrap(person::getName);
        System.out.println(name);
        Divider divider = new Divider();
        Calculator calculator = new Calculator();
        Double res = calculator.calculate(divider::division, 3.0,5.0);
        System.out.println(res);*/
        Person person = new Person();
        Wrapper wrapper = new Wrapper();
        wrapper.wrapperName(person::setName,"Anton");
        System.out.println(person.toString());
    }
}

class Person {
    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name){
        this.name = name;
    }
}

class Wrapper{
    public void wrapperName(Consumer<String> consumer, String name){
        consumer.accept(name);
    }
}








/*class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Divider{
    public Double division(Double x, Double y){
        return x/y;
    }
}


class Calculator{
    public Double calculate(BiFunction<Double, Double, Double> func, Double x, Double y){
        return func.apply(x,y);
    }
}
class Wrapper {
    String doWrap(Supplier<String> supplier) {
        return supplier.get();
    }
}*/
