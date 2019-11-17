package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Post;

import java.io.IOException;
import java.util.ArrayList;

public class PostListController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField descField;
	@FXML
	private VBox postBox;

	private MainApp mainApp;
	private int topicId;
	private PostDAO postDAO;

	public PostListController() {
		postDAO = new PostDAO();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setTopic(int topicId){
		this.topicId = topicId;
	}

	@FXML
	public void initialize(){

		/* displaying post list */
		if(topicId != 0){
			if(!postBox.getChildren().isEmpty()){
				postBox.getChildren().clear();
			}
			ArrayList<Post> posts = postDAO.selectByColName("id_Topic", String.valueOf(topicId));
			for(Post post: posts){
				try{
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainApp.class.getResource("../view/PostPane.fxml"));
					AnchorPane messagePane = (AnchorPane) loader.load();

					postBox.getChildren().add(messagePane);
					//Allowing Controller to access the view
					PostController postController = loader.getController();
					postController.setMainApp(mainApp);
					postController.setPost(post);
					postController.initialize();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@FXML
	private void addPost(){
		if(!nameField.getText().isBlank() && !descField.getText().isBlank()){
			Post post = new Post();
			post.setPostName(nameField.getText());
			post.setDescription(descField.getText());
			post.setTopicId(topicId);
			post.setUserId(mainApp.getUser().getId());
			postDAO.insert(post);
			initialize();
		}
	}

}