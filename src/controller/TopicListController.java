package controller;

import dao.*;

public class TopicListController {

	private TopicDAO topicDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}