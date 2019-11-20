package controller;

import dao.MessageDAO;
import dao.PostDAO;
import dao.TopicDAO;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import model.Post;

import model.Topic;

import java.util.ArrayList;

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
    @FXML
    private Button deletePostBttn;
    @FXML
    private Text managementInfoTxt;

    private MainApp mainApp;
    private Post post;
    private PostDAO postDAO;
    private MessageDAO messageDAO;
    private UserDAO userDAO;
    private ArrayList<Integer> topicAdministrators;

    public PostController() {
        messageDAO = new MessageDAO();
        userDAO = new UserDAO();
        postDAO = new PostDAO();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setTopicAdministrators(ArrayList<Integer> topicAdministrators) {
        this.topicAdministrators = topicAdministrators;
    }

    @FXML
    public void initialize(){
        if(post != null){
            nameTxt.setText(post.getPostName());
            lastUpdateTxt.setText("Last Update: " + post.getLastUpdate());
            nbMsgTxt.setText("Number of messages: " + messageDAO.countMessageByPost(post.getId()));
            descTxt.setText("Description: " + post.getDescription());
            authorLink.setText(userDAO.selectById(post.getUserId()).getPseudo());
            deletePostBttn.setVisible(false);
            managementInfoTxt.setVisible(false);
            if(mainApp.getUser() != null){
                if ((post.getUserId() == mainApp.getUser().getId()) || (topicAdministrators.contains(mainApp.getUser().getId()))) {
                    deletePostBttn.setVisible(true);
                }
                if (topicAdministrators.contains(mainApp.getUser().getId())) {
                    managementInfoTxt.setVisible(true);
                }
            }
        }

    }

    @FXML
    private void showMessage(){
        mainApp.showMessageListPane(post,topicAdministrators);
    }

    @FXML
    private void showUser(){
        mainApp.showUserPane(post.getUserId());
    }

    @FXML
    private void deletePost() {
        TopicDAO topicDAO = new TopicDAO();
        postDAO.delete(post);
        mainApp.showPostListPane(topicDAO.selectById(post.getTopicId()),topicAdministrators);
    }
}
