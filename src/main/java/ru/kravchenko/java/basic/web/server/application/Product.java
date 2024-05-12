package ru.kravchenko.java.basic.web.server.application;

public class Product {
    private int product_id;
    private String name;
    private float price;

    public int getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product(int product_id, String name, float price) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
    }
}
