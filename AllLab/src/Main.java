import java.util.Scanner;

import Lab1.Lab1;
import Lab2.Lab2;
import Lab4.Lab4;

public class Main {
	private static Scanner scanner;
	private static boolean isExit = false;
	public static void main(String[] args) throws Exception {
		scanner = new Scanner(System.in);
		while (!isExit) {
			System.out.println("================= Chọn bài Lab muốn xem hoặc thoát ======");
			System.out.println("================= Lab1: Bài Lab 1 =======================");
			System.out.println("================= Lab2: Bài Lab 2 =======================");
			System.out.println("================= Lab4: Bài Lab 4 =======================");
			System.out.println("========= Thoát chương trình (0) ========================");
			System.out.print("========= Chọn: ");
			int numb = scanner.nextInt();
			scanner.nextLine();
			switch (numb) {
				case 0:
					isExit = true;
					break;
				case 1:
					Lab1 BaiLab1 = new Lab1();
					BaiLab1.MainLab1();
					break;
				case 2:
					Lab2 BaiLab2 = new Lab2();
					BaiLab2.MainLab2();
					break;
				case 4:
					Lab4 BaiLab4 = new Lab4();
					BaiLab4.MainLab4();
					break;
				default:
					isExit = true;
					break;
			}
		}
		scanner.close();
	}

}
