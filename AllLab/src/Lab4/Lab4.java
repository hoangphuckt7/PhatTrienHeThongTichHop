package Lab4;

import java.io.IOException;
import java.util.Scanner;

public class Lab4 {
    private static Scanner scanner;
	private static boolean isExit = false;
    public static void MainLab4() {
        scanner = new Scanner(System.in);
        while (!isExit) {
			System.out.println("================= Chọn bài muốn xem hoặc thoát ======");
			System.out.println("===== Bài 1: Khảo sát địa chỉ mạng ==================");
			System.out.println("========= Thoát chương trình (0) ========================");
			System.out.print("========= Chọn: ");
			int numb = scanner.nextInt();
			scanner.nextLine();
			switch (numb) {
				case 1:
                    if (!compile()) {
                        System.out.println("[ERROR] Biên dịch thất bại.");
                        return;
                    }

                    run();
					break;

				default:
					isExit = true;
					break;
			}
		}
		scanner.close();
    }
    private static boolean compile() {

        System.out.println("[BUILD] Đang biên dịch...");
        String[] command = {
            "javac",
            "-encoding",
            "UTF-8",
            "-d",
            "src/Lab4/out",
            "src/Lab4/HostInspector.java"
        };
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
            "src/Lab4/out/",
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
}
