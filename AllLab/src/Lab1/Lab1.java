package Lab1;

import java.util.Scanner;

public class Lab1 {
	private static Scanner scanner;
	private static boolean isExit = false;
	public static void MainLab1() {
		scanner = new Scanner(System.in);
		while (!isExit) {
			System.out.println("=================== Chọn bài muốn xem hoặc thoát ========");
			System.out.println("========= Bài 1: in ra màn hình “Hello, World!” =========");
			System.out.println("========= Bài 2: nhập vào tên của bạn ===================");
			System.out.println("========= Bài 3: nhập vào 2 số A và B, in tổng ==========");
			System.out.println("========= Bài 4: nhập số in ra chẵn lẽ ==================");
			System.out.println("========= Bài 5: nhập tháng in ra tên TA ================");
			System.out.println("========= Thoát chương trình (0) ========================");
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
			case 3:
				Bai3();
				break;
			case 4:
				Bai4();
				break;
			case 5:
				Bai5();
				break;
			default:
				isExit = true;
				break;
			}
		}
		scanner.close();
	}
	private static void Bai1() {
		System.out.println("Hello, World!");
	}
	private static void Bai2() {
		System.out.println("What's your name?");
		String str = scanner.nextLine();
		System.out.println("Hi, I am "+str);
	}
	private static void Bai3() {
		System.out.print("Vui lòng nhập số hạng thứ nhất: ");
		int soA = scanner.nextInt();
		System.out.print("Vui lòng nhập số hạng thứ hai: ");
		int soB = scanner.nextInt();
		int kq = soA + soB;
		System.out.println("Tính tổng [" + soA + " + " + soB + "] = " + kq);
	}
	private static void Bai4() {
		System.out.println(">> Kiểm tra số chẳn lẽ <<");
		System.out.print("Vui lòng nhập số cần kiểm tra: ");
		int so = scanner.nextInt();
		if (so % 2 == 0) {
			System.out.println("Số " + so + " là số chẵn.");
		} else {
			System.out.println("Số " + so + " là số lẽ.");
		}
	}
	private static void Bai5() {
		boolean isrun = true;
		while (isrun) {
		System.out.print("Vui lòng nhập tháng: ");
		int so = scanner.nextInt();
		switch (so) {
			case 1:
				System.out.println("January");
				break;
			case 2:
				System.out.println("February");
				break;
			case 3:
				System.out.println("March");
				break;
			case 4:
				System.out.println("April");
				break;
			case 5:
				System.out.println("May");
				break;
			case 6:
				System.out.println("June");
				break;
			case 7:
				System.out.println("July");
				break;
			case 8:
				System.out.println("August");
				break;
			case 9:
				System.out.println("September");
				break;
			case 10:
				System.out.println("October");
				break;
			case 11:
				System.out.println("November");
				break;
			case 12:
				System.out.println("December");
				break;
			default:
				isrun = false;
				System.out.println("STOP");
				break;
			}
		}
	}
}