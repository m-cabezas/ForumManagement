package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Topic;

import java.io.IOException;
import java.util.ArrayList;

public class TopicListController {

	@FXML
	private VBox topicBox;


	private DAO<Topic> topicDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public TopicListController() {
		topicDAO = new TopicDAO();
	}

	@FXML
	public  void initialize(){
		ArrayList<Topic> topics = topicDAO.getAll();

		for(Topic topic: topics){
			try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainApp.class.getResource("../view/MessagePane.fxml"));
				AnchorPane messagePane = (AnchorPane) loader.load();

				topicBox.getChildren().add(messagePane);

				//Allowing Controller to access the view
				TopicController topicController = loader.getController();
				topicController.setMainApp(mainApp);
				topicController.setTopic(topic);
				topicController.initialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}