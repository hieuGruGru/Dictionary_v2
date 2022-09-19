package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Từ điển Anh - Việt");
        primaryStage.getIcons().add(new Image("D:\\1.Subjects\\OOP\\OOP_N1_BTL_N9\\soucre_code\\Dictionary_v2\\src\\resource\\teemo.jpg"));
        primaryStage.show();

    }
}
