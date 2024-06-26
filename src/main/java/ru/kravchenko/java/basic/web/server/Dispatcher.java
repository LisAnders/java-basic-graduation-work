package ru.kravchenko.java.basic.web.server;

import ru.kravchenko.java.basic.web.server.application.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private Map<String, RequestProcessor> router;
    private RequestProcessor unknownOperationRequestProcessor;
    private RequestProcessor getFileProcessor;

    public Dispatcher() {
        this.router = new HashMap<>();
        this.router.put("GET /products", new GetProductsProcessor());
        this.router.put("POST /products", new CreateNewProductProcessor());
        this.router.put("PUT /products", new UpdateProductProcessor());
        this.router.put("DELETE /products", new DeleteProductProcessor());
        this.unknownOperationRequestProcessor = new UnknownOperationRequestProcessor();
        this.getFileProcessor = new GetFileProcessor();
    }

    public void execute(HttpRequest httpRequest, OutputStream outputStream) throws IOException {
        if (!router.containsKey(httpRequest.getRouteKey())) {
            if (httpRequest.getMethod() == HttpMethod.GET) {
                getFileProcessor.execute(httpRequest, outputStream);
                return;
            }
            unknownOperationRequestProcessor.execute(httpRequest, outputStream);
            return;
        }
        router.get(httpRequest.getRouteKey()).execute(httpRequest, outputStream);
    }
}
