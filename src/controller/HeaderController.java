package controller;

import dao.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import model.User;

import java.util.ArrayList;

public class HeaderController {

    @FXML
    private ComboBox<String> userComboBox;
    @FXML
    private Text currentUserTxt;
    @FXML
    private Button adminAreaBtn;

    private UserDAO userDAO;
    private MainApp mainApp;
    private User currentUser;


    public HeaderController() {
        userDAO = new UserDAO();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        adminAreaBtn.setVisible(false);
        if (mainApp == null || mainApp.getUser() == null) {
            currentUserTxt.setVisible(false);

        } else {
            currentUserTxt.setText(mainApp.getUser().getPseudo());
            currentUserTxt.setVisible(true);
        }

        if (mainApp != null && mainApp.getUser() != null) {
            User user = new User();
            try {
                user = (User) mainApp.getUser().clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            updateUserList(user);
            pickUser();
        } else {
            updateUserList(null);
        }


    }

    public void updateUserList(User prevUser) {
        if (!userComboBox.getItems().isEmpty()) {
            userComboBox.getItems().clear();
        }
        ArrayList<User> users = userDAO.getAll();
        int i = 0;
        int index = -1;
        userComboBox.getItems().add("Visitor");
        for (User user : users) {
            if (user.getId() != 1) {
                userComboBox.getItems().add(user.getId() + " - " + user.getPseudo());
                if (prevUser != null) {
                    if (prevUser.equals(user)) {
                        index = i;
                    }
                }

            }
            i++;
        }

        if (index != -1) {
            userComboBox.getSelectionModel().select(index);
        }else{
            userComboBox.getSelectionModel().select(0);
        }

    }

    @FXML
    private void pickUser() {


        /* updating current user */
        try {
            String parts[] = userComboBox.getSelectionModel().getSelectedItem().split("-", 2);
            currentUser = userDAO.selectById(Integer.valueOf(parts[0].trim()));
            currentUserTxt.setText(currentUser.getPseudo());
            currentUserTxt.setVisible(true);
            if (currentUser.isAdmin()) {
                adminAreaBtn.setVisible(true);
            } else {
                adminAreaBtn.setVisible(false);
            }
        } catch (NumberFormatException | NullPointerException e) {
            currentUser = null;
            currentUserTxt.setText(null);
            adminAreaBtn.setVisible(false);
        }

        mainApp.setUser(currentUser);
        /* if we are in the admin pane, we request the main app to show the topics */
        if(mainApp.getUser() != null){
            if (!mainApp.getUser().isAdmin() && mainApp.getLastPane().getMethodStr().equals("showAdminPane")) {
                mainApp.showTopicListPane();
            } else if (mainApp.getUser().isAdmin() && mainApp.getLastPane().getMethodStr().equals("showAdminPane")) {
                mainApp.showAdminPane(mainApp.getUser().getId());
            }
        }

        mainApp.refresh();


    }

    @FXML
    private void showTopics() {
        if (mainApp.getUser() != null) {
            User user = new User();
            try {
                user = (User) mainApp.getUser().clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            updateUserList(user);
            pickUser();
        }


        mainApp.showTopicListPane();
    }

    @FXML
    private void showAdminArea() {
        mainApp.showAdminPane(currentUser.getId());
    }
}