package com.example.itproger_9;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class viewArticleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_exit;

    @FXML
    private Label text;

    @FXML
    private Label title;

    @FXML
    private Button btn_back;

    DB db = new DB();

    @FXML
    void initialize() throws SQLException {
//        Node node =null;
//        ResultSet res = db.viewArticles(id);
//
//            Label title = (Label) node.lookup("#VA_title");
//            title.setText(res.getString("title"));
//
//            Label intro = (Label) node.lookup("#VA_text");
//            intro.setText(res.getString("text"));



    }
}
