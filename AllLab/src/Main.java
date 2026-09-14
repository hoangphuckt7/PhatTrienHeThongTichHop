import java.util.Scanner;

import Lab1.Lab1;
import Lab2.Lab2;

public class Main {
	private static Scanner scanner;
	private static boolean isExit = false;
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		while (!isExit) {
			System.out.println("================= Chọn bài Lab muốn xem hoặc thoát ======");
			System.out.println("================= Lab1: Bài Lab 1 =======================");
			System.out.println("================= Lab2: Bài Lab 2 =======================");
			System.out.println("========= Thoát chương trình (0) ========================");
			System.out.print("========= Chọn: ");
			int numb = scanner.nextInt();
			scanner.nextLine();
			switch (numb) {
			case 1:
				Lab1 BaiLab1 = new Lab1();
				BaiLab1.MainLab1();
				break;
			case 2:
				Lab2 BaiLab2 = new Lab2();
				BaiLab2.MainLab2();
				break;
			default:
				isExit = true;
				break;
			}
		}
		scanner.close();
	}

}
