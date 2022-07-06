package ua.kpi.fict.acts.it03.proga.lab13;

public class Enrollee {
  private String name;
  private int score;

  public Enrollee(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return "Enrollee{" +
      "name='" + name + '\'' +
      ", score=" + score +
      '}';
  }
}