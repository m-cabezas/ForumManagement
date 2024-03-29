package controller;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.User;

public class EditUserController {
    @FXML
    private TextField pseudoField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextArea bioArea;
    @FXML
    private Text adminTxt;
    @FXML
    private Text errorTxt;

    private UserDAO userDAO;
    private MainApp mainApp;
    private int userId = 0;

    public EditUserController() {
        userDAO = new UserDAO();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    public void initialize(){
        errorTxt.setVisible(false);
        if(userId != 0){
            User user = userDAO.selectById(userId);
            fillUserInfo(user);
        }

    }

    private void fillUserInfo(User user){
        pseudoField.setText(user.getPseudo());
        nameField.setText(user.getName());
        surnameField.setText(user.getSurname());
        ageField.setText(String.valueOf(user.getAge()));
        bioArea.setText(user.getBiography());
        if(user.isAdmin()){
            adminTxt.setVisible(true);
        }else{
            adminTxt.setVisible(false);
        }
    }

    @FXML
    private void saveUser(){
        if(checkUserInfo()){
            User modifiedUSer = new User();
            modifiedUSer.setId(userId);
            modifiedUSer.setPseudo(pseudoField.getText());
            modifiedUSer.setName(nameField.getText());
            modifiedUSer.setSurname(surnameField.getText());
            modifiedUSer.setAge(Integer.valueOf(ageField.getText()));
            modifiedUSer.setBiography(bioArea.getText());
            userDAO.update(modifiedUSer);
            mainApp.showUserPane(userId);
            errorTxt.setVisible(false);
        }
    }

    private boolean checkUserInfo(){
        boolean correctAge = false;
        boolean correct = false;
        if(!ageField.getText().isBlank()){
            try{
                if(Integer.parseInt(ageField.getText()) > 0 && Integer.parseInt(ageField.getText()) < 120 ){
                    correctAge = true;
                }else{
                    errorTxt.setText("ERROR: Unvalid Age");
                    errorTxt.setVisible(true);
                }
            }catch (NumberFormatException e){
                errorTxt.setText("ERROR: Unvalid Age");
                errorTxt.setVisible(true);
            }
        }
        if(correctAge){
            correct =  !pseudoField.getText().isBlank() && !nameField.getText().isBlank() && !surnameField.getText().isBlank() && correctAge && !bioArea.getText().isBlank();
            if(!correct){
                errorTxt.setText("ERROR: Empty Fields");
                errorTxt.setVisible(true);
            }
        }
        return correct;
    }


}
