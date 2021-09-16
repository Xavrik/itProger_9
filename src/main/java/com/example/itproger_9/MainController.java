package com.example.itproger_9;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_exit;

    @FXML
    void initialize() {
        btn_exit.setOnAction(event ->{
            try {
                FileOutputStream fos = new FileOutputStream("user.settings");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(new User(""));

                oos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }


            try {
                Parent root  = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Программа itProger");
                stage.setScene(new Scene(root, 600, 400) );
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}
