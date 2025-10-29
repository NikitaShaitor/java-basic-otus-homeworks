import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    public ClientHandler[] client;
    private int port;
    private List<ClientHandler> clients;

    public Server(int port) {

        this.port = port;
        clients = new CopyOnWriteArrayList<>();

    }

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                subscribe(new ClientHandler(socket, this));
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void subscribe(ClientHandler clientHandler) {

        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {

        System.out.println("Клиент " + clientHandler.getUsername() + " отключился от сервера ");
        clients.remove(clientHandler);
    }

    public void broadcastMessage(String message) {

        for (ClientHandler c : clients) {
            c.sendMessage(message);
        }
    }

    protected void sendPrivateMessage(ClientHandler sender, String targetUsername, String message) {
        for (ClientHandler handler : clients) {
            if (handler.getUsername().equals(targetUsername)) {
                handler.sendMessage("\033[1;36m" + sender.getUsername() + "\033[0m" + "\033[0;97m" + " шепнул вам: " + message + "\033[0m");
                return;
            }
        }
    }
}