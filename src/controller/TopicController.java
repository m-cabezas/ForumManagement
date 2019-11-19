package controller;

import dao.DAO;
import dao.MessageDAO;
import dao.PostDAO;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Message;
import model.Post;
import model.Topic;

public class TopicController {

    @FXML
    private Text nameTxt;
    @FXML
    private Text nbPostTxt;
    @FXML
    private Text nbMsgTxt;
    @FXML
    private Text descTxt;

    private MainApp mainApp;
    private Topic topic;
    private PostDAO postDAO;
    private MessageDAO messageDAO;

    public TopicController() {
        postDAO = new PostDAO();
        messageDAO = new MessageDAO();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @FXML
    public void initialize(){
        if(topic != null){
            nameTxt.setText(topic.getTopicName());
            nbMsgTxt.setText("Number of Messages: " + messageDAO.countMessageByTopic(topic.getId()));
            nbPostTxt.setText("Number of Posts: " + postDAO.countPostByTopic(topic.getId()));
            descTxt.setText("Description: " + topic.getTopicDescription());
        }
    }

    @FXML
    private void showPostList(){
        if(mainApp != null){
            mainApp.showPostListPane(topic);
        }

    }
}
