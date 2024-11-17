public class Product {
    String id;
    String name;
    String category;
    String price;

    public Product(String id, String name, String category, String price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getPrice() {
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
