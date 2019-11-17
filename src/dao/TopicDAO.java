package dao;

import model.Topic;

import java.sql.*;
import java.time.ZoneId;
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
    public void update(Topic topic) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE Topic SET topic_name = ?, topic_description = ? WHERE id = "+ topic.getId());
            statement.setString(1, topic.getTopicName());
            statement.setString(2, topic.getTopicDescription());

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
    public ArrayList<Topic> selectByColName(String colName, String value) {
        String query = "SELECT * FROM " + tableName + " WHERE " + colName + "=" + value;
        return getTopics(query);
    }

    @Override
    public ArrayList<Topic> getAll() {
        String query = "SELECT * FROM " + tableName;
        return getTopics(query);
    }

    private ArrayList<Topic> getTopics(String query) {
        Statement stmt = null;
        ArrayList<Topic> topics = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String topicName = resultSet.getString(2);
                String topicDescription = resultSet.getString(3);
                Topic topic = new Topic(id,topicName,topicDescription);
                topics.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }

    @Override
    public Topic selectById(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE id=" + id;
        Statement stmt = null;
        Topic topic = new Topic();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            String topicName = resultSet.getString(2);
            String topicDescription = resultSet.getString(3);
            topic = new Topic(id,topicName,topicDescription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topic;
    }

    @Override
    public int countTableRow() {
        String query = "SELECT COUNT(*) FROM " + tableName;
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
    };
}