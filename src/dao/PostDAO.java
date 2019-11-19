package dao;

import model.Post;

import java.sql.*;
import java.util.ArrayList;

public class PostDAO implements DAO<Post> {

    private String tableName = "Post";
    private Connection conn = Connect.getInstance();

    @Override
    public void insert(Post post) {
        String query = "INSERT INTO " + tableName + " (id_Topic,post_name,post_description,date_of_creation,last_update,id_User) VALUES (?,?,?,date('now'), date('now'),?)";
        try {
            PreparedStatement prepStmt = conn.prepareStatement(query);
            prepStmt.setInt(1, post.getTopicId());
            prepStmt.setString(2, post.getPostName());
            prepStmt.setString(3, post.getDescription());
            prepStmt.setInt(4, post.getUserId());
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Post post) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE Post SET id_Topic = ?, post_name = ?, post_description = ?, date_of_creation = ?, last_update = ?, id_User = ? WHERE id = "+ post.getId());
            statement.setInt(1, post.getTopicId());
            statement.setString(2, post.getPostName());
            statement.setString(3, post.getDescription());
            statement.setString(4, post.getDateOfCreation());
            statement.setString(5, post.getLastUpdate());
            statement.setInt(6, post.getUserId());
            statement.executeUpdate();
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
            stmt.executeUpdate(query);
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
                int id = resultSet.getInt("id");
                String postName = resultSet.getString("post_name");
                int topicId = resultSet.getInt("id_Topic");
                String postDescription = resultSet.getString("post_description");
                String dateOfCreation = resultSet.getString("date_of_creation");
                int userId = resultSet.getInt("id_User");
                String lastUpdate = resultSet.getString("last_update");
                Post post = new Post(id,topicId,userId,postName,dateOfCreation,lastUpdate,postDescription);
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
            String postName = resultSet.getString("post_name");
            int topicId = resultSet.getInt("id_Topic");
            String postDescription = resultSet.getString("post_description");
            String dateOfCreation = resultSet.getString("date_of_creation");
            int userId = resultSet.getInt("id_User");
            String lastUpdate = resultSet.getString("last_update");
            post = new Post(id,topicId,userId,postName,dateOfCreation,lastUpdate,postDescription);
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
    }

    public int countPostByTopic(int topicId){
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE  id_Topic = " + topicId;
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
            PreparedStatement statement = conn.prepareStatement("UPDATE Post SET id_User = ? WHERE id_User = "+ userId);
            statement.setInt(1, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}