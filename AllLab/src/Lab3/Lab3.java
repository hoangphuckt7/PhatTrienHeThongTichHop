package Lab3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Lab3 {
    private static Scanner scanner;
    private static boolean isExit = false;

    public static void main(String[] args) throws Exception {
        scanner = new Scanner(System.in);
        while (!isExit) {
            System.out.println("================= Chọn bài muốn xem hoặc thoát ======");
            System.out.println("===== Bài 1: Viết chương trình đọc từng dòng từ bàn phím. ==================");
            System.out.println("===== Bài 2: Tạo thư mục data, ghi 3 dòng  ==================");
            System.out.print("========= Chọn: ");
            int numb = scanner.nextInt();
            scanner.nextLine();
            switch (numb) {
                case 1:
                    Bai1();
                    break;
                case 2:
                    Bai2();
                    break;
                default:
                    isExit = true;
                    break;
            }
        }
        scanner.close();
    }

    private static void Bai1() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int count = 0;
        System.out.println("Nhập văn bản; nhập q để kết thúc:");
        try {
            while (true) {
                String line = reader.readLine();
                if (line == null || line.equalsIgnoreCase("q")) {
                    break;
                }
                count++;
                System.out.printf("Dòng %d: %s%n", count, line);
            }
        } catch (IOException e) {
            System.err.println("Không thể đọc dữ liệu: " + e.getMessage());
        }
        System.out.println("Tổng số dòng đã nhập: " + count);
    }

    private static void Bai2() {
        // Đường dẫn tương đối từ thư mục gốc của project (AllLab)
        String dirPath = "AllLab\\src\\Lab3\\data";
        String filePath = dirPath + "/ghi_chu.txt";
        // Tạo thư mục data nếu chưa tồn tại
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // PHẦN 1: ĐỌC TỪ BÀN PHÍM VÀ GHI VÀO FILE
        // -------------------------------------------------------------
        System.out.println("Mời bạn nhập 3 dòng tiếng Việt:");
        
        // Đọc dữ liệu từ bàn phím (System.in) bằng BufferedReader (Mục tiêu 3)
        BufferedReader banPhim = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        
        // Sử dụng try-with-resources để tự động đóng luồng ghi file (Mục tiêu 5)
        try (
            // Dùng Character Stream (Writer) kết hợp buffer để ghi UTF-8 (Mục tiêu 2, 4)
            FileOutputStream fos = new FileOutputStream(filePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter ghiFile = new BufferedWriter(osw)
        ) {
            for (int i = 1; i <= 3; i++) {
                System.out.print("Nhập dòng " + i + ": ");
                String dong = banPhim.readLine(); // Đọc 1 dòng từ bàn phím
                
                ghiFile.write(dong);              // Ghi vào file
                ghiFile.newLine();                // Xuống dòng trong file
            }
            System.out.println("-> Đã ghi file thành công vào: " + filePath);
            
        } catch (IOException e) {
            System.out.println("Lỗi I/O khi ghi file: " + e.getMessage());
        }
        // -------------------------------------------------------------
        // PHẦN 2: ĐỌC FILE VÀ ĐÁNH SỐ THỨ TỰ
        // -------------------------------------------------------------
        System.out.println("\n--- NỘI DUNG FILE ĐÃ LƯU ---");
        
        // Sử dụng try-with-resources để tự động đóng luồng đọc file
        try (
            // Dùng Character Stream (Reader) kết hợp buffer để đọc UTF-8
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader docFile = new BufferedReader(isr)
        ) {
            String line;
            int stt = 1;
            
            // Đọc từng dòng. 
            // Khi line == null tức là đã đến điểm kết thúc luồng (EOF) (Mục tiêu 1)
            while ((line = docFile.readLine()) != null) {
                System.out.println(stt + ". " + line);
                stt++;
            }
        } catch (IOException e) {
            System.out.println("Lỗi I/O khi đọc file: " + e.getMessage());
        }
    }
}
