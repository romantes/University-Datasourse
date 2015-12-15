package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.*;

public class DAOFactory {

	private static final Logger logger = Logger.getLogger(DAOFactory.class);

	static {
		InputStream in = null;
		try {
			Properties props = new Properties();
			in = new FileInputStream(
					"/Users/apple/Java/University/University/src/main/resources/log4j.properties");
			props.load(in);
			PropertyConfigurator.configure(props);
			logger.trace("Properties loaded: " + props);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Properties getDBproperties() {
		InputStream in = null;
		Properties props = null;
		try {
			props = new Properties();
			in = new FileInputStream(
					"/Users/apple/Java/University/University/src/main/resources/db.properties");
			props.load(in);
			PropertyConfigurator.configure(props);
			logger.trace("DB properties loaded: " + props);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}

	public Connection getConnection(Properties properties) throws DAOException {
		Connection conn = null;
		try {
			Class.forName(properties.getProperty("db_driver"));
			String url = properties.getProperty("db_url");
			String user = properties.getProperty("db_username");
			String password = properties.getProperty("db_password");
			conn = DriverManager.getConnection(url, user, password);
			logger.trace(conn);
		} catch (SQLException | ClassNotFoundException e) {
			logger.error("Error in getting connection process...", e);
			throw new DAOException(e, "getConnectionMethod");
		}
		return conn;
	}

}