package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.ResultSet;

import database.Connect;

public class MenuModel {
	Connect connect = Connect.getConnection();
	
	public Vector<Menu> select(){
		String query = "SELECT * FROM Menu";
		ResultSet rs = (ResultSet) connect.executeQuery(query);
		Vector<Menu> menus = new Vector<>();
		
		try {
			while(rs.next()) {
				String code = "", name = "";
				int price = 0, stock = 0;
				code = rs.getString("Code");
				name = rs.getString("Name");
				price = rs.getInt("Price");
				stock = rs.getInt("Stock");
				menus.add(new Menu(code, name, price, stock));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return menus;
	}
	
	public boolean Insert(String code, String name, int price, int stock) {
		String query = "INSERT INTO Menu VALUES (?, ?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean Update(String code, String name, int price, int stock) {
		String query = "UPDATE FROM Menu SET Name = ?, Price = ?, Stock = ? WHERE Code = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, stock);
			ps.setString(4, code);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean delete(String stock) {
		String query = "DELETE FROM Menu WHERE Code = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, stock);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}

}
