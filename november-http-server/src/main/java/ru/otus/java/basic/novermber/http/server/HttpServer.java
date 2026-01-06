package ru.otus.java.basic.novermber.http.server;

import ru.otus.java.basic.novermber.http.server.application.ItemsStorage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final int port;
    private final Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        ItemsStorage.init(); // Инициализация хранилища товаров
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                WorkerThread worker = new WorkerThread(clientSocket, dispatcher);
                Thread thread = new Thread(worker);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
