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
			System.out.println("========= Bài 1: Quản lý sản phẩm – Tính đóng gói =======");
			System.out.println("========= Bài 2: Quản lý người trong trường đại học =====");
			System.out.println("========= Thoát chương trình (0) ========================");
			System.out.print("========= Chọn: ");
			int numb = scanner.nextInt();
			scanner.nextLine();
			switch (numb) {
				case 0:
					isExit = true;
					break;
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
		List<SanPham> sp = new ArrayList<SanPham>();
		sp.add(new SanPham("000001", "TIVI", 100000, 2));
		sp.add(new SanPham("000002", "TULANH", 200000, 3));

		System.out.println("========= Chọn sản phẩm thực thi ========");
		System.out.println("========= TIVI ==========================");
		System.out.println("========= TULANH ========================");
		System.out.print("========= Chọn: ");
		String name = scanner.next();
		scanner.nextLine();
		// SanPham spChon = sp.get(sp.indexOf(name));
		SanPham spChon = null;
		for (SanPham s : sp) {
			if (s.getTenSP().equalsIgnoreCase(name)) {
				spChon = s;
				break;
			}
		}
		if (spChon != null) {
			System.out.println("-> Bạn đã chọn sản phẩm: " + spChon.getTenSP());
			spChon.hienThiThongTin();
		} else {
			System.out.println("-> Không tìm thấy sản phẩm có tên: " + name);
		}

	}

	private static void Bai2() {
		System.out.println("\n========= THÔNG TIN SINH VIÊN =========");

		SinhVien sv1 = new SinhVien("Nguyễn Văn A", 2003, "Hà Nội", "SV01", "CNTT", 8.6);
		SinhVien sv2 = new SinhVien("Nguyễn Văn B", 2004, "Hải Phòng", "SV02", "Kinh tế", 6.5);

		sv1.hienThiThongTin();
		sv2.hienThiThongTin();

		System.out.println("\n========= THÔNG TIN 2 GIẢNG VIÊN =========");
		GiangVien gv1 = new GiangVien("Lê Thị C", 1980, "Đà Nẵng", "GV01", "IT", 5000000, 2.5);
		GiangVien gv2 = new GiangVien("Trần Văn D", 1975, "TP HCM", "GV02", "Hệ thống thông tin", 5000000, 3.0);

		gv1.hienThiThongTin();
		gv2.hienThiThongTin();
	}
}
