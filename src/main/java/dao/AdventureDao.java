package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.Adventure;
import utils.DateUtil;
import utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;

public class AdventureDao {

    //SQL QUERIES
    private static final String ADVENTURE_PARAMETERS = "(user_id,type,title,content,start_date,end_date)";

    private static final String CREATE_ADVENTURE_QUERY = "INSERT INTO adventures" + ADVENTURE_PARAMETERS + " VALUES (?,?,?,?,?,?);";
    private static final String READ_ADVENTURE_QUERY = "SELECT * FROM adventures WHERE id = ? ;";
    private static final String READ_USERS_ADVENTURES_QUERY = "SELECT * FROM adventures WHERE user_id = ?;";
    private static final String UPDATE_ADVENTURE_QUERY = "UPDATE adventures SET user_id = ?,type = ?,title = ?,content = ?,start_date = ?,end_date = ?  WHERE id = ?;";
    private static final String DELETE_ADVENTURE_QUERY = "DELETE FROM adventures WHERE id = ?;";

    private static final String READ_ADVENTURES_FROM_TO = "SELECT * FROM adventures ORDER BY end_date DESC LIMIT ?, ?;";


    //Creates adventure in database, returns adventure with id if created, or null if not
    public Adventure create(Adventure adventure) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertAdvPrepStm = connection.prepareStatement
                     (CREATE_ADVENTURE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setPrepStmParameters(insertAdvPrepStm, adventure);
            try {
                insertAdvPrepStm.executeUpdate();
            } catch (MySQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
            }
            try (ResultSet generatedKeys = insertAdvPrepStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    adventure.setId(generatedKeys.getInt(1));
                    return adventure;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Reads adventure from database, returns adventure if found, or null if not
    public Adventure read(Integer adventureId) {
        Adventure advToReturn = null;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement readAdvPrepStm = connection.prepareStatement(READ_ADVENTURE_QUERY);
            readAdvPrepStm.setInt(1, adventureId);
            ResultSet resultSet = readAdvPrepStm.executeQuery();
            if (resultSet.next()) advToReturn = generateAdvFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return advToReturn;
    }

    //Reads adventure from database by user's id, returns arraylist of adventures if found, or null if not
    public ArrayList<Adventure> readByUserId(Integer userId) {
        ArrayList<Adventure> adventuresToReturn = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement readAdvsPrepStm = connection.prepareStatement(READ_USERS_ADVENTURES_QUERY);
            readAdvsPrepStm.setInt(1, userId);
            ResultSet resultSet = readAdvsPrepStm.executeQuery();
            while (resultSet.next()) {
                Adventure advToAdd = generateAdvFromResultSet(resultSet);
                adventuresToReturn.add(advToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adventuresToReturn;
    }

    //Reads adventures from some point to some point order by end date
    public ArrayList<Adventure> readFromTo(Integer from, Integer to) {

        ArrayList<Adventure> adventuresToReturn = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement readAdvsPrepStm = connection.prepareStatement(READ_ADVENTURES_FROM_TO);
            readAdvsPrepStm.setInt(1, from);
            readAdvsPrepStm.setInt(2, to);
            ResultSet resultSet = readAdvsPrepStm.executeQuery();
            while (resultSet.next()) {
                Adventure advToAdd = generateAdvFromResultSet(resultSet);
                adventuresToReturn.add(advToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adventuresToReturn;

    }


    //Updates adventure in database, returns updated adventure if updated successfully, null if not
    public Adventure update(Adventure adventure) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement updateAdvPrepStm = connection.prepareStatement(UPDATE_ADVENTURE_QUERY)) {
            setPrepStmParameters(updateAdvPrepStm, adventure);
            updateAdvPrepStm.setInt(7, adventure.getId());
            updateAdvPrepStm.executeUpdate();
            return adventure;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Deletes adventure from database, returns true if deleted, false if not
    public Boolean delete(Integer adventureId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement deleteAdvPrepStm = connection.prepareStatement(DELETE_ADVENTURE_QUERY);
            deleteAdvPrepStm.setInt(1, adventureId);
            deleteAdvPrepStm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private Adventure generateAdvFromResultSet(ResultSet resultSet) throws SQLException {
        Adventure advToReturn = new Adventure(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("type"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                DateUtil.stringToDate(resultSet.getString("start_date")),
                DateUtil.stringToDate(resultSet.getString("end_date")));
        return advToReturn;
    }

    private void setPrepStmParameters(PreparedStatement prepStm, Adventure adventure) throws SQLException {
        prepStm.setInt(1, adventure.getUserId());
        prepStm.setString(2, adventure.getType());
        prepStm.setString(3, adventure.getTitle());
        prepStm.setString(4, adventure.getContent());
        prepStm.setDate(5, Date.valueOf(adventure.getStartDate()));
        prepStm.setDate(6, Date.valueOf(adventure.getEndDate()));
    }

}
