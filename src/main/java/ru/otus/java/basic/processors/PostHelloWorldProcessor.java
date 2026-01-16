package ru.otus.java.basic.processors;

import ru.otus.java.basic.HttpRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PostHelloWorldProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body><h1>POST Hello World!</h1></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }

}
