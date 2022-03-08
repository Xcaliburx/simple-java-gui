import java.sql.Connection;

import dao.CustomerDAO;
import db.DBConnection;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainMenu();
		
		//cek connection
//		Connection connection = DBConnection.connect();
//		if(connection != null) {
//			System.out.println("Established");
//		}else {
//			System.out.println("Not established");
//		}
//		
//		CustomerDAO customerDAO = new CustomerDAO();
//		customerDAO.getCustomerData();
//		customerDAO.insertData();
//		System.out.println(customerDAO.getLatestID());
	}

}
