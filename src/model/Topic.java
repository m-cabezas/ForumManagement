package model;

import java.util.*;

public class Topic {

	private int id;
	private String topicName;
	private String topicDescription;
	private ArrayList<Integer> administrators;

	public Topic() {
		administrators = new ArrayList<>();
	}

	public Topic(int id, String topicName, String topicDescription, ArrayList<Integer> administrators) {
		this.id = id;
		this.topicName = topicName;
		this.topicDescription = topicDescription;
		this.administrators = administrators;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public void setAdministrators(ArrayList<Integer> administrators) {
		this.administrators = administrators;
	}

	public void addAdministrator(Integer adminId){
		administrators.add(adminId);
	}

	public ArrayList<Integer> getAdministrators() {
		return administrators;
	}
}