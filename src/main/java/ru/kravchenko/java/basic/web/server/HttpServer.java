package ru.kravchenko.java.basic.web.server;

import ru.kravchenko.java.basic.web.server.application.DBStorage;
import ru.kravchenko.java.basic.web.server.logging.LogService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
    }

    public void  start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LogService.info("Сервер запущен на порту: " + this.port);
            this.dispatcher = new Dispatcher();
            LogService.info("Диспетчер проинициализирован");

            DBStorage.connect();
            LogService.info("Подключились к БД");

            while (true) {
                try (Socket socket = serverSocket.accept()){
                    byte[] buffer = new byte[8192];
                    int n = socket.getInputStream().read(buffer);
                    if (n > 0) {
                        String rawRequest = new String(buffer, 0, n);
                        HttpRequest request = new HttpRequest(rawRequest);
                        request.info(true);
                        dispatcher.execute(request, socket.getOutputStream());
                    }
                }
            }

        } catch (IOException  | SQLException e) {
            LogService.error(e.getMessage());
        }
    }
}
