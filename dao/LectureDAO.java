package com.mentat.University.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mentat.University.Group;
import com.mentat.University.Lecture;
import com.mentat.University.Professor;
import com.mentat.University.Room;
import com.mentat.University.Subject;


public class LectureDAO {
	private static Logger logger = Logger.getLogger(LectureDAO.class);
	
	private DAOFactory factory = new DAOFactory();
	private Properties properties = factory.getDBproperties();
	
	public Lecture createLecture(Date date, String subjecttitle, long professorid, 
			String grup, String roomnumber) throws DAOException {
		logger.info("createLecture (" + date + " " + subjecttitle + " "+ professorid  + 
				" " + grup + " " + roomnumber +")");
		
		validateLecture(date, subjecttitle, professorid, grup, roomnumber);
		
		String sql = "INSERT INTO lecture (date, subject, professor, grup, room) VALUES (?,?,?,?,?)";

		Lecture lecture = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			Timestamp t = new Timestamp(date.getTime());
			statement.setTimestamp(1, t);
			statement.setString(2, subjecttitle);
			statement.setLong(3, professorid);
			statement.setString(4, grup);
			statement.setString(5, roomnumber);
			statement.execute();

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet.next();

			logger.trace("Creating Lecture");
			Date d = resultSet.getTimestamp(2);
			Subject subject = new SubjectDAO().getSubject(resultSet.getString(3));
			Professor professor = new ProfessorDAO().getProfessor(resultSet.getLong(4));
			Group group = new GroupDAO().getGroup(resultSet.getString(5));
			Room room = new RoomDAO().getRoom(resultSet.getString(6));
			lecture = new Lecture(d, subject, professor, group, room);
			
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
		return lecture;
	}
	
	public int deleteLecture(long id) throws DAOException {
		logger.info("deleteLecture(" + id + ")");

		if (!checkLectureExistanceInDB(id)) {
			throw new DAOException(
					"Lecture you trying to delete is not exist in DB: " + id);
		}

		String sql = "DELETE FROM lecture WHERE id = ?";
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
	
	public void validateLecture(Date date, String subjecttitle, long professorid, 
			String grup, String roomnumber) throws DAOException {
		
		logger.info("validateLecture (" + date + " " + subjecttitle + " "+ professorid  + 
				" " + grup + " " + roomnumber +")");

		Timestamp t = new Timestamp(date.getTime());
	
		String sql = "SELECT * FROM lecture WHERE date BETWEEN ?::timestamp without time zone - time '1:30' AND ?::timestamp without time zone + time '1:30';"; 
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);
			statement.setTimestamp(1, t);
			statement.setTimestamp(2, t);
			
			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				System.out.println("I'm in while loop");
				if (resultSet.getLong(4) == professorid) {
					throw new DAOException(
							"Professor you trying to add is busy on another lecture.");
				}

				if (resultSet.getString(5) == grup) {
					throw new DAOException(
							"Group you trying to add is busy in another lecture.");
				}

				if (resultSet.getString(6) == roomnumber) {
					throw new DAOException(
							"Room you trying to add to lecture is busy in another lecture.");
				}
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
	}
	
	public List<Lecture> getAllLectures() throws DAOException {
		logger.info("getAllLectures ()");
		
		String sql = "SELECT * FROM lecture ORDER BY date DESC;";
		
		ArrayList <Lecture> result = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			logger.trace("Open connection");
			connection = factory.getConnection(properties);

			logger.trace("Statement created");
			statement = connection.prepareStatement(sql);

			logger.trace("Get resultSet");
			resultSet = statement.getGeneratedKeys();
			resultSet = statement.executeQuery();
			result = new ArrayList<Lecture>();
			
			while (resultSet.next()) {
				logger.trace("Creating lecture");
				Date date = resultSet.getTimestamp(2);
				Subject subject = new SubjectDAO().getSubject(resultSet.getString(3));
				Professor professor = new ProfessorDAO().getProfessor(resultSet.getLong(4));
				Group group = new GroupDAO().getGroup(resultSet.getString(5));
				Room room = new RoomDAO().getRoom(resultSet.getString(6));
				Lecture lecture = new Lecture(date, subject, professor, group, room);
				result.add(lecture);
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
		
		return result;
	}
	public boolean checkLectureExistanceInDB(long id) throws DAOException {
		logger.info("checkLectureExistanceInDB (" + id + ")");

		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "SELECT * FROM lecture WHERE id ='" + id + "';";
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



















