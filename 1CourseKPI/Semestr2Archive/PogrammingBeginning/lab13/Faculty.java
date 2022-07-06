package ua.kpi.fict.acts.it03.proga.lab13;

import java.util.LinkedList;

public class Faculty {
  private String name;
  private LinkedList<Student> list = new LinkedList<>();

  public Faculty(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LinkedList<Student> getList() {
    return list;
  }

  public void setList(LinkedList<Student> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "Faculty{" +
      "name='" + name + '\'' +
      ", list=" + list +
      '}';
  }
}