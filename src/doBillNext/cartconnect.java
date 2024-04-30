package doBillNext;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class cartconnect {

	Connection con=null;
	public static Connection cartConnector()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:src/MyDatabase/cartdb.db");

			return con;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
