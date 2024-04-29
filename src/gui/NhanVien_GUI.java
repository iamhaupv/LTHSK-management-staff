package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.*;
import javax.swing.AbstractCellEditor;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import connect.connectDB;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import entity.NhanVien;
import entity.PhongBan;












public class NhanVien_GUI extends JFrame implements ActionListener , MouseListener  {
	
	private JTable table;
	private JTextField txtMaNV;
	private JTextField txthoTen;
	private JTextField txtTuoi;
	private JTextField txtTienLuong;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnXoaTrang;
	private DefaultTableModel tableModel;
	private JLabel lblPhong;
	private JTextField txtPhong;
	private JComboBox cboPhong;
	private JButton btnSua;
	private PhongBan_DAO pb_dao;
	private NhanVien_DAO nv_dao;
	
	
	private String strName = "";
	private JButton btnXem;
	
  public NhanVien_GUI() {
	  
	// khá»Ÿi táº¡o káº¿t ná»‘i Ä‘áº¿n CSDL
	
	  	connectDB.getInstance().connect();
	  	pb_dao = new PhongBan_DAO();
	  	
	  	nv_dao = new NhanVien_DAO();
	
	  	
		setTitle("^-^");
		setSize(700, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		JLabel lblTieuDe;
		pnlNorth.add(lblTieuDe = new JLabel("THĂ”NG TIN NHĂ‚N VIĂ�N"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.BLUE);

		Box b = Box.createVerticalBox();

		Box b1, b2, b3, b4, b5;
		JLabel lblMaNV, lblhoTen, lblTuoi, lblTienLuong;

		b.add(b1 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		b1.add(lblMaNV = new JLabel("MĂ£ nhĂ¢n viĂªn: "));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox()); 
		b.add(Box.createVerticalStrut(10));
		b2.add(lblhoTen = new JLabel("Há»� tĂªn: "));
		b2.add(txthoTen = new JTextField());
		
		b.add(b3 = Box.createHorizontalBox()); 
		b.add(Box.createVerticalStrut(10));
		b3.add(lblTuoi = new JLabel("Tuá»•i: ")); 
		b3.add(txtTuoi = new JTextField());
		b.add(b4 = Box.createHorizontalBox()); 
		b.add(Box.createVerticalStrut(10));
		
		b4.add(lblPhong = new JLabel("MĂ£ phĂ²ng: ")); 
		
		//Táº¡o vĂ  Ä‘á»• dá»¯ liá»‡u vĂ o comboBox
		b4.add(cboPhong = new JComboBox<String>());
		cboPhong.setEditable(true);		
		ArrayList<PhongBan> lp = pb_dao.getallPhongBan();
		for (PhongBan p : lp) {
			cboPhong.addItem(p.getMaPhong());
		}
		
		b4.add(lblTienLuong = new JLabel("Tiá»�n lÆ°Æ¡ng: ")); 
		b4.add(txtTienLuong = new JTextField());
		
		lblhoTen.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhong.setPreferredSize(lblMaNV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());

		b.add(b5 = Box.createHorizontalBox()); 
		b.add(Box.createVerticalStrut(10));
		String [] headers = "MaNV;Há»� tĂªn;Tuá»•i;PhĂ²ng;Tiá»�n lÆ°Æ¡ng".split(";");
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		b5.add(scroll);
		add(b, BorderLayout.CENTER);
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.SOUTH);
		JPanel pnlLeft, pnlRight;
		split.add(pnlLeft = new JPanel());
		split.add(pnlRight = new JPanel());

		pnlLeft.add(new JLabel("Nháº­p mĂ£ sá»‘ cáº§n tĂ¬m: "));
		pnlLeft.add(txtTim = new JTextField(10));
		pnlLeft.add(btnTim = new JButton("TĂ¬m"));

		pnlRight.add(btnThem = new JButton("Them"));
		pnlRight.add(btnSua = new JButton("Sua"));
		pnlRight.add(btnXoa = new JButton("Xoa"));
		pnlRight.add(btnXoaTrang = new JButton("XoaTrang"));
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
		
		
		
		docDuLieuVaoTable();
		
	}
  public static void main(String[] args) {
	  new NhanVien_GUI().setVisible(true);
 }
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	Object o = arg0.getSource();
	if(o.equals(btnThem)) {
		addAction();
		clearAction();
	}
	if(o.equals(btnXoaTrang)) {
		clearAction();
	}
	if(o.equals(btnXoa)) {
		deleteAction();
	}
	if(o.equals(btnSua)) {
		updateNhanVien();
	}
	if(o.equals(btnTim)) {
		tableModel.setRowCount(0);
		searchNhanVien();
		
	}
}
public void deleteAction() {
	int row = table.getSelectedRow();
	if(row >= 0) {
		String ma = (String) table.getValueAt(row, 0);
		if(nv_dao.deleteNhanVien(ma)) {
			tableModel.removeRow(row);
			clearAction();
		}
	}
}
public void clearAction() {
	txtMaNV.setText("");
	txthoTen.setText("");
	txtTuoi.setText("");
	txtTienLuong.setText("");
}
 public void docDuLieuVaoTable() {
	 ArrayList<NhanVien> lnv = nv_dao.getallNhanVien();
	 for (NhanVien nv : lnv) {
		String dataRow [] = {nv.getMaNV(), nv.getHoTen(), nv.getTuoi()+"", nv.getPhong().getMaPhong(), nv.getTienLuong()+""};
		tableModel.addRow(dataRow);
	}
	 table.setModel(tableModel);
 }
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	int row = table.getSelectedRow();
	txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
	txthoTen.setText(tableModel.getValueAt(row, 1).toString());
	txtTuoi.setText(tableModel.getValueAt(row, 2).toString());
	cboPhong.setSelectedItem(tableModel.getValueAt(row, 3).toString());
	txtTienLuong.setText(tableModel.getValueAt(row, 4).toString());
	
}
public void addAction() {
	String ma = txtMaNV.getText();
	if(ma.isEmpty() || !ma.matches("\\d")) {
		JOptionPane.showMessageDialog(this, "sldjfslkd");
		txtMaNV.requestFocus();
		return;
	}
	String ten = txthoTen.getText();
	if(ten.isEmpty()) {
		JOptionPane.showMessageDialog(this, "slakjfdasl");
		txthoTen.requestFocus();
	}
	int tuoi = Integer.parseInt(txtTuoi.getText());
	PhongBan p = new PhongBan(cboPhong.getSelectedItem().toString());
	double tien = Double.parseDouble(txtTienLuong.getText());
	
	NhanVien n = new NhanVien(ma, ten, tuoi, p, tien);
	
	if(validata()) {
		try {
			
			if(nv_dao.addNhanVien(n)) {
				tableModel.addRow(new Object[] {n.getMaNV(), n.getHoTen(), n.getTuoi(), n.getPhong(), n.getTienLuong()});
			}
			else {
				JOptionPane.showMessageDialog(this, "Trung ma nhan vien");
				txtMaNV.requestFocus();
				txtMaNV.selectAll();
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Trung ma nhan vien");
			txtMaNV.requestFocus();
			txtMaNV.selectAll();
			return;
		}
	}
}
public boolean validata() {
	String ma = txtMaNV.getText().trim();
	String ten = txthoTen.getText();
	int tuoi = Integer.parseInt(txtTuoi.getText());
	PhongBan p = new PhongBan(cboPhong.getSelectedItem().toString());
	double tien = Double.parseDouble(txtTienLuong.getText());
	if(!(ma.length()>0)) {
		JOptionPane.showMessageDialog(this, "Chua nhap");
		txtMaNV.requestFocus();
		return false;
	}
	return true;
}
public void searchNhanVien() {
	DefaultTableModel tbModel = (DefaultTableModel) table.getModel();
	tbModel.setRowCount(0);
	strName = txtTim.getText();
	if(strName.isEmpty()) {
//		JOptionPane.showMessageDialog(this, "ldskjlsjfl");
//		return;
		docDuLieuVaoTable();
		return;
	}
	
	for (NhanVien nv : nv_dao.findByID(strName)) {
		Object dataRow[] = new Object[5];
		dataRow[0] = nv.getMaNV();
		dataRow[1] = nv.getHoTen();
		dataRow[2] = nv.getTuoi();
		dataRow[3] = nv.getPhong().getMaPhong();
		dataRow[4] = nv.getTienLuong();
		tableModel.addRow(dataRow);
	}
	table.setModel(tbModel);
}
public void updateNhanVien() {
	int row = table.getSelectedRow();
	String ma = txtMaNV.getText();
	String ten = txthoTen.getText();
	int tuoi = Integer.parseInt(txtTuoi.getText());
	PhongBan p = new PhongBan(cboPhong.getSelectedItem().toString());
	double tien = Double.parseDouble(txtTienLuong.getText());
	NhanVien n = new NhanVien(ma, ten, tuoi, p, tien);
	if(row >= 0) {
		if(nv_dao.updateNhanVien(n)) {
			table.setValueAt(txthoTen.getText(), row, 1);
			table.setValueAt(txtTuoi.getText(), row, 2);
			table.setValueAt(cboPhong.getSelectedItem(), row, 3);
			table.setValueAt(txtTienLuong.getText(), row, 4);
		}
	}
}
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

}
