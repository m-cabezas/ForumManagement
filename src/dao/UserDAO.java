package dao;

import model.Topic;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class UserDAO implements DAO<User> {

    private String tableName = "User";
    private Connection conn = Connect.getInstance();

    @Override
    public void insert(User user) {
        String query = "INSERT INTO " + tableName + " (admin,age,biography,name,pseudo,surname) VALUES ('0','" + user.getAge() + "','" + user.getBiography() + "','" + user.getAge() + "','" + user.getPseudo() + "','" + user.getSurname() + "')";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE User SET admin = ?, age = ?, biography = ?, name = ?, pseudo = ?, surname = ? WHERE id = "+ user.getId());
            statement.setBoolean(1, user.isAdmin());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getBiography());
            statement.setString(4, user.getName());
            statement.setString(5, user.getPseudo());
            statement.setString(6, user.getSurname());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        String query = "DELETE FROM " + tableName + " WHERE id =" + user.getId();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> selectByColName(String colName, String value) {
        String query = "SELECT * FROM " + tableName + " WHERE " + colName + "=" + value;
        return getUsers(query);
    }

    @Override
    public ArrayList<User> getAll() {
        String query = "SELECT * FROM " + tableName;
        return getUsers(query);
    }

    private ArrayList<User> getUsers(String query) {
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                boolean admin = resultSet.getBoolean(6);
                int age = resultSet.getInt(3);
                String biography = resultSet.getString(4);
                String name = resultSet.getString(5);
                String pseudo = resultSet.getString(2);
                String surname = resultSet.getString(7);
                User user = new User(id,admin,age,biography,name,pseudo,surname);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User selectById(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE id=" + id;
        Statement stmt = null;
        User user = new User();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            boolean admin = resultSet.getBoolean(2);
            int age = resultSet.getInt(3);
            String biography = resultSet.getString(4);
            String name = resultSet.getString(5);
            String pseudo = resultSet.getString(6);
            String surname = resultSet.getString(7);
            user = new User(id,admin,age,biography,name,pseudo,surname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int countTableRow() {
        String query = "SELECT COUNT(*) FROM " + tableName + "";
        Statement stmt = null;
        int numberOfRows = 0;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            numberOfRows = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfRows;
    }
}