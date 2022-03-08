package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//connect ke database
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_db?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dao = data access object
		return connection;
	}
	
}
