package ru.kravchenko.java.basic.web.server.application.processors;

import com.google.gson.Gson;
import ru.kravchenko.java.basic.web.server.HttpRequest;
import ru.kravchenko.java.basic.web.server.application.DBStorage;
import ru.kravchenko.java.basic.web.server.application.Product;
import ru.kravchenko.java.basic.web.server.logging.LogService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class GetProductsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream outputStream) throws IOException {

        try {
            Gson gson = new Gson();
            String response;
            if (httpRequest.getParameter("id")!=null) {
                Product product = DBStorage.getProductById(Integer.parseInt(httpRequest.getParameter("id")));
                response = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + gson.toJson(product);
            } else {
                List<Product> products = DBStorage.getAllProducts();
                response = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + gson.toJson(products);
            }
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (SQLException e) {
            LogService.error(e.getMessage());
        }

    }
}
