import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ChatApp {
    public static void main(String[] args) {
        User dao = new User();

        JOptionPane.showInputDialog("Введите ваше имя:");
        String username = JOptionPane.showInputDialog("Имя пользователя:");
        String password = JOptionPane.showInputDialog("Пароль:");

        try {
            dao.registerUser(username, password);
            JOptionPane.showMessageDialog(null, "Регистрация успешна!");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Ошибка регистрации: " + e.getMessage());
        }

        username = JOptionPane.showInputDialog("Авторизация\nЛогин:");
        password = JOptionPane.showInputDialog("Пароль:");

        try {
            if(dao.authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(null, "Вы вошли в систему!");
            } else {
                JOptionPane.showMessageDialog(null, "Неверный логин или пароль!");
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Ошибка авторизации: " + e.getMessage());
        }
    }
}