package dao;

import model.Topic;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TopicDAO implements DAO<Topic> {

    private String tableName = "Topic";
    private Connection conn = Connect.getInstance();

    @Override
    public void insert(Topic topic) {
        String query = "INSERT INTO " + tableName + " (topic_name,topic_description) VALUES ('" + topic.getTopicName() + "','" + topic.getTopicDescription() + "')";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Topic topic) {
        String query = "DELETE FROM " + tableName + " WHERE id =" + topic.getId();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectByColName(String colName, String value) {
        String query = "SELECT * FROM " + tableName + " WHERE " + colName + "=" + value;
        return getTopics(query);
    }

    @Override
    public ArrayList<Topic> getAll() {
        String query = "SELECT * FROM " + tableName;
        return getTopics(query);
    }

    private ArrayList<User> getUsers(String query) {
        Statement stmt = null;
        ArrayList<Topic> Topics = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String topicName =
                User user = new User(id,admin,age,biography,name,pseudo,surname);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void selectById(int id) {

    }
}