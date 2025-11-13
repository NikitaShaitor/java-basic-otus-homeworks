import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

enum Role {
    USER,
    ADMIN
}

public class ClientHandler {

    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private Role role = Role.USER;


    public ClientHandler(Socket socket, Server server) throws IOException {

        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        username = "User" + socket.getPort();

        new Thread(() -> {

            System.out.println("Клиент подключился к серверу " + socket.getPort());
            sendMessage("Вы подключились, ваш ник: " + username);

            try {
                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.equals("/exit")) {
                            sendMessage("/exitok");
                            break;
                        } else if (message.startsWith("/w")) {
                            String[] parts = message.split(" ", 3);
                            if (parts.length >= 3) {
                                String targetUsername = parts[1];
                                String content = parts[2];
                                server.sendPrivateMessage(this, targetUsername, content);
                            } else {
                                sendMessage("Неправильный формат команды '/w'. Используйте: /w <Имя пользователя> <Сообщение>");
                            }
                        } else if (role == Role.ADMIN && message.startsWith("/kick")) {
                            String[] parts = message.split(" ");
                            if (parts.length > 1) {
                                String targetUsername = parts[1].trim();

                                boolean foundAndKicked = false;
                                synchronized (server.client) {
                                    for (ClientHandler ch : server.client) {
                                        if (ch.getUsername().equals(targetUsername)) {
                                            ch.disconnect();
                                            foundAndKicked = true;
                                            break;
                                        }
                                    }
                                }

                                if (foundAndKicked) {
                                    sendMessage("Пользователь: " + targetUsername + " успешно отлючен");
                                } else {
                                    sendMessage("Пользователь с именем: " + targetUsername + " не найден");
                                }
                            } else {
                                sendMessage("Ошибка: неверный формат команды '/kick'. Пример правильного формата: /kick userName");
                            }
                        }
                    } else {
                        server.broadcastMessage(username + ": " + message);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMessage(String message) {

        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public void disconnect() {
        server.unsubscribe(this);

        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            if (out != null) {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            if (socket != null) {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}