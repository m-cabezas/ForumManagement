package dao;

import dao.DAO.*;
import javafx.geometry.Pos;
import model.Post;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class PostDAO implements DAO<Post> {

    private String tableName = "Post";
    private Connection conn = Connect.getInstance();

    @Override
    public void insert(Post post) {
        String query = "INSERT INTO " + tableName + " (id_Topic,post_name) VALUES ('" + post.getTopicId()+ "','" + post.getPostName() + "')";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Post post) {
        String query = "DELETE FROM " + tableName + " WHERE id =" + post.getId();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Post> selectByColName(String colName, String value) {
        String query = "SELECT * FROM " + tableName + " WHERE " + colName + "=" + value;
        return getPosts(query);
    }

    @Override
    public ArrayList<Post> getAll() {
        String query = "SELECT * FROM " + tableName;
        return getPosts(query);
    }

    private ArrayList<Post> getPosts(String query) {
        Statement stmt = null;
        ArrayList<Post> posts = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String postName = resultSet.getString(2);
                int topicId = resultSet.getInt(3);
                String postDescription = resultSet.getString(4);
                Date dateOfCreation = resultSet.getDate(5);
                int userId = resultSet.getInt(6);
                Date lastUpdate = resultSet.getDate(7);
                Post post = new Post(id,topicId,userId,postName,dateOfCreation,lastUpdate);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post selectById(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE id=" + id;
        Statement stmt = null;
        Post post = new Post();
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            String postName = resultSet.getString(2);
            int topicId = resultSet.getInt(3);
            String postDescription = resultSet.getString(4);
            Date dateOfCreation = resultSet.getDate(5);
            int userId = resultSet.getInt(6);
            Date lastUpdate = resultSet.getDate(7);
            post = new Post(id,topicId,userId,postName,dateOfCreation,lastUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
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