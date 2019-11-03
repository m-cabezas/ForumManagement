package controller;

import dao.*;

public class MessageListController {

	private MessageDAO messageDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}