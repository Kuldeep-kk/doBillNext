package doBillNext;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class databaseconnector {
	
	Connection con=null;
	
	public static Connection dbConnector() {
	
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:src/MyDatabase/firstdb.db");
			
			return con;
			}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
			}
	}
	

}
