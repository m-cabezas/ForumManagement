package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.User;

public class UserController {

	@FXML
    private Text pseudoTxt;
	@FXML
	private Text nameTxt;
	@FXML
	private Text surnameTxt;
	@FXML
	private Text ageTxt;
	@FXML
	private Text biographyTxt;
	@FXML
	private Text adminTxt;

	private UserDAO userDAO;
	private MainApp mainApp;

	private User user;
	private int userId;

	public UserController() {
		userDAO = new UserDAO();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}



	@FXML
	public void initialize(){
		if(userId != 0){
			user = userDAO.selectById(userId);
			pseudoTxt.setText(user.getPseudo());
			nameTxt.setText("Name: " + user.getName());
			surnameTxt.setText("Surname: " + user.getSurname());
			ageTxt.setText(String.valueOf("Age: " + user.getAge()));
			biographyTxt.setText("Biography: \n" + user.getBiography());

			if(user.isAdmin()){
				adminTxt.setVisible(true);
			}else{
				adminTxt.setVisible(false);
			}
		}
	}
}