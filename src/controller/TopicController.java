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
    private DAO<Post> postDAO;
    private DAO<Message> messageDAO;

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
            nbMsgTxt.setText("nb Message TODO"); // TODO nb msg
            nbPostTxt.setText(postDAO.countTableRow(topicId)); // TODO nb post
            descTxt.setText(topic.getTopicDescription());
        }
    }

    @FXML
    private void showPostList(){
        mainApp.showPostListPane(topic.getId());
    }
}
