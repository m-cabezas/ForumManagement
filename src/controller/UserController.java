package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.User;

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

	private User user;

	public UserController() {
		userDAO = new UserDAO();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
	public void initialize(){
		if(user != null){
			pseudoTxt.setText(user.getPseudo());
			nameTxt.setText(user.getName());
			surnameTxt.setText(user.getSurname());
			ageTxt.setText(String.valueOf(user.getAge()));
			biographyTxt.setText(user.getBiography());
			userIdTxt.setText(String.valueOf(user.getId()));
			adminTxt.setText(String.valueOf(user.isAdmin()));
		}
	}
}