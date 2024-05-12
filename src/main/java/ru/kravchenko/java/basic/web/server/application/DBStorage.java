package ru.kravchenko.java.basic.web.server.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStorage {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_LOGIN = "postgres";
    private static final String DB_PASSWORD = "password";
    private static Connection connection;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
    }

    public static List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        ResultSet rs = connection.createStatement().executeQuery("select * from products order by product_id");
        while (rs.next()) {
            Product product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getFloat("price"));
            products.add(product);
        }
        return products;
    }

    public static Product getProductById(int id) throws SQLException {
        Product product = new Product();
        PreparedStatement ps = connection.prepareStatement("select * from products where product_id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            product.setProduct_id(rs.getInt("product_id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getFloat("price"));
            return product;
        }
        return product;
    }



    public static void createNewProduct(String name, float price) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into products (name, price) values (?, ?)");
        ps.setString(1, name);
        ps.setFloat(2, price);
        ps.executeUpdate();
    }

    public static void updateProductById(int product_id, String name, float price) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update products set name = ?, price = ? where product_id = ?");
        ps.setString(1, name);
        ps.setFloat(2, price);
        ps.setInt(3, product_id);
        ps.executeUpdate();
    }

    public static void deleteProductById(int product_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from products where product_id = ?");
        ps.setInt(1, product_id);
        ps.executeUpdate();
    }
}
