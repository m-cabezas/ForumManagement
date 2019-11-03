package controller;

import dao.*;

public class AdminController {

	private TopicDAO topicDAO;
	private PostDAO postDAO;
	private UserDAO userDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}