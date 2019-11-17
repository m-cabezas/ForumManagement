package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import model.User;

import java.util.ArrayList;

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
		if(mainApp == null || mainApp.getUser() == null){
			currentUserTxt.setVisible(false);
		}else{
			currentUserTxt.setText(mainApp.getUser().getPseudo());
			currentUserTxt.setVisible(true);
		}
		// TODO: Populate the userComboBox thanks to the userDao
		ArrayList<User> users = userDAO.getAll();
		for(User user : users){
//			if(user.getId() != 1){
				userComboBox.getItems().add(user.getId() + " - " + user.getPseudo());
//			}

		}
	}

	@FXML
	public void pickUser(){
		String parts[] = userComboBox.getSelectionModel().getSelectedItem().split("-" , 2);
		User currentUser = userDAO.selectById(Integer.valueOf(parts[0].trim()));
		currentUserTxt.setText(currentUser.getPseudo());
		currentUserTxt.setVisible(true);
		mainApp.setUser(currentUser);
	}
}