package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class HeaderController {

	@FXML
	private ComboBox<String> userComboBox;
	@FXML
	private Text currentUserTxt;

	private UserDAO userDAO;
	private MainApp mainApp;

	public HeaderController() {
		userDAO = new UserDAO();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void initialize(){
		if(mainApp.getUser() == null){
			currentUserTxt.setVisible(false);
		}else{
			currentUserTxt.setText(mainApp.getUser().getPseudo());
			currentUserTxt.setVisible(true);
		}
		// TODO: Populate the userComboBox thanks to the userDao

	}

	@FXML
	public void pickUser(){

		userComboBox.getSelectionModel().getSelectedItem();
		// TODO: Set the mainApp user -> ex : mainApp.setUser(user)
	}
}