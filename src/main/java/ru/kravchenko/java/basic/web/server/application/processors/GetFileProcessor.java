package ru.kravchenko.java.basic.web.server.application.processors;

import ru.kravchenko.java.basic.web.server.HttpRequest;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class GetFileProcessor implements RequestProcessor {
    private final RequestProcessor unknownOperationRequestProcessor = new UnknownOperationRequestProcessor();

    @Override
    public void execute(HttpRequest httpRequest, OutputStream outputStream) throws IOException {
        String dir = "src/main/resources/static";
        String uri = httpRequest.getUri();
        String filePath = dir + uri;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String[] tokens = uri.split("[.]", 2);
            String fileFormat = tokens[1];
            String contentType = "text/html";
            switch (fileFormat) {
                case "csv":
                    contentType = "text/csv";
                    break;
            }
            StringBuilder response = new StringBuilder("HTTP/1.1 200 OK\r\nContent-Type: ");
            response.append(contentType).append("\r\n\r\n");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            outputStream.write(response.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            unknownOperationRequestProcessor.execute(httpRequest, outputStream);
        }
    }
}
