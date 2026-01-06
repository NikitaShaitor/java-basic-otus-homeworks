package ru.otus.java.basic.novermber.http.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerThread implements Runnable {
    private final Socket clientSocket;
    private final Dispatcher dispatcher;

    public WorkerThread(Socket clientSocket, Dispatcher dispatcher) {
        this.clientSocket = clientSocket;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try (
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream()
        ) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] tempBuffer = new byte[8192];

            int bytesRead;
            while ((bytesRead = inputStream.read(tempBuffer)) != -1) {
                buffer.write(tempBuffer, 0, bytesRead);
            }

            String rawRequest = buffer.toString("UTF-8");

            HttpRequest request = new HttpRequest(rawRequest);
            request.info(true);

            dispatcher.execute(request, outputStream);
        } catch (IOException | OutOfMemoryError ex) {
            System.err.println("Ошибка при обработке запроса: " + ex.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) {}
        }
    }
}
