package com.example.itproger_9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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

    private  DB db = new DB();


    @FXML
    void initialize() {
        btn_reg.setOnAction(event -> {
            login_reg.setStyle("-fx-border-color: #fafafa");
            email_reg.setStyle("-fx-border-color: #fafafa");
            passw_reg.setStyle("-fx-border-color: #fafafa");
            btn_reg.setText("Зарегистрироваться");

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
            String pass = md5String(passw_reg.getCharacters().toString());
            try {
               boolean isReg=  db.regUser(login_reg.getCharacters().toString(), email_reg.getCharacters().toString(), pass);
               if(isReg){
                   login_reg.setText("");
                   email_reg.setText("");
                   passw_reg.setText("");
                   btn_reg.setText("Готово");
               }else
                   login_reg.setText("ВВедите другой логин");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        btn_auth.setOnAction(event -> {
            login_auth.setStyle("-fx-border-color: #fafafa");
            passw_auth.setStyle("-fx-border-color: #fafafa");
            btn_reg.setText("Зарегистрироваться");

            if(login_auth.getCharacters().length() <= 3){
                login_auth.setStyle("-fx-border-color: red");
                return;
            } else if(passw_auth.getCharacters().length() <= 3){
                passw_auth.setStyle("-fx-border-color: red");
                return;
            }
            String pass = md5String(passw_auth.getCharacters().toString());
            try {
                boolean isAuth=  db.authUser(login_auth.getCharacters().toString(), pass);
                if(isAuth){
                    FileOutputStream fos = new FileOutputStream("user.settings");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(new User(login_auth.getCharacters().toString()));
                    oos.close();

                    login_auth.setText("");
                    passw_auth.setText("");
                    btn_auth.setText("Готово");

                    Parent root  = FXMLLoader.load(getClass().getResource("main.fxml"));
                    Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Программа itProger");
                    stage.setScene(new Scene(root, 600, 400) );
                    stage.show();

                }else
                    btn_auth.setText("Не найден логин");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public  static String md5String(String pass){
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(pass.getBytes(StandardCharsets.UTF_8));
            digest = messageDigest.digest();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1,digest);
        String md5Hex = bigInteger.toString(16);
        while (md5Hex.length() < 32){
            md5Hex = "0"+md5Hex;
        }
        return md5Hex;
    }
}
