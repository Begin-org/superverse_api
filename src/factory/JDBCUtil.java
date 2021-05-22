package factory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/superverse?useTimezone=true&serverTimezone=UTC";
	private static final String JDBC_USUARIO = "root";
	private static final String JDBC_SENHA = "";
	private static Driver driver = null;

	public static synchronized Connection getConnection() 
			throws	SQLException, ClassNotFoundException {
		if (driver == null) { 
			try { 
				Class jdbcClass = Class.forName( JDBC_DRIVER );
				driver = (Driver) jdbcClass.newInstance();
				DriverManager.registerDriver(driver);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
	}

	public static void close(Connection con) throws SQLException { 
		if (con != null) { 
			con.close();
		}
	}
	
}
