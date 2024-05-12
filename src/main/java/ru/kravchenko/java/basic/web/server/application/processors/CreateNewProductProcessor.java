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

public class CreateNewProductProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream outputStream) throws IOException {
        Gson gson = new Gson();
        Product product = gson.fromJson(httpRequest.getBody(), Product.class);
        try {
            DBStorage.createNewProduct(product.getName(), product.getPrice());
            String response = "HTTP/1.1 201 Created";
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (SQLException e) {
            // e.printStackTrace();
            LogService.error(e.getMessage());
        }


    }
}
