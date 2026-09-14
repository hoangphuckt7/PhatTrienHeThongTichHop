package Lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab2 {
	private static Scanner scanner;
	private static boolean isExit = false;
	public static void MainLab2() {
		scanner = new Scanner(System.in);
		while (!isExit) {
			System.out.println("=================== Chọn bài muốn xem hoặc thoát ========");
			System.out.println("========= Bài 1: Quản lý sản phẩm – Tính đóng gói ========");
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

			default:
				isExit = true;
				break;
			}
		}
		scanner.close();
	}
	private static void Bai1() {
		List<SanPham> sp = new ArrayList<SanPham>();
		sp.add(new SanPham("000001","TIVI", 100000,2));
		sp.add(new SanPham("000002","TULANH", 200000,3));
		
		System.out.println("========= Chọn sản phẩm thực thi ========");
		System.out.println("========= TIVI ==========================");
		System.out.println("========= TULANH ========================");
		System.out.print("========= Chọn: ");
		String name = scanner.next();
		scanner.nextLine();
		SanPham spChon = sp.get(sp.indexOf(name));
		
		
	}
}
