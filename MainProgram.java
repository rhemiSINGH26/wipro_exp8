package com.college.inventory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {

        // Create SessionFactory
        Configuration configuration =
                new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFactory =
                configuration.buildSessionFactory();

        ProductDAO dao = new ProductDAO(sessionFactory);

        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== PRODUCT INVENTORY SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    Product product =
                            new Product(name, category, price, quantity);

                    dao.addProduct(product);

                    break;

                case 2:
                    dao.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter New Price: ");
                    double newPrice = sc.nextDouble();

                    System.out.print("Enter New Quantity: ");
                    int newQuantity = sc.nextInt();

                    dao.updateProduct(updateId, newPrice, newQuantity);

                    break;

                case 4:
                    System.out.print("Enter Product ID to Delete: ");
                    int deleteId = sc.nextInt();

                    dao.deleteProduct(deleteId);

                    break;

                case 5:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();

        sessionFactory.close();

        System.out.println("Application closed.");
    }
}