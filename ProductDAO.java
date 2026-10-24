package com.college.inventory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;

    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // CREATE - Add Product
    public void addProduct(Product product) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.persist(product);

            transaction.commit();

            System.out.println("Product added successfully!");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    // READ - View Products
    public void viewProducts() {

        try (Session session = sessionFactory.openSession()) {

            List<Product> products =
                    session.createQuery("FROM Product", Product.class)
                           .getResultList();

            System.out.println("\n----- PRODUCT INVENTORY -----");

            for (Product product : products) {
                System.out.println(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE - Update Product
    public void updateProduct(int id, double newPrice,
                              int newQuantity) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);

            if (product != null) {

                product.setPrice(newPrice);
                product.setQuantity(newQuantity);

                transaction.commit();

                System.out.println("Product updated successfully!");

            } else {

                System.out.println("Product not found!");
                transaction.rollback();

            }

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    // DELETE - Delete Product
    public void deleteProduct(int id) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);

            if (product != null) {

                session.remove(product);

                transaction.commit();

                System.out.println("Product deleted successfully!");

            } else {

                System.out.println("Product not found!");
                transaction.rollback();

            }

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}