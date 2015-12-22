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

public class GroupDAO {
	private static Logger logger = Logger.getLogger(GroupDAO.class);

	private DAOFactory factory = new DAOFactory();
	private Properties properties = factory.getDBproperties();

	public Group createGroup(String groupnumber) throws DAOException {
		logger.info("createGroup (" + groupnumber + ")");

		if (checkGroupExistanceInDB(groupnumber)) {
			throw new DAOException(
					"Group you trying to create is already exist in DB: "
							+ groupnumber);
		}

		String sql = "INSERT INTO groups (groupnumber) VALUES (?)";

		Group group = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); 
			statement.setString(1, groupnumber); 
			statement.execute();

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet.next();

			logger.trace("Creating Group");
			group = new Group(resultSet.getLong(1),
					resultSet.getString(2)); //can be more
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
		return group;
	}
	public Group getGroup(String groupnumber) throws DAOException {
		logger.info("getGroup (" + groupnumber + ")");

		if (!checkGroupExistanceInDB(groupnumber)) {
			throw new DAOException(
					"Group you trying to get is not exist in DB : "
							+ groupnumber);
		}
		String sql = "SELECT * FROM groups WHERE groupnumber = ?;";

		Group group = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, groupnumber);

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet = statement.executeQuery();
			resultSet.next();

			logger.trace("Creating Group");
			group = new Group(resultSet.getLong(1),
					resultSet.getString(2));
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
		return group;
	}
	
	public List<Group> getAllGroups() throws DAOException {
		logger.info("getAllGreoup ()");

		String sql = "SELECT * FROM groups";

		List<Group> allGroups = null;
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

			logger.trace("Creating List<Group>");
			allGroups = new ArrayList<>();

			while (resultSet.next()) {
				Group group = new Group(resultSet.getLong(1),
						resultSet.getString(2));
				allGroups.add(group);
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
		return allGroups;
	}
	public int updateGroup(String oldgroupnumber, String newgroupnumber)
			throws DAOException {
		logger.trace("updateGroup(" + oldgroupnumber + ", " + newgroupnumber + ")");

		if (!checkGroupExistanceInDB(oldgroupnumber)) {
			throw new DAOException(
					"Group you trying to update is not exist in DB: "
							+ oldgroupnumber);
		}
		if (checkGroupExistanceInDB(newgroupnumber)) {
			throw new DAOException("Group is already exists in DB :"
					+ newgroupnumber);
		}

		String sql = "UPDATE groups SET groupnumber = ? WHERE groupnumber = ?;";

		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, newgroupnumber);
			statement.setString(2, oldgroupnumber);

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
	
	public int deleteGroup(String groupnumber) throws DAOException {
		logger.info("deleteGroup(" + groupnumber + ")");

		if (!checkGroupExistanceInDB(groupnumber)) {
			throw new DAOException(
					"Group you trying to delete is not exist in DB: " + groupnumber);
		}
		
		if (checkGroupIsHaveRefSudentsInDB(groupnumber)) {
			throw new DAOException(
					"Group you trying to delete contains students, remove students from group first");
		}
		
		String sql = "DELETE FROM groups WHERE groupnumber = ?";
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, groupnumber);

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
	
	public boolean checkGroupExistanceInDB(String groupnumber) throws DAOException {
		logger.info("checkGroupExistanceInDB (" + groupnumber + ")");

		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT FROM groups WHERE groupnumber ='" + groupnumber + "';";
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
	
	public boolean checkGroupIsHaveRefSudentsInDB(String groupnumber) throws DAOException {
		logger.info("checkGroupIsEmptyInDB (" + groupnumber + " )");

		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM students WHERE groupnumber ='" + groupnumber + "';";
		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			result = resultSet.next();
			
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
