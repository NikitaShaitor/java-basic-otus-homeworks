package ru.otus.java.basic.novermber.http.server.processors;

import ru.otus.java.basic.novermber.http.server.HttpRequest;
import ru.otus.java.basic.novermber.http.server.exceptions_handling.BadRequestException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CalculatorProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {

        int a, b;
        if (!request.containsParameter("a")) {
            throw new BadRequestException("В запросе отсутствует обязательный параметр запроса 'a'");
        }
        if (!request.containsParameter("b")) {
            throw new BadRequestException("В запросе отсутствует обязательный параметр запроса 'b'");
        }
        try {
            a = Integer.parseInt(request.getParameter("a"));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Параметр запроса 'a' имеет некорректный формат");
        }
        try {
            b = Integer.parseInt(request.getParameter("b"));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Параметр запроса 'b' имеет некорректный формат");
        }
        String result = a + " + " + b + " = " + (a + b);
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body><h1>" + result + "</h1></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
