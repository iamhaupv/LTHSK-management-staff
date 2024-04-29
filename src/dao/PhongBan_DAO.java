package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connect.connectDB;
import entity.PhongBan;

public class PhongBan_DAO {
	ArrayList<PhongBan> dsp;
	PhongBan p;
	public PhongBan_DAO() {
		// TODO Auto-generated constructor stub
		dsp = new ArrayList<PhongBan>();
		p = new PhongBan();
	}
	//
	public ArrayList<PhongBan> getallPhongBan(){
		ArrayList<PhongBan> danhSachP = new ArrayList<PhongBan>();
		try {
			connectDB.getInstance();
			Connection con = connectDB.getConnection();
			String sql = "select * from PhongBan";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				PhongBan p = new PhongBan(maPhong, tenPhong);
				danhSachP.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachP;
	}
}
