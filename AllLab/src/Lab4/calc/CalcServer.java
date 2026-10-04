package Lab4.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CalcServer {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("[SERVER] Server started!");
            System.out.println("[SERVER] TCP server listening on port " + PORT);
            while (true) {
                try (Socket socket = server.accept()) {
                    handleClient(socket);
                } catch (IOException e) {
                    System.err.println("> Loi phien client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("> Khong mo duoc Server: " + e.getMessage());
        }
    }

    static void handleClient(Socket socket) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            String request;
            while ((request = reader.readLine()) != null) {
                System.out.println("Nhận từ client: " + request);
                String response = processCalculation(request);

                writer.println(response);
                System.out.println("Phản hồi client: " + response);
            }
        } catch (Exception e) {
            System.out.println("Client đã ngắt kết nối.");
        }
    }

    private static String processCalculation(String requestStr) {
        String[] parts = requestStr.trim().split("\\s+");

        if (parts.length != 4 || !parts[0].equalsIgnoreCase("CALC")) {
            return "ERR INVALID_SYNTAX";
        }

        String operator = parts[2];
        double num1, num2;

        try {
            num1 = Double.parseDouble(parts[1]);
            num2 = Double.parseDouble(parts[3]);
        } catch (NumberFormatException e) {
            return "ERR INVALID_OPERAND";
        }

        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    return "ERR DIVISION_BY_ZERO";
                }
                result = num1 / num2;
                break;
            default:
                return "ERR INVALID_OPERATOR";
        }

        return "OK " + result;
    }
}
