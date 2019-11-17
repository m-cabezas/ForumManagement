package model;

import java.util.ArrayList;

public class User {

	private int id;
	private String pseudo;
	private String name;
	private String surname;
	private int age;
	private String biography;
	private boolean admin;

	public User() {
	}

	public User(int id, boolean admin, int age, String biography, String name, String pseudo, String surname) {
		this.id = id;
		this.pseudo = pseudo;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.biography = biography;
		this.admin = admin;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public boolean equals(Object obj) {
		User tmp = (User) obj;
		return tmp.getId() == this.getId();
	}
}