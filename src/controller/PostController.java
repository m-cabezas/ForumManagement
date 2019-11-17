package controller;

import javafx.fxml.FXML;
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

    private MainApp mainApp;
    private Post post;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @FXML
    public void initialize(){

    }
}
