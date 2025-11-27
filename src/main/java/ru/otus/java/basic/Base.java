import java.sql.*;

public class Base {
    private static final String DB_URL = "jdbc:sqlite:G:/GAS/base/base1";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public boolean authenticateUser(String username, String password) throws SQLException {
        openConnection();
        try {

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            stmt.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } finally {
            closeConnection();
        }
    }

    private void openConnection() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL);
    }

    private void closeConnection() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
