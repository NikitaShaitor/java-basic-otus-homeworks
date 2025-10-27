package ru.otus.java.basic;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Сервер запущен");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println("Доступные операции: +, -, *, /");

                String inputLine;
                if ((inputLine = in.readLine()) != null) {
                    String result = calculate(inputLine.trim());
                    out.println(result);
                }

                clientSocket.close();
            }
        }
    }
    private static String calculate(String expression) {
        try {
            int index = expression.indexOf(' ');
            double num1 = Double.parseDouble(expression.substring(0, index));
            char operator = expression.charAt(index+1);
            double num2 = Double.parseDouble(expression.substring(index+3));

            switch (operator) {
                case '+':
                    return "" + (num1 + num2);
                case '-':
                    return "" + (num1 - num2);
                case '*':
                    return "" + (num1 * num2);
                case '/':
                    if (num2 != 0) {
                        return "" + (num1 / num2);
                    } else {
                        throw new ArithmeticException("Деление на ноль!");
                    }
                default:
                    throw new IllegalArgumentException("Неверная операция");
            }
        } catch (NumberFormatException | ArithmeticException e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}

