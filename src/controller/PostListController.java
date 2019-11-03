package controller;

import dao.*;

public class PostListController {

	private PostDAO postDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}