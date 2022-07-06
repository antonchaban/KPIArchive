package ua.kpi.fict.acts.it03.proga.lab12;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
public class TaskRunner {

  public void task1()
  {
    FInterface<Laptop, String, Integer> lapContructor = Laptop::new;
    Laptop lap1 = lapContructor.get("test1", 100);
    System.out.println(lap1);
  }

  public void task2()
  {
    Consumer<String> writer = Laptop::staticMethod;
    writer.accept("Hello from static");
  }

  public void task3()
  {
    Laptop lap2 = new Laptop("comp1", 20);
    Consumer<String> writer = lap2::nonStaticMethod;
    writer.accept("Hello from nonStatic");
  }

  public void task4()
  {
    Laptop lap2 = new Laptop("comp2", 20);
    UnaryOperator<String> writer = String::toLowerCase;
    System.out.println(writer.apply("HeLlO fRoM NoN StAtIc"));
  }
}
