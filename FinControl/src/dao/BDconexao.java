package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDconexao {

	private final String HOST = "localhost";
	private final int PORT = 3306;
	private final String DBNAME = "fincontrol";
	private final String LOGIN = "root";
	private final String SENHA = "";
	private Connection connection;

	public boolean connect() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;
			connection = DriverManager.getConnection(url, LOGIN, SENHA);
			return true;

		} catch (Exception erro) {
			System.out.println("Erro ao conectar" + erro.getMessage());
			return false;
		}
	}

	public boolean close() {
		
		try {
			
			if(connection != null) {
			connection.close();
			connection = null;
		}
		return true;
		
	} catch (SQLException erro) {

        System.out.println("Erro ao desconectar: "+ erro.getMessage());
        
        return false;
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

}