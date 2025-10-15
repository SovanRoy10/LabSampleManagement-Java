package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lab_tracker";
	  private static final String JDBC_USER = "root";
	  private static final String JDBC_PASSWORD = "password";
	    
	  private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	  
	  private static Connection con = null;

	    
	    
	    // Loading and Registering the MySQL driver 
	    static 
	    {
			try {
				Class.forName(DRIVER_CLASS);
				con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			}

			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    
	    
	    
	    // method to used further 
	    public static Connection getCon()
	    {
	    	return con;
	    }
	
}
