import java.util.Objects;

public class Item {

    private String name;
    private double weight;
    private double price;
    private double quality;

    public Item(String name, double weight, double price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.quality = (price/weight);
    }

    public Item(String name, double weight, double price, double quality) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.quality = (price/weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.weight, weight) == 0 && Double.compare(item.price, price) == 0 && Double.compare(item.quality, quality) == 0 && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, price, quality);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", quality=" + quality +
                '}';
    }
/* @Override
    public String toString() {
        return "Item [name=" + name + ", weight=" + weight + ", price=" + price + " ]";
    }*/


}
