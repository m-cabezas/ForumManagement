package controller;

import dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Topic;
import model.User;

import java.util.ArrayList;

public class AdminController {

	private PostDAO postDAO;
	private TopicDAO topicDAO;
	private UserDAO userDAO;
	private MainApp mainApp;

	@FXML
	private TextField userPseudoTxtInpt;
	@FXML
	private TextField userNameTxtInpt;
	@FXML
	private TextField userSurnameTxtInpt;
	@FXML
	private TextField userAgeTxtInpt;
	@FXML
	private TextArea userBiographyTxtInpt;
	@FXML
	private TextField topicNameTxtInpt;
	@FXML
	private TextField topicDescriptionTxtInpt;
	@FXML
	private CheckBox userAdminChkbx;
	@FXML
	private VBox userListTxtBox;
	@FXML
	private VBox topicListTxtBox;
	@FXML
	private VBox userListBttnBox;
	@FXML
	private VBox topicListBttnBox;
	@FXML
	private Button switchBtn;
	@FXML
	private Text modeTxt;
	@FXML
	private Button userSubmitBttn;
	@FXML
	private Button topicSubmitBttn;
	@FXML
	private Button switchTopicModeBtn;
	@FXML
	private Text modeTopicTxt;

	private User userAdmin;
	private int userAdminId;
	private ArrayList<User> users;
	private ArrayList<Topic> topics;
	private int mode = 1;

	public AdminController() {
		userDAO = new UserDAO();
		topicDAO = new TopicDAO();
		postDAO = new PostDAO();
	}

	public void setUserId(int userAdminId) {
		this.userAdminId = userAdminId;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	private void deleteUserBtnHandler(User user) {
		users.remove(user);
		postDAO.updateUserId(user.getId());
		userDAO.delete(user);
		createUserList();
	}

	private void createUserList() {
		users = userDAO.getAll();
		userListTxtBox.getChildren().clear();
		userListBttnBox.getChildren().clear();
		for(User user: users){
			if(user.getId() != 1){
				VBox vBoxTxt = new VBox();
				VBox vBoxBttn = new VBox();
				vBoxTxt.setAlignment(Pos.CENTER_RIGHT);
				vBoxBttn.setAlignment(Pos.CENTER_LEFT);
				Button deleteButton = new Button("x");
				deleteButton.getStyleClass().add("bouton");
				Text userPseudoTxt = new Text();
				userPseudoTxt.setFill(Color.BLACK);
				userPseudoTxt.setText(user.getPseudo());
				userPseudoTxt.setCursor(Cursor.HAND);
				userPseudoTxt.setOnMouseClicked(mouseEvent -> fillUserInfo(user));
				deleteButton.addEventHandler(ActionEvent.ACTION, event -> deleteUserBtnHandler(user));

				vBoxTxt.setMinHeight(30);
				vBoxBttn.setMinHeight(30);
				vBoxTxt.getChildren().addAll(userPseudoTxt);
				vBoxBttn.getChildren().addAll(deleteButton);
				userListTxtBox.getChildren().add(vBoxTxt);
				userListBttnBox.getChildren().add(vBoxBttn);
			}
		}
	}

	private void deleteTopicBtnHandler(Topic topic) {
		topics.remove(topic);
		topicDAO.delete(topic);
		createTopicList();
	}

	private void createTopicList() {
		topics = userDAO.getTopicByUserId(userAdminId);
		topicListTxtBox.getChildren().clear();
		topicListBttnBox.getChildren().clear();
		for(Topic topic: topics){
			VBox vBoxTxt = new VBox();
			VBox vBoxBttn = new VBox();
			vBoxTxt.setAlignment(Pos.CENTER_RIGHT);
			vBoxBttn.setAlignment(Pos.CENTER_LEFT);
			vBoxTxt.setPrefWidth(100);
			vBoxBttn.setPrefWidth(100);
			Button deleteButton = new Button("x");
			deleteButton.getStyleClass().add("bouton");
			Text topicNameTxt = new Text();
			topicNameTxt.setFill(Color.BLACK);
			topicNameTxt.setText(topic.getTopicName());
			topicNameTxt.setOnMouseClicked(mouseEvent -> fillTopicInfo(topic));
			topicNameTxt.setCursor(Cursor.HAND);
			deleteButton.addEventHandler(ActionEvent.ACTION, event -> deleteTopicBtnHandler(topic));
			vBoxTxt.setMinHeight(30);
			vBoxBttn.setMinHeight(30);
			vBoxTxt.getChildren().addAll(topicNameTxt);
			vBoxBttn.getChildren().addAll(deleteButton);
			topicListTxtBox.getChildren().add(vBoxTxt);
			topicListBttnBox.getChildren().add(vBoxBttn);
		}
	}

	@FXML
	public void initialize(){
		switchUserMode();
		switchBtn.setOnMouseClicked(mouseEvent -> switchUserMode());
		switchTopicMode();
		switchTopicModeBtn.setOnMouseClicked(mouseEvent -> switchTopicMode());

		if(userAdminId != 0){
			userAdmin = userDAO.selectById(userAdminId);
			createUserList();
			createTopicList();
		}
	}

	/*
	* User Management
	*/

	private void addUser(){
		if(checkUserInfo()){
			User user = new User();
			user.setPseudo(userPseudoTxtInpt.getText());
			user.setSurname(userSurnameTxtInpt.getText());
			user.setName(userNameTxtInpt.getText());
			user.setBiography(userBiographyTxtInpt.getText());
			user.setAge(Integer.parseInt(userAgeTxtInpt.getText()));
			user.setAdmin(userAdminChkbx.isSelected());
			userDAO.insert(user);
			clearUserFields();
			switchUserMode();
			createUserList();
		}
	}

	private void updateUser(int userId){
		if(checkUserInfo()){
			User user = new User();
			user.setId(userId);
			user.setPseudo(userPseudoTxtInpt.getText());
			user.setSurname(userSurnameTxtInpt.getText());
			user.setName(userNameTxtInpt.getText());
			user.setBiography(userBiographyTxtInpt.getText());
			user.setAge(Integer.parseInt(userAgeTxtInpt.getText()));
			user.setAdmin(userAdminChkbx.isSelected());
			userDAO.update(user);
			clearUserFields();
			switchBtn.setVisible(false);
			createUserList();
			switchUserMode();
		}
	}


	private boolean checkUserInfo(){
		boolean correctAge = false;
		if(!userAgeTxtInpt.getText().isBlank()){
			if(Integer.parseInt(userAgeTxtInpt.getText()) > 0 && Integer.parseInt(userAgeTxtInpt.getText()) < 120 ){
				correctAge = true;
			}
		}
		return  !userPseudoTxtInpt.getText().isBlank() && !userNameTxtInpt.getText().isBlank() && !userSurnameTxtInpt.getText().isBlank() && correctAge && !userBiographyTxtInpt.getText().isBlank();
	}

	private void fillUserInfo(User user){
		userPseudoTxtInpt.setText(user.getPseudo());
		userSurnameTxtInpt.setText(user.getSurname());
		userNameTxtInpt.setText(user.getName());
		userAgeTxtInpt.setText(String.valueOf(user.getAge()));
		userBiographyTxtInpt.setText(user.getBiography());
		userAdminChkbx.setSelected(user.isAdmin());
		modeTxt.setText("Update User Mode");
		userSubmitBttn.setOnMouseClicked(mouseEvent -> updateUser(user.getId()));
		switchBtn.setVisible(true);
	}

	private void switchUserMode(){
		clearUserFields();
		switchBtn.setVisible(false);
		modeTxt.setText("Add User Mode");
		userSubmitBttn.setOnMouseClicked(mouseEvent -> addUser());
	}

	private void clearUserFields(){
		userPseudoTxtInpt.setText("");
		userSurnameTxtInpt.setText("");
		userNameTxtInpt.setText("");
		userAgeTxtInpt.setText("");
		userBiographyTxtInpt.setText("");
		userAdminChkbx.setSelected(false);
	}

	/*
	*  Topic Management
	*/

	private void addTopic(){ ;
		if(!topicNameTxtInpt.getText().isBlank() && !topicDescriptionTxtInpt.getText().isBlank()){
			Topic topic = new Topic();
			topic.addAdministrator(mainApp.getUser().getId());
			topic.setTopicDescription(topicDescriptionTxtInpt.getText());
			topic.setTopicName(topicNameTxtInpt.getText());
			topicDAO.insert(topic);
			mainApp.showHeaderPane();
			clearTopicFields();
			createTopicList();
		}
	}

	private void updateTopic(int topicId){
		if(!topicNameTxtInpt.getText().isBlank() && !topicDescriptionTxtInpt.getText().isBlank()){
			Topic topic = new Topic();
			topic.setId(topicId);
			topic.setTopicName(topicNameTxtInpt.getText());
			topic.setTopicDescription(topicDescriptionTxtInpt.getText());
			topicDAO.update(topic);
			switchTopicModeBtn.setVisible(false);
			clearTopicFields();
			switchTopicMode();
			createTopicList();
		}
	}

	private void fillTopicInfo(Topic topic){
		topicNameTxtInpt.setText(topic.getTopicName());
		topicDescriptionTxtInpt.setText(topic.getTopicDescription());
		modeTopicTxt.setText("Update Topic Mode");
		topicSubmitBttn.setOnMouseClicked(mouseEvent -> updateTopic(topic.getId()));
		switchTopicModeBtn.setVisible(true);
	}
	private void switchTopicMode(){
		clearTopicFields();
		modeTopicTxt.setText("Add Topic Mode");
		switchTopicModeBtn.setVisible(false);
		topicSubmitBttn.setOnMouseClicked(mouseEvent -> addTopic());
	}

	private void clearTopicFields(){
		topicDescriptionTxtInpt.setText("");
		topicNameTxtInpt.setText("");
	}
}