package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.connectDB;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_DAO {
	ArrayList<NhanVien> dsnv;
	NhanVien nv;
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
		dsnv = new ArrayList<NhanVien>();
		nv = new NhanVien();
	}
	//
	public ArrayList<NhanVien> getallNhanVien(){
		ArrayList<NhanVien> danhSachNV = new ArrayList<NhanVien>();
		try {
			connectDB.getInstance();
			Connection con =  connectDB.getConnection();
			String sql = "select * from nhanvien";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString(1);
				String ten = rs.getString(2);
				int tuoi = Integer.parseInt(rs.getString(3));
				PhongBan p = new PhongBan(rs.getString(4));
				double tien = Double.parseDouble(rs.getString(5));
				NhanVien nv = new NhanVien(maNV, ten, tuoi, p, tien);
				danhSachNV.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachNV;
	}
	//
	public boolean addNhanVien(NhanVien nv) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into nhanvien values(?, ?, ?, ?, ?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getHoTen());
			stmt.setInt(3, nv.getTuoi());
			stmt.setString(4, nv.getPhong().getMaPhong());
			stmt.setDouble(5, nv.getTienLuong());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			return n < 0;
		}
		return n > 0;
	}
	//
	public boolean deleteNhanVien(String ma) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from nhanvien where manv = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	//
	public boolean updateNhanVien(NhanVien nv) {
		connectDB.getInstance();
		Connection con = connectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update nhanvien set hoten = ?, tuoi = ?, maphong = ?, tienLuong = ? where manv = ?");
			stmt.setString(1, nv.getHoTen());
			stmt.setInt(2, nv.getTuoi());
			stmt.setString(3, nv.getPhong().getMaPhong());
			stmt.setDouble(4, nv.getTienLuong());
			stmt.setString(5, nv.getMaNV());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public List<NhanVien> findByID(String name){
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select  * from nhanvien where manv = '" + name + "'" ;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString(1);
				String ten = rs.getString(2);
				int tuoi = Integer.parseInt(rs.getString(3));
				PhongBan p = new PhongBan(rs.getString(4));
				double tien = Double.parseDouble(rs.getString(5));
				NhanVien nv = new NhanVien(maNV, ten, tuoi, p, tien);
				dsnv.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
}
