import java.io.*;
import java.util.Scanner;

class Product {
    String name;
    String unit;
    String grade;
    int quantity;
    double price;

    public Product(String name, String unit, String grade, int quantity, double price) {
        this.name = name;
        this.unit = unit;
        this.grade = grade;
        this.quantity = quantity;
        this.price = price;
    }

    public double totalCost() {
        return quantity * price;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть назву товару: ");
        String searchName = scanner.nextLine();

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                String name = data[0];
                String unit = data[1];
                String grade = data[2];
                int quantity = Integer.parseInt(data[3]);
                double price = Double.parseDouble(data[4]);

                Product product = new Product(name, unit, grade, quantity, price);
                //Виведення резуоьтату
                if (product.name.equalsIgnoreCase(searchName)) {

                    found = true;

                    System.out.println("\nІнформація про товар:");
                    System.out.println("Назва товару: " + product.name);
                    System.out.println("Сорт товару: " + product.grade);
                    System.out.println("Кількість: " + product.quantity+" "+product.unit);
                    System.out.println("Ціна за одиницю: " + product.price);
                    System.out.println("Загальна сума на складі: " + product.totalCost());
                }
            }

            if (!found) {
                System.out.println("Товар не знайдено");
            }

        } catch (IOException e) {
            System.out.println("Товар не знайдено");
        }
    }
}