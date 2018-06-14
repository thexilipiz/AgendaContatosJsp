package agenda.model;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {
	InitialContext ctx;
	DataSource ds;
	Connection conn;
	
	public Connection getConnection() {
		try {	
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/agenda_db");						
			return ds.getConnection();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}

