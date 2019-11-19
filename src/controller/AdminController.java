package controller;

import dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Admin;
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
	private TextField userBiographyTxtInpt;
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

	private User userAdmin;
	private int userAdminId;
	private ArrayList<User> users;
	private ArrayList<Topic> topics;

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
			VBox vBoxTxt = new VBox();
			VBox vBoxBttn = new VBox();
			vBoxTxt.setAlignment(Pos.CENTER_RIGHT);
			vBoxBttn.setAlignment(Pos.CENTER_LEFT);
			Button deleteButton = new Button("x");
			deleteButton.getStyleClass().add("bouton");
			Text userPseudoTxt = new Text();
			userPseudoTxt.setFill(Color.BLACK);
			userPseudoTxt.setText(user.getPseudo());
			deleteButton.addEventHandler(ActionEvent.ACTION, event -> deleteUserBtnHandler(user));

			vBoxTxt.setMinHeight(30);
			vBoxBttn.setMinHeight(30);
			vBoxTxt.getChildren().addAll(userPseudoTxt);
			vBoxBttn.getChildren().addAll(deleteButton);
			userListTxtBox.getChildren().add(vBoxTxt);
			userListBttnBox.getChildren().add(vBoxBttn);
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
		if(userAdminId != 0){
			userAdmin = userDAO.selectById(userAdminId);
			createUserList();
			createTopicList();
		}
	}

	@FXML
	private void addUser(){
		if(userInfoFilled()){
			User user = new User();
			user.setPseudo(userPseudoTxtInpt.getText());
			user.setSurname(userSurnameTxtInpt.getText());
			user.setName(userNameTxtInpt.getText());
			user.setBiography(userBiographyTxtInpt.getText());
			user.setAge(Integer.parseInt(userAgeTxtInpt.getText()));
			user.setAdmin(userAdminChkbx.isSelected());
			userDAO.insert(user);
			initialize();
		}
	}

	@FXML
	private void addTopic(){ ;
		if(!topicNameTxtInpt.getText().isBlank() && !topicDescriptionTxtInpt.getText().isBlank()){
			Topic topic = new Topic();
			topic.addAdministrator(mainApp.getUser().getId());
			topic.setTopicDescription(topicDescriptionTxtInpt.getText());
			topic.setTopicName(topicNameTxtInpt.getText());
			topicDAO.insert(topic);
			initialize();
		}
	}

	private boolean userInfoFilled(){
		boolean correctAge = false;
		if(!userAgeTxtInpt.getText().isBlank()){
			if(Integer.parseInt(userAgeTxtInpt.getText()) > 0 && Integer.parseInt(userAgeTxtInpt.getText()) < 120 ){
				correctAge = true;
			}
		}
		return  !userPseudoTxtInpt.getText().isBlank() && !userNameTxtInpt.getText().isBlank() && !userSurnameTxtInpt.getText().isBlank() && correctAge && !userBiographyTxtInpt.getText().isBlank();
	}
}