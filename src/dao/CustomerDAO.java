package dao;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import db.DBConnection;

public class CustomerDAO {

	Connection connection;
	
	public CustomerDAO() {
		try {
			initConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initConnection() throws SQLException {
		connection = DBConnection.connect();
		if(connection == null) {
			throw new SQLException("Connection");
		}
	}
	
//	public void getCustomerData() {
//		try {
//			Statement stmt = connection.createStatement();
//			String sql = "select * from customers";
//			ResultSet rs = stmt.executeQuery(sql);
//			//execute Query untuk getData
//			while(rs.next()) {
//				System.out.println("ID: " + rs.getString(1));
//				System.out.println("Name: " + rs.getString(2));
//				System.out.println("Phone: " + rs.getString(3));
//				System.out.println("City: " + rs.getString(4));
//				System.out.println("Gender: " + rs.getString(5));
//				System.out.println("");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public Vector<Vector<String>> getCustomerData() {
		Vector<Vector<String>> data = new Vector<>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select * from customers";
			ResultSet rs = stmt.executeQuery(sql);
			//execute Query untuk getData
			while(rs.next()) {
				Vector<String> rows = new Vector<>();
				rows.add(rs.getString(1));
				rows.add(rs.getString(2));
				rows.add(rs.getString(3));
				rows.add(rs.getString(4));
				rows.add(rs.getString(5));
				
				data.add(rows);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public void insertData(String name, String phone, String city, String gender) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "insert into customers values('"+ getLatestID() +"', '"+ name +"', '"+ phone +"', '"+ city +"', '"+ gender +"')";
			stmt.executeUpdate(sql);
			//tidak perlu ResultSet karena tidak ngambil data
			//executeUpdate untuk insert,update,delete
			System.out.println("Success Input Data");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLatestID() {
		String id = "";
		String newId = "";
		try {
			Statement stmt = connection.createStatement();
			String sql = "select customerID from customers ORDER BY customerID DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id = rs.getString(1);
			}
			int latestId = Integer.parseInt(id.substring(2));
			newId = "cs" + String.format("%03d", latestId + 1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return newId;
	}
	
	public void updateData(String id, String name, String phone, String city, String gender) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "update customers set name='" + name + "', phone='"+ phone +"', city='" + city 
					+"', gender='" + gender + "' where customerID='" + id + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteData(String id) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "delete from customers where customerID='" + id + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
