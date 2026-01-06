package ru.otus.java.basic.novermber.http.server.application;

import com.google.gson.Gson;
import ru.otus.java.basic.novermber.http.server.HttpRequest;
import ru.otus.java.basic.novermber.http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {

        Gson gson = new Gson();
        String itemsJson = gson.toJson(ItemsStorage.getItems());
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                itemsJson;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
