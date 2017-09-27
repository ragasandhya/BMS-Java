package com.capgemini.bmsapplication.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.security.auth.login.AccountException;

import oracle.jdbc.pool.OracleDataSource;

import com.capgemini.bmsapplication.util.DBConnection;

public class DBConnection {
	
	private static Connection conn = null;
	private static DBConnection instance = null;
	private static Properties props = null;
	private static OracleDataSource dataSource = null;

	private DBConnection() throws AccountException {
		try {
			props = loadProperties();
			dataSource = prepareDataSource();
		} catch (IOException e) {
			e.printStackTrace();
			throw new AccountException(
					" Could not read the database details from properties file ");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessage());
		}

	}
	public static DBConnection getInstance() throws AccountException {
		synchronized (DBConnection.class) {
			if (instance == null) {
				instance = new DBConnection();
			}
		}
		return instance;
	}
	public Connection getConnection() throws AccountException {
		try {

			conn = dataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountException(" Database connection problem");
		}
		return conn;
	}
	private Properties loadProperties() throws IOException {

		if (props == null) {
			Properties newProps = new Properties();
			String fileName = "jdbc/jdbc.properties";

			InputStream inputStream = new FileInputStream(fileName);
			newProps.load(inputStream);

			inputStream.close();

			return newProps;
		} else {
			return props;
		}
	}
	private OracleDataSource prepareDataSource() throws SQLException {

		if (dataSource == null) {
			if (props != null) {
				String connectionURL = props.getProperty("dburl");
				String username = props.getProperty("username");
				String password = props.getProperty("password");

				dataSource = new OracleDataSource();

				dataSource.setURL(connectionURL);
				dataSource.setUser(username);
				dataSource.setPassword(password);
			}
		}
		return dataSource;
	}
}
