public class Product {
    String id;
    String name;
    String category;
    double price;

    public Product(String id, String name, String category, double price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
