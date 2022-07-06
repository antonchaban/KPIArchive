package ua.kpi.fict.acts.it03.proga.lab11;

import java.util.Objects;

public final class Laptop extends Device {
  private String cpu;
  private int ram;
  private String gpu;


  public Laptop(String company, String matrixType, int price) {
    super(company, matrixType, price);

    this.cpu = "Cpu";
    this.ram = 128;
    this.gpu = "Gpu";
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Laptop laptop = (Laptop) o;
    return ram == laptop.ram &&
      cpu.equals(laptop.cpu) &&
      gpu.equals(laptop.gpu);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), cpu, ram, gpu);
  }

  @Override
  public String toString() {
    return "Laptop by: " + getCompany() + " with " + getMatrixType() + " matrix " + "cost: " + getPrice()+ " UAH " + "Properties: " + getCpu() +" , "+ getGpu() +" , "+ getRam() + "GB";
  }

  public String getGpu() {
    return gpu;
  }

  public String getCpu() {
    return cpu;
  }

  public int getRam() {
    return ram;
  }
}