package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import domain.Student;

public class StudentDAO {
	private static Logger logger = Logger.getLogger(StudentDAO.class);

	private DAOFactory factory = new DAOFactory();
	private Properties properties = factory.getDBproperties();

	public Student createStudent(String firstname, String secondname,
			String groupnumber) throws DAOException {
		logger.info("createStudent (" + firstname + " " + secondname
				+ " in group " + groupnumber + ")");

		String sql = "INSERT INTO students (firstname, secondname, groupnumber) VALUES (?,?,?)";

		Student student = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, firstname);
			statement.setString(2, secondname);
			statement.setString(3, groupnumber);
			statement.execute();

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet.next();

			logger.trace("Creating Student");
			student = new Student(resultSet.getLong(1), resultSet.getString(2),
					resultSet.getString(3));
			student.setGoupNumber(resultSet.getString(4));
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
		return student;
	}

	public Student getStudent(long id) throws DAOException {
		logger.info("getStudent (" + id + ")");

		if (!checkStudentExistanceInDB(id)) {
			throw new DAOException(
					"Student you trying to get is not exist in DB : " + id);
		}
		String sql = "SELECT * FROM students WHERE id = ?;";

		Student student = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet = statement.executeQuery();
			resultSet.next();

			logger.trace("Creating Student");
			student = new Student(resultSet.getLong(1), resultSet.getString(2),
					resultSet.getString(3));
			student.setGoupNumber(resultSet.getString(4));
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
		return student;
	}
	
	public int updateStudent(long id, String field, String value)
			throws DAOException {
		logger.trace("updateStudent(" + field + ", " + value + ")");

		if (!checkStudentExistanceInDB(id)) {
			throw new DAOException("Student you trying to update is not exist in DB: "
					+ id);
		}

		String sql = "UPDATE students SET " + field + " = ? WHERE id = ?;";

		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, value);
			statement.setLong(2, id);

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
	
	public int deleteStudent(long id) throws DAOException {
		logger.info("deleteStudent(" + id + ")");

		if (!checkStudentExistanceInDB(id)) {
			throw new DAOException(
					"Student you trying to delete is not exist in DB: " + id);
		}

		String sql = "DELETE FROM students WHERE id = ?";
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);

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
	
	public int removeStudentFromGroup(long id) throws DAOException {
		logger.info("removeStudentFromGroup (" + id + " )");
		int result = 0;
		Connection connection = null;
		Statement statement = null;
		
		String sql ="UPDATE students SET groupnumber = null WHERE id = " + id + ";"; 
		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);
			
			logger.trace("Statement created");
			statement = connection.createStatement();
			result = statement.executeUpdate(sql);
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
	
	public boolean checkStudentExistanceInDB(long id) throws DAOException {
		logger.info("checkStudentExistanceInDB (" + id + ")");

		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM students WHERE id =" + id + ";";
		try {
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
