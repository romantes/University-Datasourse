package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import domain.Group;
import domain.Professor;

public class ProfessorDAO {
	private static Logger logger = Logger.getLogger(ProfessorDAO.class);

	private DAOFactory factory = new DAOFactory();
	private Properties properties = factory.getDBproperties();

	public Professor createProfessor(String firstname, String secondname)
			throws DAOException {
		logger.info("createProfessor (" + firstname + " " + secondname + ")");

		String sql = "INSERT INTO professors (firstname, secondname) VALUES (?,?)";

		Professor Professor = null;
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
			statement.execute();

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet.next();

			logger.trace("Creating Subject");
			Professor = new Professor(resultSet.getLong(1),
					resultSet.getString(2), resultSet.getString(3));
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
		return Professor;
	}

	public Professor getProfessor(long id) throws DAOException {
		logger.info("getProfessor (" + id + ")");

		if (!checkProfessorExistanceInDB(id)) {
			throw new DAOException(
					"Professor you trying to get is not exist in DB : " + id);
		}

		String sql = "SELECT * FROM professors WHERE id = ?;";

		Professor professor = null;
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

			logger.trace("Creating Subject");
			professor = new Professor(resultSet.getLong(1),
					resultSet.getString(2), resultSet.getString(3));
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
		return professor;
	}

	public List<Professor> getAllProfessors() throws DAOException {
		logger.info("getAllProfessors()");

		String sql = "SELECT * FROM professors";

		List<Professor> allProfessors = null;
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

			logger.trace("Creating List<Professor>");
			allProfessors = new ArrayList<>();

			while (resultSet.next()) {
				Professor professor = new Professor(resultSet.getLong(1),
						resultSet.getString(2), resultSet.getString(3));
				allProfessors.add(professor);
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
		return allProfessors;
	}

	public int updateProfessor(long id, String column, String param)
			throws DAOException {
		logger.trace("updateProfessor(" + column + ", " + param + ")");

		if (!checkProfessorExistanceInDB(id)) {
			throw new DAOException(
					"Professor you trying to update is not exist in DB: " + id);
		}

		String sql = "UPDATE professors SET " + column + "= ? WHERE id = ?;";

		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, param);
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

	public int deleteProfessor(long id) throws DAOException {
		logger.info("deleteProfessor(" + id + ")");

		if (!checkProfessorExistanceInDB(id)) {
			throw new DAOException(
					"Professor you trying to delete is not exist in DB: " + id);
		}

		String sql = "DELETE FROM professors WHERE id = ?";
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

	public boolean checkProfessorExistanceInDB(long id) throws DAOException {
		logger.info("checkProfessorExistanceInDB (" + id + ")");

		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM professors WHERE id =" + id + ";";
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