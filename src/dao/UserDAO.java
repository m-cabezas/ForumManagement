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
        System.out.println("insert");
        String query = "INSERT INTO " + tableName + " (admin,age,biography,name,pseudo,surname) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement prepStmt = conn.prepareStatement(query);
            prepStmt.setBoolean(1, user.isAdmin());
            prepStmt.setInt(2, user.getAge());
            prepStmt.setString(3, user.getBiography());
            prepStmt.setString(4, user.getName());
            prepStmt.setString(5, user.getPseudo());
            prepStmt.setString(6, user.getSurname());
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            String query = "UPDATE User SET admin = ?, age = ?, biography = ?, name = ?, pseudo = ?, surname = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setBoolean(1, user.isAdmin());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getBiography());
            statement.setString(4, user.getName());
            statement.setString(5, user.getPseudo());
            statement.setString(6, user.getSurname());
            statement.setInt(7, user.getId());

            statement.executeUpdate();

            if(!user.isAdmin()){
                query = "DELETE  FROM Administrate WHERE id_User = ?";
                PreparedStatement prepStmt = conn.prepareStatement(query);
                prepStmt.setInt(1, user.getId());
                prepStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            String query = "UPDATE Post SET id_User = 1 WHERE id_User = ?";
            PreparedStatement prepStmt = conn.prepareStatement(query);
            prepStmt.setInt(1, user.getId());
            prepStmt.executeUpdate();
            query = "UPDATE Message SET id_User = 1 WHERE id_User = ?";
            prepStmt = conn.prepareStatement(query);
            prepStmt.setInt(1, user.getId());
            prepStmt.executeUpdate();
            query = "DELETE  FROM Administrate WHERE id_User = ?";
            prepStmt = conn.prepareStatement(query);
            prepStmt.setInt(1, user.getId());
            prepStmt.executeUpdate();

            query = "DELETE FROM " + tableName + " WHERE id =" + user.getId();
            Statement stmt = null;
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
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
                int id = resultSet.getInt("id");
                boolean admin = resultSet.getBoolean("admin");
                int age = resultSet.getInt("age");
                String biography = resultSet.getString("biography");
                String name = resultSet.getString("name");
                String pseudo = resultSet.getString("pseudo");
                String surname = resultSet.getString("surname");
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
            boolean admin = resultSet.getBoolean("admin");
            int age = resultSet.getInt("age");
            String biography = resultSet.getString("biography");
            String name = resultSet.getString("name");
            String pseudo = resultSet.getString("pseudo");
            String surname = resultSet.getString("surname");
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

    public ArrayList<Topic> getTopicByUserId(int id) {
        String query = "SELECT * FROM Topic LEFT JOIN Administrate ON Topic.id=Administrate.id_Topic LEFT JOIN " + tableName + " ON Administrate.id_User=" + tableName + ".id WHERE " + tableName + ".id=" + id;
        Statement stmt = null;
        ArrayList<Topic> topics = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int topicId = resultSet.getInt("id");
                String topicName = resultSet.getString("topic_name");
                String topicDescription = resultSet.getString("topic_description");
                query = "SELECT id_User FROM Administrate WHERE id_Topic = ?";
                PreparedStatement prepStmt = conn.prepareStatement(query);
                prepStmt.setInt(1, id);
                ResultSet userRes = prepStmt.executeQuery();
                ArrayList<Integer> adminIds = new ArrayList<>();
                while (userRes.next()){
                    adminIds.add(userRes.getInt("id_user"));
                }
                Topic topic = new Topic(topicId,topicName,topicDescription, adminIds);
                topics.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }
}