import java.io.File;
import java.util.Scanner;

public class ProductManagement {
    public static void main(String[] args) {
        BalancedSearchTree<String, Product> tree = new BalancedSearchTree<>();
        String fileName = "/Users/rhiltz/IdeaProjects/Week10/src/amazon-product-data.csv";

        // insertion process

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String productId = parts[0];
                    String name = parts[1];
                    String category = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    System.out.println("Product: " + productId + ", " + name + ", " + category + ", " + price);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);

        // for search
        System.out.println("Enter Product IDs to search (type 'exit' to stop):");
        while (true) {
            System.out.print("Search Product ID: ");
            String searchId = scanner.nextLine();
            if (searchId.equalsIgnoreCase("exit")) {
                break;
            }

            Product product = tree.get(searchId);
            if (product != null) {
                System.out.println("Product Found: " + product);
            } else {
                System.out.println("Product with ID " + searchId + " not found.");
            }
        }

        // hardcoded insertion
        Product newProduct1 = new Product("P1000", "Wireless Earbuds", "Electronics", 59.99);
        tree.put("P1000", newProduct1);
        System.out.println("Inserted: " + newProduct1);

        Product newProduct2 = new Product("P1001", "Gaming Mouse", "Accessories", 29.99);
        tree.put("P1001", newProduct2);
        System.out.println("Inserted: " + newProduct2);

        scanner.close();
    }
}
