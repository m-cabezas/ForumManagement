package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.HistoryEntry;
import model.Post;
import model.Topic;
import model.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane mainPane;
    private User user;

    private MessageListController messageListController;
    private PostListController postListController;
    private HeaderController headerController;
    private UserController userController;
    private TopicListController topicListController;
    private AdminController adminController;
    private ArrayList<HistoryEntry> history = new ArrayList<HistoryEntry>();
    private int positionIndex = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Forum Management");

        initMainView();

        showHeaderPane();
        showTopicListPane();

    }

    private void initMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/MainView.fxml"));
            mainPane = (AnchorPane) loader.load();
            Scene scene = new Scene(mainPane);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (messageListController != null) {
            messageListController.enableUserRights(true);
        }
        if (postListController != null) {
            postListController.enableUserRights(true);
        }
    }

    public User getUser() {
        return user;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }

    public void showPostListPane(Topic topic, ArrayList<Integer> topicAdministrators) {
        setToHistory("showPostListPane", topic, topicAdministrators);
        headerController.updateDirectiveButtons();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/PostListPane.fxml"));
            AnchorPane postListPane = (AnchorPane) loader.load();
            // Getting the BorderPane of the MainView
            BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

            //Adding pane to the center of the borderPane
            mainBorderPane.setCenter(postListPane);

            //Allowing Controller to access the view
            postListController = loader.getController();
            postListController.setMainApp(this);
            postListController.setTopic(topic);
            postListController.setTopicAdministrators(topicAdministrators);
            postListController.initialize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHeaderPane() {
        try {
            if (headerController == null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("../view/HeaderPane.fxml"));
                AnchorPane headerPane = (AnchorPane) loader.load();
                // Getting the BorderPane of the MainView
                BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

                //Adding pane to the center of the borderPane
                mainBorderPane.setTop(headerPane);

                //Allowing Controller to access the view
                headerController = loader.getController();
                headerController.setMainApp(this);
            }
            headerController.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTopicListPane() {
        setToHistory("showTopicListPane", null, null);
        headerController.updateDirectiveButtons();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/TopicListPane.fxml"));
            AnchorPane topicListPane = (AnchorPane) loader.load();
            // Getting the BorderPane of the MainView
            BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

            //Adding pane to the center of the borderPane
            mainBorderPane.setCenter(topicListPane);

            //Allowing Controller to access the view
            topicListController = loader.getController();
            topicListController.setMainApp(this);
            topicListController.initialize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAdminPane(int userId) {
        setToHistory("showAdminPane", userId, null);
        headerController.updateDirectiveButtons();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/AdminPane.fxml"));
            AnchorPane adminPane = (AnchorPane) loader.load();
            // Getting the BorderPane of the MainView
            BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

            //Adding pane to the center of the borderPane
            mainBorderPane.setCenter(adminPane);

            //Allowing Controller to access the view
            adminController = loader.getController();
            adminController.setMainApp(this);
            adminController.setUserId(userId);
            adminController.initialize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessageListPane(Post post, ArrayList<Integer> topicAdministrators) {
        setToHistory("showMessageListPane", post, topicAdministrators);
        headerController.updateDirectiveButtons();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/MessageListPane.fxml"));
            AnchorPane messageListPane = (AnchorPane) loader.load();
            // Getting the BorderPane of the MainView
            BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

            //Adding pane to the center of the borderPane
            mainBorderPane.setCenter(messageListPane);

            //Allowing Controller to access the view
            messageListController = loader.getController();
            messageListController.setMainApp(this);
            messageListController.setPost(post);
            messageListController.setTopicAdministrators(topicAdministrators);
            messageListController.initialize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUserPane(int userId) {
        setToHistory("showUserPane", userId, null);
        headerController.updateDirectiveButtons();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/UserPane.fxml"));
            AnchorPane userPane = (AnchorPane) loader.load();
            // Getting the BorderPane of the MainView
            BorderPane mainBorderPane = (BorderPane) mainPane.getChildren().get(0);

            //Adding pane to the center of the borderPane
            mainBorderPane.setCenter(userPane);

            //Allowing Controller to access the view
            userController = loader.getController();
            userController.setUserId(userId);
            userController.setMainApp(this);
            userController.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setToHistory(String methodStr, Object methodParameter, Object secondMethodParameter) {
        HistoryEntry historyEntry = new HistoryEntry(methodStr, methodParameter, secondMethodParameter);
		System.out.println("position bf seth " + positionIndex);
		int index = history.size() -1;
		System.out.println("index historique bf seth " + index);
        if (history.size() != 0) {
            if (history.size() >= 2) {
                System.out.println(historyEntry.getMethodStr());
                HistoryEntry previousHistoryEntry = history.get(history.size() - 2);
                System.out.println(previousHistoryEntry.getMethodStr());
                System.out.println(" attributes verified : " + historyEntry.getMethodStr() + "!=" + previousHistoryEntry.getMethodStr() + " && " + historyEntry.getMethodParameter() + "!=" + previousHistoryEntry.getMethodParameter() + " && " + historyEntry.getSecondMethodParameter() + "!=" + previousHistoryEntry.getSecondMethodParameter());
//                if (!historyEntry.getMethodStr().equals(previousHistoryEntry.getMethodStr()) && !historyEntry.getMethodParameter().equals(previousHistoryEntry.getMethodParameter()) && !historyEntry.getSecondMethodParameter().equals(previousHistoryEntry.getSecondMethodParameter())) {
//                    System.out.println("we add " + historyEntry.getMethodStr());
//                    history.add(historyEntry);
//                    positionIndex++;
//                }
				if (!historyEntry.equals(previousHistoryEntry)) {
                    System.out.println("we add " + historyEntry.getMethodStr());
                    history.add(historyEntry);
                    positionIndex++;
                }
            } else {
                System.out.println("we add " + historyEntry.getMethodStr());
                history.add(historyEntry);
                positionIndex++;
            }
        } else {
            System.out.println("we add " + historyEntry.getMethodStr());
            history.add(historyEntry);
        }
        System.out.println("position af seth " + positionIndex);
        int index2 = history.size() -1;
        System.out.println("index historique af seth " + index2);
    }

    public ArrayList<HistoryEntry> getHistory() {
        return history;
    }

    public HistoryEntry getLastPane() {
        return history.get(history.size() - 1);
    }

    public HistoryEntry getSecondToLastPane() {
        return history.get(history.size() - 2);
    }

    public void refresh() {
        HistoryEntry historyEntry = getLastPane();
        callPane(historyEntry);
    }

    public void redirect(HistoryEntry historyEntry) {
        callPane(historyEntry);
    }

    private void callPane(HistoryEntry historyEntry) {
        String methodStr = historyEntry.getMethodStr();
        Object methodParameter = historyEntry.getMethodParameter();
        Object secondMethodParameter = historyEntry.getSecondMethodParameter();
        switch (methodStr) {
            case "showPostListPane":
                showPostListPane((Topic) methodParameter, (ArrayList<Integer>) secondMethodParameter);
                break;
            case "showTopicListPane":
                showTopicListPane();
                break;
            case "showAdminPane":
                showAdminPane((int) methodParameter);
                break;
            case "showMessageListPane":
                showMessageListPane((Post) methodParameter, (ArrayList<Integer>) secondMethodParameter);
                break;
            case "showUserPane":
                showUserPane((int) methodParameter);
                break;
            default:
                System.err.println("No case for last Pane");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}