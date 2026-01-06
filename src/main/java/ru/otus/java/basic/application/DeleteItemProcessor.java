package ru.otus.java.basic.application;

import ru.otus.java.basic.HttpRequest;
import ru.otus.java.basic.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DeleteItemProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        if (id <= 0 || !ItemsStorage.deleteItem(id)) {
            sendResponse(output, 404, "application/json", "{\"message\": \"Product not found\"}");
            return;
        }
        sendResponse(output, 200, "application/json", "{}"); // Возвращаем пустой JSON при успешном удалении
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
            case 200:
                return "OK";
            case 404:
                return "Not Found";
            default:
                return "";
        }
    }
}
