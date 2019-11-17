package model;



public class Message {

    private int id;
    private int userId;
    private int postId;
    private String dateOfCreation;
    private String content;

    public Message() {
    }

    public Message(int id, int userId, int postId, String dateOfCreation, String content) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.dateOfCreation = dateOfCreation;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfCreation() {
        return this.dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}