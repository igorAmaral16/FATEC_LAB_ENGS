package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {
	private Connection con;

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		String hostName = "localhost";
		String dbName = "trabEng";
		String user = "sa";
		String senha = "123456";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		con = DriverManager.getConnection(String.format(
				"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", hostName, dbName, user, senha));

		return con;
	}

}
