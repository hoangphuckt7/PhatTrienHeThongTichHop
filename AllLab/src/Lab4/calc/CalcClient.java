package Lab4.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class CalcClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8888;
        try (
            Socket socket = new Socket();
        ) {
            // Thiết lập kết nối tới Server với timeout 5 giây
            System.out.println("Connecting to server " + host + ":" + port + "...");
            socket.connect(new InetSocketAddress(host, port), 5000);
            System.out.println("Connected! Enter (ex: CALC + 100 200). Enter 'exit' out.");

            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);

            String userInput;
            while (true) {
                System.out.print("Client > ");
                userInput = keyboardInput.readLine();
                
                if (userInput == null || userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                if (userInput.trim().isEmpty()) {
                    continue;
                }

                // Gửi dữ liệu lên Server (kèm ký tự xuống dòng nhờ println)
                serverWriter.println(userInput);

                // Chờ và nhận phản hồi từ Server
                String response = serverReader.readLine();
                System.out.println("Server > " + response);
            }

        } catch (SocketTimeoutException e) {
            System.err.println("Lỗi: Kết nối quá thời gian (Timeout 5 giây)!");
        } catch (IOException e) {
            System.err.println("Lỗi kết nối: " + e.getMessage());
        }
    }
}
