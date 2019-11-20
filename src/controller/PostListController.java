package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Post;
import model.Topic;

import java.io.IOException;
import java.util.ArrayList;

public class PostListController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField descField;
	@FXML
	private VBox postBox;
	@FXML
	private Text topicNameTxt;
	@FXML
	private Text topicDescriptionTxt;
	@FXML
	private Button postBtn;
	@FXML
	private Text errorTxt;


	private MainApp mainApp;
	private Topic topic;
	private PostDAO postDAO;
	private ArrayList<Integer> topicAdministrators;

	public PostListController() {
		postDAO = new PostDAO();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public void setTopicAdministrators(ArrayList<Integer> topicAdministrators) {
		this.topicAdministrators = topicAdministrators;
	}

	@FXML
	public void initialize(){
		errorTxt.setVisible(false);
		enableUserRights(false);
		if(mainApp != null){
			if(mainApp.getUser() != null){
				enableUserRights(true);
			}
		}

		/* displaying post list */
		if(topic != null){
			topicNameTxt.setText(topic.getTopicName());
			topicDescriptionTxt.setText("Description: " + topic.getTopicDescription());

			if(!postBox.getChildren().isEmpty()){
				postBox.getChildren().clear();
			}
			ArrayList<Post> posts = postDAO.selectByColName("id_Topic", String.valueOf(topic.getId()));
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
					postController.setTopicAdministrators(topicAdministrators);
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
			post.setTopicId(topic.getId());
			post.setUserId(mainApp.getUser().getId());
			postDAO.insert(post);
			initialize();
			errorTxt.setVisible(false);
		}else{
			errorTxt.setText("ERROR: Empty Fields");
			errorTxt.setVisible(true);
		}
	}

	public void enableUserRights(boolean bool){
		if(bool){
			postBtn.setDisable(false);
			descField.setDisable(false);
			nameField.setDisable(false);
		}else{
			postBtn.setDisable(true);
			descField.setDisable(true);
			nameField.setDisable(true);
		}
	}

}