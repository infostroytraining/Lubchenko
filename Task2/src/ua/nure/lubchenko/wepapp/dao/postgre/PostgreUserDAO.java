package ua.nure.lubchenko.wepapp.dao.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.db.ConnectionHolder;
import ua.nure.lubchenko.webapp.db.Fields;
import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.wepapp.dao.UserDAO;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class PostgreUserDAO implements UserDAO {

	private static final String INSERT_USER = "INSERT INTO users values(default,?, ?, ?, ?, ?)";
	private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	private static final String UPDATE_USER = "UPDATE users SET email=?, password=?, name=?, surname=?, imageurl=? WHERE id=?";
	private static final String DELETE_USER = "DELETE FROM users WHERE id_user=?";
	private static final String SELECT_ALL = "SELECT * FROM users";

	

	private static final Logger log = LogManager.getLogger();

	@Override
	public User create(User user) throws DAOException {
		log.entry(user);
		Connection connection = ConnectionHolder.getConnection();
		try {

			PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			int columnsCounter = 0;
			statement.setString(++columnsCounter, user.getEmail());
			statement.setString(++columnsCounter, user.getPassword());
			statement.setString(++columnsCounter, user.getName());
			statement.setString(++columnsCounter, user.getSurname());
			statement.setString(++columnsCounter, user.getImageUrl());

			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			log.error("SQLException during answer insert query", ex);
			throw new DAOException(ex);
		} finally {
			close(connection);
		}
		log.exit(user);
		return user;
	}

	@Override
	public User get(int id) {
		Connection connection = ConnectionHolder.getConnection();
		User user = null;
		ResultSet rs = null;
		try {
			PreparedStatement pstm = connection.prepareStatement(GET_USER_BY_ID);
			pstm.setInt(1, id);
			pstm.execute();
			rs = pstm.getResultSet();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(connection);
		}
		return user;
	}

	@Override
	public void update(User user) {
		Connection connection = ConnectionHolder.getConnection();
		try {
			PreparedStatement pstm = connection.prepareStatement(UPDATE_USER);
			int columnsCounter = 0;
			pstm.setString(++columnsCounter, user.getEmail());
			pstm.setString(++columnsCounter, user.getPassword());
			pstm.setString(++columnsCounter, user.getName());
			pstm.setString(++columnsCounter, user.getSurname());
			pstm.setString(++columnsCounter, user.getImageUrl());

			pstm.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			close(connection);
		}
	}

	@Override
	public void remove(int id) {
		Connection connection = ConnectionHolder.getConnection();
		try {
			PreparedStatement pstm = connection.prepareStatement(DELETE_USER);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<User> getAll() throws DAOException {
		Connection connection = ConnectionHolder.getConnection();
		List<User> usersList = new ArrayList<User>();
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = connection.createStatement();
			stm.executeQuery(SELECT_ALL);
			rs = stm.getResultSet();

			while (rs.next()) {
				usersList.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			System.out.println(usersList);
			close(rs);
			close(stm);
		}
		return usersList;
	}

	@Override
	public User getByEmail(String email) {
		Connection connection = ConnectionHolder.getConnection();
		User user = null;
		ResultSet rs = null;
		try {
			PreparedStatement pstm = connection.prepareStatement(GET_USER_BY_EMAIL);
			int k = 1;
			pstm.setString(k++, email);
			pstm.execute();
			rs = pstm.getResultSet();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(connection);
		}
		return user;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt(Fields.USER_ID));
		user.setEmail(rs.getString(Fields.USER_EMAIL));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setName(rs.getString(Fields.USER_NAME));
		user.setSurname(rs.getString(Fields.USER_SURNAME));
		user.setImageUrl(rs.getString(Fields.USER_IMAGE_URL));

		return user;
	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
