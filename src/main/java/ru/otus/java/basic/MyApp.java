import java.sql.SQLException;
import java.util.Scanner;

public class MyApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            if(Base.authenticateUser(username, password)) {
                System.out.println("Аутентификация успешна!");
            } else {
                System.out.println("Неверное имя пользователя или пароль.");
            }
        } catch(SQLException e) {
            System.err.println("Ошибка базы данных: " + e.getMessage());
        }
    }
}