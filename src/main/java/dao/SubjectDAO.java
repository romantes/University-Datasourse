package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.*;

import org.apache.log4j.Logger;

public class SubjectDAO {
	private static Logger logger = Logger.getLogger(SubjectDAO.class);

	private DAOFactory factory = new DAOFactory();
	private Properties properties = factory.getDBproperties();

	public Subject createSubject(String subjecttitle) throws DAOException {
		logger.info("createSubject(" + subjecttitle + ")");
		
		if (checkObjectExistanceInDB(subjecttitle)) {
			throw new DAOException("Object you trying to create is already exist in DB");
		}
		
		Subject subject = null;
		String sql = "INSERT INTO subjects (subjecttitle) VALUES (?)";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, subjecttitle);
			statement.execute();

			logger.trace("Get result set");
			resultSet = statement.getGeneratedKeys();
			resultSet.next();

			logger.trace("Creating subject");
			subject = new Subject(resultSet.getLong(1), resultSet.getString(2));
			logger.trace("Subject: " + subject);

		} catch (SQLException e) {
			logger.error("Error in createSubject()");
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					logger.trace("ResultSet closed");
				}
				if (statement != null) {
					statement.close();
					logger.trace("Statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("Connection closed");
				}
			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				throw new DAOException(e);
			}

		}

		return subject;
	}

	public Subject getSubject(String subjecttitle) throws DAOException,
			DAOException {

		logger.info("getSubject(" + subjecttitle + ")");
		
		if (!checkObjectExistanceInDB(subjecttitle)) {
			throw new DAOException("Object you trying to get is not exist in DB");
		}
		
		String sql = "SELECT * FROM subjects WHERE subjecttitle = ?;";
		
		Subject subject = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, subjecttitle);

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet = statement.executeQuery();
			resultSet.next();
			
			logger.trace("Creating Subject");
			subject = new Subject(resultSet.getLong(1), resultSet.getString(2));

		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					logger.trace("ResultSet closed");
				}
				if (statement != null) {
					statement.close();
					logger.trace("Statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("Connection closed");
				}
			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return subject;
	}
	public List<Subject> getAllSubjects() throws DAOException {
		
		logger.info("getAllSubject()");

		String sql = "SELECT * FROM subjects";

		List<Subject> allSubject = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.createStatement();

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet = statement.executeQuery(sql);
			resultSet.next();

			logger.trace("Creating List<Subject>");
			allSubject = new ArrayList<>();

			while (resultSet.next()) {
				Subject subject = new Subject(resultSet.getLong(1),
						resultSet.getString(2));
				
				allSubject.add(subject);
			}

		} catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					logger.trace("ResultSet closed");
				}
				if (statement != null) {
					statement.close();
					logger.trace("Statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("Connection closed");
				}
			} catch (SQLException e) {
				logger.error(e);
				throw new DAOException(e);
			}
		}
		return allSubject;
	}
	
	public int deleteSubject(String subjecttitle) throws DAOException {
		logger.info("deleteSubject(" + subjecttitle + ")");
		String sql = "DELETE FROM subjects WHERE subjecttitle = ?";

		if (!checkObjectExistanceInDB(subjecttitle)) {
			throw new DAOException("Object you trying to delete is not exist in DB");
		}
		
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, subjecttitle);

			logger.trace("Get result");
			result = statement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					logger.trace("Statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("Connection closed");
				}
			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}

	public int updateSubject(String subjecttitle, String newsubjecttitle)
			throws DAOException {
		logger.trace("updateSubject(" + subjecttitle + ", " + newsubjecttitle
				+ ")");

		if (!checkObjectExistanceInDB(subjecttitle)) {
			throw new DAOException("Object you trying to update is not exist in DB");
		}
		if (checkObjectExistanceInDB(newsubjecttitle)) {
			throw new DAOException(newsubjecttitle + " is already exists in DB");
		}
		
		String sql = "UPDATE subjects SET subjecttitle = ? WHERE subjecttitle = ?;";

		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, newsubjecttitle);
			statement.setString(2, subjecttitle);

			result = statement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {

				if (statement != null) {
					statement.close();
					logger.trace("Statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("Connection closed");
				}
			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		return result;
	}
	
	public boolean checkObjectExistanceInDB(String subjecttitle) throws DAOException {
		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT FROM subjects WHERE subjecttitle ='"+ subjecttitle + "';";
		try{
			logger.trace("Open connection");
			connection = factory.getConnection(properties);
			
			logger.trace("Statement created");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				result = true;
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			try {

				if (statement != null) {
					statement.close();
					logger.trace("Statement closed");
				}
				if (connection != null) {
					connection.close();
					logger.trace("Connection closed");
				}
			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
				throw new DAOException(e);
			}
		}
		
		return result;
	}
}
