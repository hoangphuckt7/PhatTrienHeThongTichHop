package Lab2;

public class SanPham {
	private String masp;
	private String tensp;
	private double gia;
	private double tonkho;
	
	public SanPham(String masp, String tensp, double gia, double tonkho) {
		this.masp = masp;
		this.tensp = tensp;
		this.gia = gia;
		this.tonkho = tonkho;
	}
	public void nhapHang(int soLuongNhap) {
		if(soLuongNhap <= 0) {
			System.out.println("Số lượng nhập lớn hơn 0");
		}else {			
			tonkho += (double)soLuongNhap;
		}
	}
	public double tinhThanhTien(int soLuong) {		
		return (gia * soLuong);
	}
	public boolean banHang(int soLuongBan) {
		if(soLuongBan <= 0) {
			System.out.println("Số lượng bán lớn hơn 0");
			return false;
		}
		if(soLuongBan > tonkho) {
			System.out.println("Số lượng bán quá số lượng trong kho");
			return false;
		}
		tonkho -= (double)soLuongBan;
		return true;
	}
	public void hienThiThongTin() {
		System.out.println("Tên sp: " + tensp + " Mã sp: " + masp + " Giá: " + gia + " Tồn kho: " + tonkho);
	}
}
