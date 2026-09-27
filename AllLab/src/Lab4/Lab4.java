package Lab4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab4 {
    private static Scanner scanner;
	private static boolean isExit = false;
    private static final List<String> BASE_COMPILE_COMMAND = List.of(
        "javac",
        "-encoding",
        "UTF-8",
        "-d",
        "AllLab/src/Lab4/out"
    );
    private static String Server = "java -cp AllLab/src/Lab4/out/ Lab4.TcpCommandServer";
    private static String Client = "java -cp AllLab/src/Lab4/out/ Lab4.TcpCommandClient";
    private static String HOST = "localhost";
	private static int PORT = 5000;
    public static void MainLab4() throws Exception {
        scanner = new Scanner(System.in);
        while (!isExit) {
			System.out.println("================= Chọn bài muốn xem hoặc thoát ======");
			System.out.println("===== Bài 1: Khảo sát địa chỉ mạng ==================");
			System.out.println("===== Bài 2: TCP client server theo giao thức dòng ==");
			System.out.print("========= Chọn: ");
			int numb = scanner.nextInt();
			scanner.nextLine();
			switch (numb) {
				case 1:
                    if (!compile(1)) {
                        System.out.println("[ERROR] Biên dịch thất bại.");
                        return;
                    }

                    run();
					break;
                case 2:
                    if (!compile(2)) {
                        System.out.println("[ERROR] Biên dịch thất bại.");
                        return;
                    }
                    if(isServerRunning(HOST, PORT)){
                        System.out.println("> Server đang chạy.");
                    } else{
                        System.out.println("[ERROR] Server đang chạy.");
                        System.out.println("> Chạy lại server.");
                        openTerminal("Server",Server);

                        waitForServer(HOST, PORT);
                    }
                    System.out.println( "[LAUNCHER] Starting Client..." );

                    openTerminal( "Client", Client);

					break;
				default:
					isExit = true;
					break;
			}
		}
		scanner.close();
    }
    private static boolean compile(Integer numb) {

        System.out.println("[BUILD] Đang biên dịch...");
        List<String> command = new ArrayList<>(BASE_COMPILE_COMMAND);
        if(numb == 1){
            command.add("AllLab/src/Lab4/HostInspector.java");
        } else if(numb == 2){
            command.add("AllLab/src/Lab4/TcpCommandServer.java");
            command.add("AllLab/src/Lab4/TcpCommandClient.java");
        }
        try {
            System.out.println("> " + String.join(" ", command));
            
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Cho output của javac hiển thị trực tiếp trên console
            processBuilder.inheritIO();

            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("[BUILD] Hoàn tất biên dịch.");
                return true;
            }

            return false;

        } catch (IOException | InterruptedException e) {

            System.out.println("[BUILD] Lỗi: " + e.getMessage());

            return false;
        }
    }

    private static void run() {

        System.out.println("[RUN] Đang chạy chương trình...");
        String[] command = {
            "java",
            "-cp",
            "AllLab/src/Lab4/out/",
            "Lab4.HostInspector"
        };
        try {
            System.out.println("> " + String.join(" ", command));
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            processBuilder.inheritIO();

            Process process = processBuilder.start();

            process.waitFor();

        } catch (IOException | InterruptedException e) {

            System.out.println("[RUN] Lỗi: " + e.getMessage());
        }
    }

    private static boolean isServerRunning(String host, int port){
        try (Socket socket = new Socket()) {
            socket.connect(
                new InetSocketAddress(host, port)
                , 5000);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void openTerminal( String title, String command ) throws IOException {

        new ProcessBuilder(
            "cmd.exe",
            "/c",
            "start",
            "\"" + title + "\"",
            "cmd.exe",
            "/k",
            command
        ).start();
    }

    private static void waitForServer( String host, int port ) throws InterruptedException {

        System.out.println("[LAUNCHER] Waiting for server...");

        while (!isServerRunning(host, port)) {

            Thread.sleep(500);

            System.out.println(
                "[LAUNCHER] Server chưa ready..."
            );
        }

        System.out.println(
            "[LAUNCHER] Server READY!"
        );
    }
}
