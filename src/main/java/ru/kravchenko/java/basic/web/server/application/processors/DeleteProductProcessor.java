package ru.kravchenko.java.basic.web.server.application.processors;

import ru.kravchenko.java.basic.web.server.HttpRequest;
import ru.kravchenko.java.basic.web.server.application.DBStorage;
import ru.kravchenko.java.basic.web.server.logging.LogService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class DeleteProductProcessor implements RequestProcessor{

    @Override
    public void execute(HttpRequest httpRequest, OutputStream outputStream) throws IOException {
        int id = Integer.parseInt(httpRequest.getParameter("id"));
        try {
            DBStorage.deleteProductById(id);
            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1> Product deleted </h1></body></html>";
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (SQLException e) {
            LogService.error(e.getMessage());
        }
    }
}
