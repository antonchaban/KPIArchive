package ua.kpi.fict.acts.it03.proga.lab13;

public class Student {
  private String name;
  private String surname;
  private int RBN;
  private int score;

  public Student(String name, String surname, int RBN, int score) {
    this.name = name;
    this.surname = surname;
    this.RBN = RBN;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getRBN() {
    return RBN;
  }

  public void setRBN(int RBN) {
    this.RBN = RBN;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return "Student{" +
      "name='" + name + '\'' +
      ", surname='" + surname + '\'' +
      ", RBN=" + RBN +
      ", score=" + score +
      '}';
  }
}