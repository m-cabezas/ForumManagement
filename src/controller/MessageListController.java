package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Message;
import java.io.IOException;
import java.util.ArrayList;

public class MessageListController {

	@FXML
	private Button postBtn;
	@FXML
	private TextArea msgArea;
	@FXML
	private VBox msgBox;

	private MessageDAO messageDAO;
	private MainApp mainApp;
	private int postId;

	public MessageListController() {
		messageDAO = new MessageDAO();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@FXML
	public void initialize(){
		/* displaying message list */
		if(postId != 0){
			ArrayList<Message> messages = messageDAO.selectByColName("id_Post", String.valueOf(postId));
			for(Message message: messages){
				try{
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainApp.class.getResource("../view/MessagePane.fxml"));
					AnchorPane messagePane = (AnchorPane) loader.load();

					msgBox.getChildren().add(messagePane);

					//Allowing Controller to access the view
					MessageController messageController = loader.getController();
					messageController.setMainApp(mainApp);
					messageController.setMessage(message);
					messageController.initialize();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	private void addMessage(){
		if(!msgArea.getText().isBlank()){
			Message message = new Message();
			message.setContent(msgArea.getText());
			message.setUserId(mainApp.getUser().getId());
			message.setPostId(postId);
			messageDAO.insert(message);
			initialize();
		}
	}
}