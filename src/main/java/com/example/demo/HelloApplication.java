package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Use absolute path from resources root
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/viewRequestPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600); // width x height
        scene.getStylesheets().add(getClass().getResource("/com/example/demo/space-theme.css").toExternalForm());

        stage.setTitle("Teacher Requests");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
