import java.io.File;
import java.util.Scanner;

public class ProductManagement {
    public static void main(String[] args) {
        BalancedSearchTree<String, Product> tree = new BalancedSearchTree<>();

        // insertion process

        try {
            Scanner fileScan = new Scanner(new File("/Users/rhiltz/IdeaProjects/Week10/csv/amazon-product-data.csv"));
            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String productId = parts[0];
                    String name = parts[1];
                    String category = parts[2];
                    String price = parts[3];
                    Product product = new Product(productId, name, category, price);

                    // Insert the product into the tree
                    tree.put(productId, product);

                    StdOut.println("Inserted: " + product);
                }
            }
        } catch (Exception e) {
            StdOut.println("Error reading file: " + e.getMessage());
        }

        Scanner scan = new Scanner(System.in);

        // for search
        StdOut.println("Enter Product IDs to search (type 'exit' to stop):");
        while (true) {
            StdOut.println("Search Product ID: ");
            String searchId = scan.nextLine();
            if (searchId.equalsIgnoreCase("exit")) {
                break;
            }

            Product product = tree.get(searchId);
            if (product != null) {
                StdOut.println("Product Found: " + product);
            } else {
                StdOut.println("Product with ID " + searchId + " not found.");
            }
        }

        // hardcoded insertion
        Product newProduct1 = new Product("12345", "Wireless Earbuds", "Electronics", "$59.99");
        tree.put("12345", newProduct1);
        StdOut.println("Inserted: " + newProduct1);

        Product newProduct2 = new Product("1234567", "Macbook Air", "Electronics", "$999.99");
        tree.put("1234567", newProduct2);
        StdOut.println("Inserted: " + newProduct2);

        // duplicate test
        try {
            Product duplicateProduct = new Product("12345", "Duplicate Wireless Earbuds", "Electronics", "$49.99");
            tree.put("12345", duplicateProduct);
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate Insertion Error: " + e.getMessage());
        }

        scan.close();
    }
}
