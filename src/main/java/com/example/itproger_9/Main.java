package com.example.itproger_9;


        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        String scena = "sample.fxml";
        File file =  new File("user.settings");
        boolean exists = file.exists();
        if (exists){

            FileInputStream fis = new FileInputStream("user.settings");
            ObjectInputStream ois = new ObjectInputStream(fis);
            User user = (User) ois.readObject();
            if(user.getLogin().equals(""))
                scena = "main.fxml";

            ois.close();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Программа itProger");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}