package ru.kravchenko.java.basic.web.server.application.processors;

import ru.kravchenko.java.basic.web.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnknownOperationRequestProcessor implements RequestProcessor{
    @Override
    public void execute(HttpRequest httpRequest, OutputStream outputStream) throws IOException {
        //String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>Unknown Operation Request!</h1></body></html>";
        String response = "HTTP/1.1 404\r\nContent-Type: text/html\r\n\r\n<html><body><h1>Page Not Found</h1></body></html>";
        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
