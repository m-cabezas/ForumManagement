package controller;

import dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
	private TextField userBiographyTxtInpt;
	@FXML
	private CheckBox userAdminChkbx;
	@FXML
	private VBox userListBox;
	@FXML
	private VBox topicListBox;

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
		if (!userListBox.getChildren().isEmpty()){
			userListBox.getChildren().clear();
		}
		for(User user: users){
			System.out.printf(user.getPseudo());
			HBox hbox = new HBox();
			hbox.getStyleClass().add("hbox");
			hbox.setSpacing(5);
			Button deleteButton = new Button("x");
			deleteButton.getStyleClass().add("bouton");
			Text userPseudoTxt = new Text();
			userPseudoTxt.setFill(Color.BLACK);
			userPseudoTxt.setText(user.getPseudo());
			deleteButton.addEventHandler(ActionEvent.ACTION, event -> deleteUserBtnHandler(user));
			hbox.getChildren().addAll(userPseudoTxt, deleteButton);
			userListBox.getChildren().add(hbox);
		}
	}

	private void deleteTopicBtnHandler(Topic topic) {
		topics.remove(topic);
		topicDAO.delete(topic);
		createTopicList();
	}

	private void createTopicList() {
		topics = userDAO.getTopicByUserId(userAdminId);
		topicListBox.getChildren().clear();
		for(Topic topic: topics){
			HBox hbox = new HBox();
			hbox.getStyleClass().add("hbox");
			hbox.setSpacing(5);
			Button deleteButton = new Button("x");
			deleteButton.getStyleClass().add("bouton");
			Text topicNameTxt = new Text();
			topicNameTxt.setFill(Color.BLACK);
			topicNameTxt.setText(topic.getTopicName());
			deleteButton.addEventHandler(ActionEvent.ACTION, event -> deleteTopicBtnHandler(topic));
			hbox.getChildren().addAll(topicNameTxt, deleteButton);
			topicListBox.getChildren().add(hbox);
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
}