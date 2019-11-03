package model;

public class User {

	private int id;
	private String pseudo;
	private String name;
	private String surname;
	private int age;
	private String biography;
	private ArrayList<Message> messages;
	private boolean admin;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public ArrayList<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

}