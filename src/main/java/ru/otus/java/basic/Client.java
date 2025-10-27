package ru.otus.java.basic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String availableOperations = reader.readLine();
            System.out.println("Полученные операции от сервера:");
            System.out.println(availableOperations);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Введите первое число: ");
            double number1 = Double.parseDouble(userInput.readLine());
            System.out.print("Выберите операцию (+, -, *, /): ");
            char operation = userInput.readLine().charAt(0);
            System.out.print("Введите второе число: ");
            double number2 = Double.parseDouble(userInput.readLine());

            String request = number1 + " " + operation + " " + number2;
            writer.println(request);

            String response = reader.readLine();
            System.out.println("Результат вычислений: " + response);
        }
    }
}

