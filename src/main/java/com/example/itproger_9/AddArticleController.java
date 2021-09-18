package com.example.itproger_9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddArticleController {

    @FXML
    private TextField title_field;

    @FXML
    private Button btn_add;

    @FXML
    private TextArea inro_filed;

    @FXML
    private TextArea text_filed1;

    private  DB db = new DB();

    @FXML
    void initialize(){
        btn_add.setOnAction(event ->{
                 title_field.setStyle("-fx-border-color: #fafafa");
                inro_filed.setStyle("-fx-border-color: #fafafa");
                text_filed1.setStyle("-fx-border-color: #fafafa");


                if(title_field.getCharacters().length() <= 3){
                    title_field.setStyle("-fx-border-color: red");
                    return;
                } else if (inro_filed.getText().length() <= 5){
                    inro_filed.setStyle("-fx-border-color: red");
                    return;
                }else if(text_filed1.getText().length() <= 3){
                    text_filed1.setStyle("-fx-border-color: red");
                    return;
                }

                try {
                    db.addArticle(title_field.getCharacters().toString(), inro_filed.getText().toString(), text_filed1.getText().toString());
                    Parent root  = FXMLLoader.load(getClass().getResource("main.fxml"));
                    Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Программа itProger");
                    stage.setScene(new Scene(root, 600, 400) );
                    stage.show();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });

    }

}
