package com.inventory.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class ProductDAO {

    // Save product
    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(product);

        tx.commit();
        session.close();
    }

    // Get product by ID
    public Product getProduct(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Product product = session.get(Product.class, id);

        session.close();
        return product;
    }

    // Update product
    public void updateProduct(Long id, double price, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {
            product.setPrice(price);
            product.setQuantity(quantity);
            session.update(product);
        }

        tx.commit();
        session.close();
    }

    // Delete product
    public void deleteProduct(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {
            session.delete(product);
        }

        tx.commit();
        session.close();
    }
}