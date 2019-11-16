package model;

public class Post {

	private int id;
	private int title;
	private int creator;
	private int dateOfCreation;
	private int lastUpdate;

	public Post() {
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(int dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public int getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(int lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}