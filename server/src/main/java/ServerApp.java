public class ServerApp {
    public static final int PORT = 8080;

    public static void main(String[] args) {

        new Server(PORT).start();
    }
}

