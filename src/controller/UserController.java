package controller;

import dao.*;

public class UserController {

	private UserDAO userDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}