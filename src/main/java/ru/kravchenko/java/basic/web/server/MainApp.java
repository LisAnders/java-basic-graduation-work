package ru.kravchenko.java.basic.web.server;

public class MainApp {
    public static void main(String[] args) {
        new HttpServer(8189).start();
    }
}
