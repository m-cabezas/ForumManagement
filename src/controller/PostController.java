package controller;

import dao.MessageDAO;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import model.Post;

public class PostController {
    @FXML
    private Text nameTxt;
    @FXML
    private Text lastUpdateTxt;
    @FXML
    private Text nbMsgTxt;
    @FXML
    private Text descTxt;
    @FXML
    private Hyperlink authorLink;

    private MainApp mainApp;
    private Post post;
    private MessageDAO messageDAO;
    private UserDAO userDAO;

    public PostController() {
        messageDAO = new MessageDAO();
        userDAO = new UserDAO();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @FXML
    public void initialize(){
        if(post != null){
            nameTxt.setText(post.getPostName());
            lastUpdateTxt.setText("Last Update: " + post.getLastUpdate());
            nbMsgTxt.setText("Number of message: " + messageDAO.countMessageByPost(post.getId()));
            descTxt.setText("Description: " + post.getDescription());
            authorLink.setText(userDAO.selectById(post.getUserId()).getPseudo());
        }

    }

    @FXML
    private void showMessage(){
        mainApp.showMessageListPane(post);
    }

    @FXML
    private void showUser(){
        mainApp.showUserPane(post.getUserId());
    }
}
