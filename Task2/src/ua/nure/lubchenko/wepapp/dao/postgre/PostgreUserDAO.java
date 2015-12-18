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
	private static final Logger log = LogManager.getLogger();

	private static final String INSERT_USER = "INSERT INTO users values(default,?, ?, ?, ?, ?)";
	private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	private static final String UPDATE_USER = "UPDATE users SET email=?, password=?, name=?, surname=?, imageurl=? WHERE id=?";
	private static final String DELETE_USER = "DELETE FROM users WHERE id_user=?";
	private static final String SELECT_ALL = "SELECT * FROM users";

	@Override
	public User create(User user) throws DAOException {
		log.info("PostgreUserDAO#create user");
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
			log.error("SQLException during user insert query", ex);
			throw new DAOException(ex);
		} finally {
			close(connection);
		}
		log.exit(user);
		log.info("finished PostgreUserDAO#create user");
		return user;
	}

	@Override
	public User get(int id) {
		log.info("PostgreUserDAO # get user by ID");
		log.entry(id);

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
			log.error("SQLException during user getById query", ex);
		} finally {
			close(rs);
			close(connection);
		}
		log.exit(user);
		log.info("finished PostgreUserDAO # get user by ID");
		return user;
	}

	@Override
	public void update(User user) {
		log.info("PostgreUserDAO # update user");
		log.entry(user);
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
			log.error("SQLException during user update query", ex);			
		} finally {
			close(connection);
		}
		log.info("finished updating");
	}

	@Override
	public void remove(int id) {
		log.info("PostgreUserDAO # delete user by ID");
		log.entry(id);
		Connection connection = ConnectionHolder.getConnection();
		try {
			PreparedStatement pstm = connection.prepareStatement(DELETE_USER);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException ex) {
			log.error("SQLException during user delete query", ex);			
		}finally{
			close(connection);
		}
		log.info("finished removing");
	}

	@Override
	public List<User> getAll() throws DAOException {
		log.info("PostgreUserDAO # get all users from db");
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
			log.error("SQLException during user getAll query", ex);			
		} finally {
			close(rs);
			close(stm);
		}
		log.info("Finished getAll method");
		return usersList;
	}

	@Override
	public User getByEmail(String email) {
		log.info("PostgreUserDAO # get user by E-mail");
		log.entry(email);
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
			log.error("SQLException during user getByEmail query", ex);			
		} finally {
			close(rs);
			close(connection);
		}
		log.exit(user);
		log.info("finished PostgreUserDAO # get user by E-mail");
		return user;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		log.info("Extracting user from db...");

		User user = new User();
		user.setId(rs.getInt(Fields.USER_ID));
		user.setEmail(rs.getString(Fields.USER_EMAIL));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setName(rs.getString(Fields.USER_NAME));
		user.setSurname(rs.getString(Fields.USER_SURNAME));
		user.setImageUrl(rs.getString(Fields.USER_IMAGE_URL));
		log.info("Finished extrating user");
		log.exit(user);
		return user;
	}

	private void close(Connection con) {
		log.info("Closing connection resources...");
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				log.error("SQLException ocured while closing connection: "+e);
			}
		}
		log.info("Finished closing connection");
	}

	private void close(ResultSet rs) {
		log.info("Closing result set resources...");

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("SQLException ocured while closing result set: "+e);
			}
		}
		log.info("Finished closing result set");
	}
	
	private void close(Statement stmt) {
		log.info("Closing statement resources...");
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.error("SQLException ocured while closing statement: "+e);
			}
		}
		log.info("Finished closing statement");
	}
}
