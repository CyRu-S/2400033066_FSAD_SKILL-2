package com.inventory.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.loader.ProductDataLoader;
import com.inventory.util.HibernateUtil;

public class HQLDemo {

        public static void main(String[] args) {

                SessionFactory factory = HibernateUtil.getSessionFactory();
                Session session = factory.openSession();

                try {

                        // Insert sample data
                        ProductDataLoader.loadSampleProducts(session);

                        sortProductsByPriceAscending(session);
                        sortProductsByPriceDescending(session);
                        sortProductsByQuantityDescending(session);

                        getFirstThreeProducts(session);
                        getNextThreeProducts(session);

                        countTotalProducts(session);
                        countProductsInStock(session);

                } finally {
                        session.close();
                        factory.close();
                }
        }

        // Sort by price ASC
        public static void sortProductsByPriceAscending(Session session) {

                String hql = "FROM Product p ORDER BY p.price ASC";
                Query<Product> query = session.createQuery(hql, Product.class);

                List<Product> products = query.list();

                System.out.println("Products sorted by price ASC:");
                for (Product p : products) {
                        System.out.println(p);
                }
        }

        // Sort by price DESC
        public static void sortProductsByPriceDescending(Session session) {

                String hql = "FROM Product p ORDER BY p.price DESC";
                Query<Product> query = session.createQuery(hql, Product.class);

                List<Product> products = query.list();

                System.out.println("Products sorted by price DESC:");
                for (Product p : products) {
                        System.out.println(p);
                }
        }

        // Sort by quantity
        public static void sortProductsByQuantityDescending(Session session) {

                String hql = "FROM Product p ORDER BY p.quantity DESC";
                Query<Product> query = session.createQuery(hql, Product.class);

                List<Product> products = query.list();

                System.out.println("Products sorted by quantity:");
                for (Product p : products) {
                        System.out.println(p.getName() + " - " + p.getQuantity());
                }
        }

        // Pagination first 3
        public static void getFirstThreeProducts(Session session) {

                String hql = "FROM Product p";
                Query<Product> query = session.createQuery(hql, Product.class);

                query.setFirstResult(0);
                query.setMaxResults(3);

                List<Product> products = query.list();

                System.out.println("First 3 products:");
                for (Product p : products) {
                        System.out.println(p);
                }
        }

        // Pagination next 3
        public static void getNextThreeProducts(Session session) {

                String hql = "FROM Product p";
                Query<Product> query = session.createQuery(hql, Product.class);

                query.setFirstResult(3);
                query.setMaxResults(3);

                List<Product> products = query.list();

                System.out.println("Next 3 products:");
                for (Product p : products) {
                        System.out.println(p);
                }
        }

        // Count all products
        public static void countTotalProducts(Session session) {

                String hql = "SELECT COUNT(p) FROM Product p";
                Query<Long> query = session.createQuery(hql, Long.class);

                Long count = query.uniqueResult();

                System.out.println("Total products: " + count);
        }

        // Count products with quantity > 0
        public static void countProductsInStock(Session session) {

                String hql = "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0";
                Query<Long> query = session.createQuery(hql, Long.class);

                Long count = query.uniqueResult();

                System.out.println("Products in stock: " + count);
        }
}