package agencia.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql implements IConexao {

	private final String HOSTNAME = "localhost";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	private final String SCHEMA = "globaltours";
	private final String PORT = "3306";
	private Connection conn;
	
	@Override
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + SCHEMA, USERNAME, PASSWORD);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void closeConnection() {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
