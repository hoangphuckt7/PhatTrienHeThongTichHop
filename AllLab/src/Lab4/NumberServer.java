package Lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class NumberServer {

	private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Server đang khởi động trên cổng " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client đã kết nối: " + socket.getInetAddress());
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Lỗi Server: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))
            ) {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    inputLine = inputLine.trim();

                    if ("QUIT".equalsIgnoreCase(inputLine)) {
                        writer.write("BYE\n");
                        writer.flush();
                        break;
                    }

                    // Xử lý logic đổi chữ số sang tiếng Việt
                    String response = convertDigitToWord(inputLine);
                    writer.write(response + "\n");
                    writer.flush();
                }
            } catch (IOException e) {
                System.err.println("Lỗi kết nối với client: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    System.out.println("Đã ngắt kết nối client.");
                } catch (IOException e) {
                }
            }
        }

        private String convertDigitToWord(String input) {
            if (input.length() == 1) {
                char c = input.charAt(0);
                switch (c) {
                    case '0': return "Không";
                    case '1': return "Một";
                    case '2': return "Hai";
                    case '3': return "Ba";
                    case '4': return "Bốn";
                    case '5': return "Năm";
                    case '6': return "Sáu";
                    case '7': return "Bảy";
                    case '8': return "Tám";
                    case '9': return "Chín";
                }
            }
            return "ERR INVALID_DIGIT";
        }
    }

}
