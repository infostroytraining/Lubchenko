package ua.nure.lubchenko.wepapp.dao.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.db.ConnectionHolder;
import ua.nure.lubchenko.webapp.db.Fields;
import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.wepapp.dao.UserDAO;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class PostgreUserDAO implements UserDAO {

	private static final String INSERT_USER = "INSERT INTO users values(default, ?, ?, ?, ?, ?)";
	private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

	private static final Logger log = LogManager.getLogger();

	@Override
	public User create(User user) throws DAOException {
		log.entry(user);
		Connection connection = ConnectionHolder.getConnection();
		try {
			
			System.out.println(connection);
			PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			System.out.println(statement);
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getName());
			statement.setString(5, user.getSurname());
			statement.setString(6, user.getImageUrl());
			
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			log.error("SQLException during answer insert query", ex);
			throw new DAOException(ex);
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
		}
		return user;
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
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

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
