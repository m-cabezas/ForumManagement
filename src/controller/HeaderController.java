package controller;

import dao.*;

public class HeaderController {

	private UserDAO userDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}