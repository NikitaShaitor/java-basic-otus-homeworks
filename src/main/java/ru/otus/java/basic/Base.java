import java.sql.*;

public class Base {
    private static final String DB_URL = "jdbc:sqlite:G:/GAS/base/base1";
    private static Connection connection;
    private static PreparedStatement statement;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных.", e);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean authenticateUser(String username, String password) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username=?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt("count") > 0;
            }
        }
    }
}
