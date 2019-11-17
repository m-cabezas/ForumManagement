package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Post;
import model.Topic;
import model.User;


import java.io.IOException;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane mainPane;
	private User user;

	private MessageListController messageListController;
	private PostListController postListController;
	private HeaderController headerController;
	private UserController userController;
	private TopicListController topicListController;
	private AdminController adminController;


	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Forum Management");

		initMainView();

		showHeaderPane();
		showTopicListPane();

	}

	private void initMainView() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/MainView.fxml"));
			mainPane = (AnchorPane) loader.load();
			Scene scene = new Scene(mainPane);

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void showPostListPane(Topic topic) {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/PostListPane.fxml"));
			AnchorPane postListPane = (AnchorPane) loader.load();
			// Getting the BorderPane of the MainView
			BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

			//Adding pane to the center of the borderPane
			mainBorderPane.setCenter(postListPane);

			//Allowing Controller to access the view
			postListController = loader.getController();
			postListController.setMainApp(this);
			postListController.setTopic(topic);
			postListController.initialize();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showHeaderPane() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/HeaderPane.fxml"));
			AnchorPane headerPane = (AnchorPane) loader.load();
			// Getting the BorderPane of the MainView
			BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

			//Adding pane to the center of the borderPane
			mainBorderPane.setTop(headerPane);

			//Allowing Controller to access the view
			headerController = loader.getController();
			headerController.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showTopicListPane() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/TopicListPane.fxml"));
			AnchorPane topicListPane = (AnchorPane) loader.load();
			// Getting the BorderPane of the MainView
			BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

			//Adding pane to the center of the borderPane
			mainBorderPane.setCenter(topicListPane);

			//Allowing Controller to access the view
			topicListController = loader.getController();
			topicListController.setMainApp(this);
			topicListController.initialize();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAdminPane() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/AdminPane.fxml"));
			AnchorPane adminPane = (AnchorPane) loader.load();
			// Getting the BorderPane of the MainView
			BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

			//Adding pane to the center of the borderPane
			mainBorderPane.setCenter(adminPane);

			//Allowing Controller to access the view
			adminController = loader.getController();
			adminController.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMessageListPane(Post post) {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/MessageListPane.fxml"));
			AnchorPane messageListPane = (AnchorPane) loader.load();
			// Getting the BorderPane of the MainView
			BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

			//Adding pane to the center of the borderPane
			mainBorderPane.setCenter(messageListPane);

			//Allowing Controller to access the view
			messageListController = loader.getController();
			messageListController.setMainApp(this);
			messageListController.setPost(post);
			messageListController.initialize();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showUserPane(int userId) {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/UserPane.fxml"));
			AnchorPane userPane = (AnchorPane) loader.load();
			// Getting the BorderPane of the MainView
			BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

			//Adding pane to the center of the borderPane
			mainBorderPane.setCenter(userPane);

			//Allowing Controller to access the view
			userController = loader.getController();
			userController.setUserId(userId);
			userController.setMainApp(this);
			userController.initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
			launch(args);
	}

}