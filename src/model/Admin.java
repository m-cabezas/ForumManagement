package model;

import java.util.*;

public class Admin extends User {

	private ArrayList<Topic> topics;

	public Admin() {
		super();
	}

	public void createUser() {
		// TODO - implement Admin.createUser
		throw new UnsupportedOperationException();
	}

	public ArrayList<User> getUsers() {
		// TODO - implement Admin.getUsers
		throw new UnsupportedOperationException();
	}

	public void modifyUser(User user) {
		// TODO - implement Admin.modifyUser
		throw new UnsupportedOperationException();
	}

	public void addTopic(Topic topic){
		topics.add(topic);
	}

	public ArrayList<Topic> getTopics() {
		return topics;
	}

	public void removeTopic(Topic topic){

	}
}