package entity;

import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private int tuoi;
	private PhongBan phong;
	private double tienLuong;
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNV) {
		// TODO Auto-generated constructor stub
		this.maNV = maNV;
	}
	public NhanVien(String maNV, String hoTen, int tuoi, PhongBan phong, double tienLuong) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.tuoi = tuoi;
		this.phong = phong;
		this.tienLuong = tienLuong;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public PhongBan getPhong() {
		return phong;
	}
	public void setPhong(PhongBan phong) {
		this.phong = phong;
	}
	public double getTienLuong() {
		return tienLuong;
	}
	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", tuoi=" + tuoi + ", phong=" + phong + ", tienLuong="
				+ tienLuong + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	
}
