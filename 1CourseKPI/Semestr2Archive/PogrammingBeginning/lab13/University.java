package ua.kpi.fict.acts.it03.proga.lab13;

import java.util.LinkedList;

public class University {
  private String name;
  private LinkedList<Faculty> list = new LinkedList<>();

  public University(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LinkedList<Faculty> getList() {
    return list;
  }

  public void setList(LinkedList<Faculty> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "University{" +
      "name='" + name + '\'' +
      ", list=" + list +
      '}';
  }
}
