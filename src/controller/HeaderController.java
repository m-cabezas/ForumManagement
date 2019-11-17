package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import model.User;

import java.util.ArrayList;

public class HeaderController {

	@FXML
	private ComboBox<String> userComboBox;
	@FXML
	private Text currentUserTxt;
	@FXML
	private Button adminAreaBtn;

	private UserDAO userDAO;
	private MainApp mainApp;
	private User currentUser;

	public HeaderController() {
		userDAO = new UserDAO();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void initialize(){
		adminAreaBtn.setVisible(false);
		if(mainApp == null || mainApp.getUser() == null){
			currentUserTxt.setVisible(false);
		}else{
			currentUserTxt.setText(mainApp.getUser().getPseudo());
			currentUserTxt.setVisible(true);
		}
		// TODO: Populate the userComboBox thanks to the userDao
		ArrayList<User> users = userDAO.getAll();
		for(User user : users){
			if(user.getId() != 1){
				userComboBox.getItems().add(user.getId() + " - " + user.getPseudo());
			}

		}
	}

	@FXML
	private void pickUser(){
		String parts[] = userComboBox.getSelectionModel().getSelectedItem().split("-" , 2);
		currentUser = userDAO.selectById(Integer.valueOf(parts[0].trim()));
		currentUserTxt.setText(currentUser.getPseudo());
		currentUserTxt.setVisible(true);
		if(currentUser.isAdmin()){
			adminAreaBtn.setVisible(true);
		}else{
			adminAreaBtn.setVisible(false);
		}
		mainApp.setUser(currentUser);
	}

	@FXML
	private void showTopics(){
		mainApp.showTopicListPane();
	}

	@FXML
	private void showAdminArea(){
		mainApp.showAdminPane(currentUser.getId());
	}
}