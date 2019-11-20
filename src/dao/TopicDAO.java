package dao;


import model.Topic;

import java.sql.*;
import java.util.ArrayList;

public class TopicDAO implements DAO<Topic> {

    private String tableName = "Topic";
    private Connection conn = Connect.getInstance();

    @Override
    public void insert(Topic topic) {
        try {
            String query = "INSERT INTO " + tableName + " (topic_name,topic_description) VALUES (?,?)";
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, topic.getTopicName());
            stmt.setString(2, topic.getTopicDescription());
            stmt.executeUpdate();

            query = "";
            for (Integer adminId : topic.getAdministrators()) {
                String querySelect = "SELECT id FROM " + tableName + " WHERE topic_name = ?";

                PreparedStatement stmtSelect = conn.prepareStatement(querySelect);
                stmtSelect.setString(1, topic.getTopicName());
                ResultSet res = stmtSelect.executeQuery();

                query = " INSERT INTO Administrate (id_User, id_Topic) VALUES(?,?); ";

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, adminId);
                stmt.setInt(2, res.getInt("id"));

                stmt.executeUpdate();

                /* Adding super user to the administrate table */
                if(adminId != 2){
                    query = "INSERT INTO Administrate (id_User, id_Topic) VALUES(?,?); ";
                    stmt = conn.prepareStatement(query);
                    stmt.setInt(1, 2);
                    stmt.setInt(2, res.getInt("id"));
                }


                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Topic topic) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE Topic SET topic_name = ?, topic_description = ? WHERE id = " + topic.getId());
            statement.setString(1, topic.getTopicName());
            statement.setString(2, topic.getTopicDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Topic topic) {

        String query = "DELETE FROM Administrate WHERE id_Topic =" + topic.getId();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "DELETE FROM " + tableName + " WHERE id =" + topic.getId();
        stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
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
                int id = resultSet.getInt("id");
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
                Topic topic = new Topic(id, topicName, topicDescription, adminIds);
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
            topic = new Topic(id, topicName, topicDescription, adminIds);
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
    }

    ;
}