package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class UserController {

	@FXML
    private Text pseudoTxt;
	private Text nameTxt;
	private Text surnameTxt;
	private Text ageTxt;
	private Text biographyTxt;
	private Text userIdTxt;
	private Text adminTxt;

	private UserDAO userDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}