package com.epam.testapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.epam.testapp.database.util.JdbcUtils;
import com.epam.testapp.exception.CannotGetConnectionException;

public final class ConnectionPool {

	private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
	private static final int DEFAULT_POOL_SIZE = 10;
	private static final String NOT_FOUND_DRIVAR_CLASS = "Could not load JDBC driver class.";

	private String driverClassName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	private BlockingQueue<Connection> freeConnections;
	private BlockingQueue<Connection> busyConnections;

	public ConnectionPool() {
	}

	public void init() throws SQLException {
		try {
			Class.forName(driverClassName);
			freeConnections = new ArrayBlockingQueue<Connection>(poolSize);
			busyConnections = new ArrayBlockingQueue<Connection>(poolSize);

			Connection connection = null;
			for (int i = 0; i < poolSize; i++) {
				connection = createConnection();
				freeConnections.add(connection);
			}
		} catch (ClassNotFoundException e) {
			LOGGER.error(NOT_FOUND_DRIVAR_CLASS, e);
			throw new IllegalStateException(NOT_FOUND_DRIVAR_CLASS, e);
		}
	}

	private Connection createConnection() throws SQLException {
		Connection connection = DriverManager
				.getConnection(url, user, password);
		return connection;
	}

	public Connection takeConnection() throws CannotGetConnectionException {
		Connection connection = null;
		try {
			connection = freeConnections.take();
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
		if (connection != null) {
			busyConnections.add(connection);
		} else {
			throw new CannotGetConnectionException();
		}
		return connection;
	}

	public void releaseConnection(Connection connection) {
		busyConnections.remove(connection);
		if (connection != null) {
			freeConnections.add(connection);
		}
	}

	private void closeConnection(BlockingQueue<Connection> connections) {
		Connection connection = null;

		while ((connection = connections.poll()) != null) {
			JdbcUtils.closeConnection(connection);
		}
	}

	public void dispose() {
		closeConnection(freeConnections);
		closeConnection(busyConnections);
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url.trim();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize < 1 ? poolSize : DEFAULT_POOL_SIZE;
	}
}
