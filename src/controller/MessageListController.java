package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class MessageListController {

	@FXML
	private Button postBtn;
	@FXML
	private TextArea msgArea;
	@FXML
	private VBox msgBox;

	private MessageDAO messageDAO;
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public MessageListController() {
		
	}

	@FXML
	public void initialize(){

	}
}