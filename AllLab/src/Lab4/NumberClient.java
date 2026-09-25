package Lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class NumberClient {
	private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        String[] testCases = {
            "0",  "9", "", "10", "a", " 5 ", "QUIT"
        };

        try (
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))
        ) {
            System.out.println("Đã kết nối tới Server. Đang gửi các ca biên...");

            for (String testCase : testCases) {
                writer.write(testCase + "\n");
                writer.flush();
                System.out.println("[Client gửi] : \"" + testCase + "\"");

                String response = reader.readLine();
                System.out.println("[Server nhận]: " + response);
                System.out.println("----------------------------------------");
                
                Thread.sleep(500);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Lỗi Client: " + e.getMessage());
        }
    }
}
