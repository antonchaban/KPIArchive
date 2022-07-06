package ua.kpi.fict.acts.it03.proga.lab10;


import java.util.Objects;

class Device {
    public String company;
    private String matrixType;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return price == device.price &&
                Objects.equals(company, device.company) &&
                Objects.equals(matrixType, device.matrixType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, matrixType, price);
    }

    public Device(String company, String matrixType, int price) {
        this.company = company;
        this.matrixType = matrixType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Device{" +
                "company='" + company + '\'' +
                ", matrixType='" + matrixType + '\'' +
                ", price=" + price +
                '}';
    }


    public String getCompany() {
        return company;
    }

    public String getMatrixType() {
        return matrixType;
    }

    public int getPrice() {
        return price;
    }
}