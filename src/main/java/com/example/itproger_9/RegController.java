package com.example.itproger_9;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegController {

    @FXML
    private TextField login_reg;

    @FXML
    private TextField email_reg;

    @FXML
    private PasswordField passw_reg;

    @FXML
    private CheckBox confidention;

    @FXML
    private Button btn_reg;

    @FXML
    private TextField login_auth;

    @FXML
    private PasswordField passw_auth;

    @FXML
    private Button btn_auth;

    @FXML
    void initialize() {
        btn_reg.setOnAction(event -> {
            login_reg.setStyle("-fx-border-color: #fafafa");
            email_reg.setStyle("-fx-border-color: #fafafa");
            passw_reg.setStyle("-fx-border-color: #fafafa");

            if(login_reg.getCharacters().length() <= 3){
                login_reg.setStyle("-fx-border-color: red");
                return;
            } else if (email_reg.getCharacters().length() <= 5){
                email_reg.setStyle("-fx-border-color: red");
                return;
            }else if(passw_reg.getCharacters().length() <= 3){
                passw_reg.setStyle("-fx-border-color: red");
                return;
            }else if(!confidention.isSelected()){
                btn_reg.setText("Поставьте галочку");
                return;
            }
        });
    }
}
