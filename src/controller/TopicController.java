package controller;

import dao.DAO;
import dao.MessageDAO;
import dao.PostDAO;
import dao.TopicDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button deleteTopicBttn;
    @FXML
    private Text managementInfoTxt;

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
            deleteTopicBttn.setVisible(false);
            managementInfoTxt.setVisible(false);
            if (mainApp.getUser() != null) {
                if ((topic.getAdministrators().contains(mainApp.getUser().getId()))) {
                    deleteTopicBttn.setVisible(true);
                    managementInfoTxt.setVisible(true);
                }
            }
        }
    }

    @FXML
    private void showPostList(){
        if(mainApp != null){
            mainApp.showPostListPane(topic);
        }
    }

    @FXML
    private void deleteTopic(){
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.delete(topic);
        mainApp.showTopicListPane();
    }
}
