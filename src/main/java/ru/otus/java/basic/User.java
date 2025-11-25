import java.sql.*;

public class User {
    private static final String DB_URL = "jdbc:sqlite:chat.db";

    public boolean userExists(String username) throws SQLException {
        try(Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username=?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public void registerUser(String username, String password) throws SQLException {
        if(userExists(username)) {
            throw new IllegalArgumentException("Пользователь с таким именем уже зарегистрирован.");
        }

        try(Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password); // Здесь должен быть пароль, зашифрованный хэшем!
            stmt.executeUpdate();
        }
    }

    public boolean authenticateUser(String username, String password) throws SQLException {
        try(Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}
