package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    //SQL QUERIES
    private static final String USER_PARAMETERS = "(username,email,password,city,country)";

    private static final String CREATE_USER_QUERY = "INSERT INTO users" + USER_PARAMETERS + " VALUES (?,?,?,?,?);";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ? ;";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET" + USER_PARAMETERS + " VALUES (?,?,?,?,?);";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?;";
    private static final String VERIFY_USER_QUERY = "SELECT * FROM users WHERE username = ?;";


    //Create User in database, returns user with id if created, or null if not
    public User create(User user) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertUserPrepStm = connection.prepareStatement
                     (CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setPrepStmParameters(insertUserPrepStm, user);
            try {
                insertUserPrepStm.executeUpdate();
            } catch (MySQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
            }
            try (ResultSet generatedKeys = insertUserPrepStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    user.setId(generatedKeys.getInt(1));
                    return user;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Reads User from database by user's id, returns user if found, or null if not
    public User read(Integer userId) {
        User userToReturn = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement readUserPrepStm = connection.prepareStatement(READ_USER_QUERY)) {
            readUserPrepStm.setInt(1, userId);
            ResultSet resultSet = readUserPrepStm.executeQuery();
            if(resultSet.next()){
                userToReturn = generateUserFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userToReturn;
    }

    //Updates User to database, returns same user if updated successfully, or null if data is incorrect
    public User update(User user) {
        User userToReturn = null;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement updateUserPrepStm = connection.prepareStatement(UPDATE_USER_QUERY);
            setPrepStmParameters(updateUserPrepStm, user);
            updateUserPrepStm.executeUpdate();
            userToReturn = user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userToReturn;
    }

    //Deletes User from database, returns true if deleted successfully, or false otherwise
    public Boolean delete(Integer userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement deleteUserPrepStm = connection.prepareStatement(DELETE_USER_QUERY);
            deleteUserPrepStm.setInt(1, userId);
            deleteUserPrepStm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Verifies the user with database, returns user if data is correct, or null when incorrect
    public User verify(String username, String password) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement verifyUserPrepStm = conn.prepareStatement(VERIFY_USER_QUERY)) {
            verifyUserPrepStm.setString(1, username);
            ResultSet resultSet = verifyUserPrepStm.executeQuery();
            if (resultSet.next()) {
                User userToReturn = generateUserFromResultSet(resultSet);
                if (BCrypt.checkpw(password, userToReturn.getPassword())) {
                    return userToReturn;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void setPrepStmParameters(PreparedStatement prepStm, User user) throws SQLException {
        prepStm.setString(1, user.getUsername());
        prepStm.setString(2, user.getEmail());
        prepStm.setString(3, user.getPassword());
        prepStm.setString(4, user.getCity());
        prepStm.setString(5, user.getCountry());
    }

    private User generateUserFromResultSet(ResultSet resultSet) throws SQLException {
        User userToReturn = new User(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("city"),
                resultSet.getString("country")
        );
        return userToReturn;
    }
}

