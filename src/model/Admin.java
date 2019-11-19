package model;

import java.util.*;

public class Admin extends User {

	private ArrayList<Integer> topics;

	public Admin() {
		super();
	}

	public void addTopic(Integer topicId){
		topics.add(topicId);
	}

	public ArrayList<Integer> getTopics() {
		return topics;
	}

}