package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Message;
import model.Post;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class MessageListController {

    @FXML
    private Button postBtn;
    @FXML
    private TextArea msgArea;
    @FXML
    private VBox msgBox;
    @FXML
    private Text postNameTxt;
    @FXML
    private Text postDescriptionTxt;

    private MessageDAO messageDAO;
    private PostDAO postDAO;
    private MainApp mainApp;
    private Post post;
    private ArrayList<Integer> topicAdministrators;

    public MessageListController() {
        messageDAO = new MessageDAO();
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

    public Post getPost() {
        return post;
    }

    @FXML
    public void initialize() {
        enableUserRights(false);
        if (mainApp != null) {
            if (mainApp.getUser() != null) {
                enableUserRights(true);
            }
        }

        if (!msgBox.getChildren().isEmpty()) {
            msgBox.getChildren().clear();
        }

        /* displaying message list */
        if (post != null) {
            postNameTxt.setText(post.getPostName());
            postDescriptionTxt.setText("Description: " + post.getDescription());
            ArrayList<Message> messages = messageDAO.selectByColName("id_Post", String.valueOf(post.getId()));
            for (Message message : messages) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("../view/MessagePane.fxml"));
                    AnchorPane messagePane = (AnchorPane) loader.load();

                    msgBox.getChildren().add(messagePane);

                    //Allowing Controller to access the view
                    MessageController messageController = loader.getController();
                    messageController.setMainApp(mainApp);
                    messageController.setMessage(message);
                    messageController.setPost(post);
                    messageController.setTopicAdministrators(topicAdministrators);
                    messageController.initialize();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void addMessage() {

        if (!msgArea.getText().isBlank()) {
            Message message = new Message();
            message.setContent(msgArea.getText());
            message.setUserId(mainApp.getUser().getId());
            message.setPostId(post.getId());
            messageDAO.insert(message);
            Date date = new Date();
            post.setLastUpdate(new SimpleDateFormat("yyyy-MM-dd").format(date));
            postDAO.update(post);
            initialize();
        }
    }


    public void enableUserRights(boolean bool) {
        if (bool) {
            postBtn.setDisable(false);
        } else {
            postBtn.setDisable(true);
        }
    }
}