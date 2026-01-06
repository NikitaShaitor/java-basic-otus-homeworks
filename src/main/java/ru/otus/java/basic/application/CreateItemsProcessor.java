package ru.otus.java.basic.application;

import com.google.gson.Gson;
import ru.otus.java.basic.HttpRequest;
import ru.otus.java.basic.processors.RequestProcessor;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CreateItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        Item item = gson.fromJson(request.getBody(), Item.class);
        ItemsStorage.createItem(item);
        String response = "" +
                "HTTP/1.1 201 Created\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                gson.toJson(item);
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
