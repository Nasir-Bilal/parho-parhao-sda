package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the initial page (Requests page)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/viewRequestPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/demo/space-theme.css").toExternalForm());

        stage.setTitle("Parho Parhao - Requests");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}