package dao;

import model.Message;

import java.sql.*;
import java.util.ArrayList;

public class MessageDAO implements DAO<Message> {

    private String tableName = "Message";
    private Connection conn = Connect.getInstance();

    @Override
    public void insert(Message message) {
        String query = "INSERT INTO " + tableName + " (content,date_of_creation,id_Post,id_User) VALUES ('" + message.getContent() + "', date('now'),'" + message.getPostId() + "','" + message.getUserId() + "')";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message){
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE Message SET content = ?, date_of_creation = ?, id_Post = ?, id_User = ? WHERE id = "+message.getId());
            statement.setString(1,message.getContent());
            statement.setDate(2, java.sql.Date.valueOf(message.getDateOfCreation()));
            statement.setInt(3,message.getPostId());
            statement.setInt(4, message.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Message message) {
        String query = "DELETE FROM " + tableName + " WHERE id =" + message.getId();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param colName
     * @param value
     */
    @Override
    public ArrayList<Message> selectByColName(String colName, String value) {
        String query = "SELECT * FROM " + tableName + " WHERE " + colName + "=" + value;
        return getMessages(query);
    }

    @Override
    public ArrayList<Message> getAll() {
        String query = "SELECT * FROM " + tableName;
        return getMessages(query);
    }

    private ArrayList<Message> getMessages(String query) {
        Statement stmt = null;
        ArrayList<Message> messages = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("id_User");
                int postId = resultSet.getInt("id_Post");
                String dateOfCreation = resultSet.getString("date_of_creation");
                String content = resultSet.getString("content");
                Message message = new Message(id,userId,postId,dateOfCreation,content);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public Message selectById(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE id=" + id;
        Statement stmt = null;
        Message message = new Message();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            int userId = resultSet.getInt("id_User");
            int postId = resultSet.getInt("id_Post");
            String dateOfCreation = resultSet.getString("date_of_creation");
            String content = resultSet.getString("content");
            message = new Message(id,userId,postId,dateOfCreation,content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
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

    public int countMessageByPost(int postId){
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE  id_Post =" + postId;
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

    public int countMessageByTopic(int topicId){
        String query = "SELECT COUNT(*) FROM " + tableName + " LEFT JOIN Post P on Message.id_Post = P.id WHERE id_Topic =" + topicId;
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

    /**
     * Replace post's author by the Undefined User
     * @param userId
     */
    public void updateUserId(int userId) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE Message SET id_User = ? WHERE id_User = "+ userId);
            statement.setInt(1, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}