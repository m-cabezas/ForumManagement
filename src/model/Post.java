package model;


public class Post {

	private int id;
	private int topicId;
	private int userId;
	private String postName;
	private String dateOfCreation;
	private String lastUpdate;
	private String description;

	public Post() {
	}

	public Post(int id, int topicId, int userId, String postName, String dateOfCreation, String lastUpdate, String description) {
		this.id = id;
		this.topicId = topicId;
		this.userId = userId;
		this.postName = postName;
		this.dateOfCreation = dateOfCreation;
		this.lastUpdate = lastUpdate;
		this.description = description;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}