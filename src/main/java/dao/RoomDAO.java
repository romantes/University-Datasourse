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

import domain.Professor;
import domain.Room;

public class RoomDAO {
	private static Logger logger = Logger.getLogger(RoomDAO.class);

	private DAOFactory factory = new DAOFactory();
	private Properties properties = factory.getDBproperties();

	public Room createRoom(String roomnumber) throws DAOException {
		logger.info("createRoom (" + roomnumber + ")");

		if (checkRoomExistanceInDB(roomnumber)) {
			throw new DAOException(
					"Room you trying to create is already exist in DB: "
							+ roomnumber);
		}

		String sql = "INSERT INTO rooms (roomnumber) VALUES = ?;";

		Room Room = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); 
														
			statement.setString(1, roomnumber); 
			statement.execute();

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet.next();

			logger.trace("Creating Subject");
			Room = new Room(resultSet.getLong(1), resultSet.getString(2)); 
																			
																			
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
		return Room;	
	}	
	public Room getRoom(String roomnumber) throws DAOException {
		logger.info("getRoom (" + roomnumber + ")");

		if (!checkRoomExistanceInDB(roomnumber)) {
			throw new DAOException(
					"Room you trying to get is not exist in DB : "
							+ roomnumber);
		}
		String sql = "SELECT * FROM rooms WHERE roomnumber = ?;";

		Room Room = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, roomnumber);

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet = statement.executeQuery();
			resultSet.next();

			logger.trace("Creating Room");
			Room = new Room(resultSet.getLong(1),
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
		return Room;
	}
	
	public List<Room> getAllRooms() throws DAOException {
		logger.info("getAllRooms()");

		String sql = "SELECT * FROM rooms";

		List<Room> allRooms = null;
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

			logger.trace("Creating List<Rooms>");
			allRooms = new ArrayList<>();

			while (resultSet.next()) {
				Room room = new Room(resultSet.getLong(1),
						resultSet.getString(2));
				allRooms.add(room);
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
		return allRooms;
	}
	public int updateRoom(String oldroomnumber, String newroomnumber)
			throws DAOException {
		logger.trace("updateRoom(" + oldroomnumber + ", " + newroomnumber + ")");

		if (!checkRoomExistanceInDB(oldroomnumber)) {
			throw new DAOException(
					"Room you trying to update is not exist in DB: "
							+ oldroomnumber);
		}
		if (checkRoomExistanceInDB(newroomnumber)) {
			throw new DAOException("Room is already exists in DB :"
					+ newroomnumber);
		}

		String sql = "UPDATE rooms SET roomnumber = ? WHERE roomnumber = ?;";

		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, newroomnumber);
			statement.setString(2, oldroomnumber);

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
	public int deleteRoom(String roomnumber) throws DAOException {
		logger.info("deleteRoom(" + roomnumber + ")");

		if (!checkRoomExistanceInDB(roomnumber)) {
			throw new DAOException(
					"Room you trying to delete is not exist in DB: " + roomnumber);
		}

		String sql = "DELETE FROM rooms WHERE roomnumber = ?";
		int result = 0;
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setString(1, roomnumber);

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
	
	public boolean checkRoomExistanceInDB(String roomnumber) throws DAOException {
		logger.info("checkRoomExistanceInDB (" + roomnumber + ")");

		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT FROM rooms WHERE roomnumber ='" + roomnumber + "';";
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