package model;

import java.util.*;

public class Topic {

	private int id;
	private String topicName;
	private String topicDescription;

	public Topic() {
	}

	public Topic(int id, String topicName, String topicDescription) {
		this.id = id;
		this.topicName = topicName;
		this.topicDescription = topicDescription;
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
}