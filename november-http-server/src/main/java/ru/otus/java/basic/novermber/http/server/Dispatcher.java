package ru.otus.java.basic.novermber.http.server;

import ru.otus.java.basic.novermber.http.server.application.CreateItemsProcessor;
import ru.otus.java.basic.novermber.http.server.application.GetItemsProcessor;
import ru.otus.java.basic.novermber.http.server.exceptions_handling.BadRequestException;
import ru.otus.java.basic.novermber.http.server.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dispatcher {
    private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);
    private Map<String, RequestProcessor> routes;
    private RequestProcessor defaultNotFoundProcessor;
    private RequestProcessor defaultStaticResourceProcessor;

    public Dispatcher() {
        routes = new HashMap<>();
        routes.put("GET /hello", new HelloWorldProcessor());
        routes.put("POST /hello", new PostHelloWorldProcessor());
        routes.put("GET /add", new CalculatorProcessor());
        routes.put("GET /shop/api/v1/items", new GetItemsProcessor());
        routes.put("POST /shop/api/v1/items", new CreateItemsProcessor());
        routes.put("DELETE /shop/api/v1/items", new DeleteItemProcessor());
        defaultNotFoundProcessor = new DefaultNotFoundProcessor();
        defaultStaticResourceProcessor = new DefaultStaticResourceProcessor();
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (Files.exists(Paths.get("static/", request.getUri().substring(1)))) {
            logger.debug("Обработка статического ресурса {}", request.getUri());
            defaultStaticResourceProcessor.execute(request, output);
            return;
        }
        if (!routes.containsKey(request.getRoutingKey())) {
            logger.warn("Запрос {} не найден в маршрутах", request.getRoutingKey());
            defaultNotFoundProcessor.execute(request, output);
            return;
        }
        try {
            logger.info("Выполнение маршрута {}", request.getRoutingKey());
            routes.get(request.getRoutingKey()).execute(request, output);
        } catch (BadRequestException e) {
            logger.error("Ошибка обработки запроса: BAD REQUEST {}", e.getMessage());
            sendResponse(output, 400, "text/html", "<html><body><h1>BAD REQUEST: " + e.getMessage() + "</h1></body></html>");
        } catch (Exception e) {
            logger.error("Ошибка обработки запроса", e);
            sendResponse(output, 500, "text/html", "<html><body><h1>ОЙ</h1></body></html>");
        }
    }

    private void sendResponse(OutputStream out, int code, String contentType, String content) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ").append(code).append(" ")
                .append(getStatusText(code)).append("\r\n")
                .append("Content-Type: ").append(contentType).append("\r\n")
                .append("\r\n").append(content);
        out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    private String getStatusText(int code) {
        switch (code) {
            case 200: return "OK";
            case 201: return "Created";
            case 400: return "Bad Request";
            case 404: return "Not Found";
            case 500: return "Internal Server Error";
            default: return "";
        }
    }
}
