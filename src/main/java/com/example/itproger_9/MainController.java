package com.example.itproger_9;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_exit, btn_add_article;


    @FXML
    private VBox panelVBox;

    private  DB db = new DB();

    @FXML
    void initialize() throws SQLException,ClassNotFoundException {
        ResultSet res = db.getArticles();
        while(res.next()){
            Node node = null;

                try {
                    node = FXMLLoader.load(getClass().getResource("article.fxml"));
                    Label title = (Label) node.lookup("#title");
                    title.setText(res.getString("title"));

                    Label intro = (Label) node.lookup("#intro");
                    intro.setText(res.getString("intro"));
                    final Node nodeSet = node;
                    node.setOnMouseEntered(event ->{
                            nodeSet.setStyle("-fx-background-color: #707173");
                     });
                    node.setOnMouseExited(event ->{
                        nodeSet.setStyle("-fx-background-color: #343434");
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }


                HBox hBox =new HBox();
                hBox.getChildren().add(node);
                hBox.setAlignment(Pos.BASELINE_CENTER);
                panelVBox.getChildren().add(hBox);
                panelVBox.setSpacing(10);

            Node finalNode = node;
            node.setOnMouseClicked(event -> {


                Parent root  = null;
                try {
                    //System.out.println(res.getString("id")));
                    db.viewArticles();

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                        root = FXMLLoader.load(getClass().getResource("viewArticle.fxml"));
                        Stage stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("Программа itProger");
                        stage.setScene(new Scene(root, 600, 400) );
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                });

            }



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

        btn_add_article.setOnAction(event ->{
            try {
                Parent root  = FXMLLoader.load(getClass().getResource("addArticle.fxml"));
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
