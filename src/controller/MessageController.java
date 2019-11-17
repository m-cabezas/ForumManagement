package controller;

import dao.MessageDAO;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Message;
import model.User;

public class MessageController {
    @FXML
    private Text pseudoTxt;
    @FXML
    private Text dateTxt;
    @FXML
    private Text contentTxt;

    private MainApp mainApp;
    private Message message;
    private UserDAO userDAO;
    private User user;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageController() {
        userDAO = new UserDAO();
    }

    @FXML
    public void initialize(){
        if(message != null){
            user = userDAO.selectById(message.getUserId());
            pseudoTxt.setText(user.getPseudo());
            dateTxt.setText("Date of creation: " + message.getDateOfCreation());
            contentTxt.setText(message.getContent());
        }

    }

    @FXML
    private void showUser(){
        mainApp.showUserPane(user.getId());
    }
}
