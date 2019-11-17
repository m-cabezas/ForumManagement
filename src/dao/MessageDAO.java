package dao;

import model.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MessageDAO implements DAO<Message> {

    private String tableName = "Message";
    private Connection conn = Connect.getInstance();

    @Override
    public void insert(Message message) {
        String query = "INSERT INTO " + tableName + " (content,date_of_creation,id_Post,id_User) VALUES ('" + message.getContent() + "','Now()','" + message.getPostId() + "','" + message.getUserId() + "')";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
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
                int id = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                int postId = resultSet.getInt(3);
                Date dateOfCreation = resultSet.getDate(4);
                String content = resultSet.getString(5);
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
            int userId = resultSet.getInt(2);
            int postId = resultSet.getInt(3);
            Date dateOfCreation = resultSet.getDate(4);
            String content = resultSet.getString(5);
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
    };
}