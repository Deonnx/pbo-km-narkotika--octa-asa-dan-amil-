package com.example.pbo.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/pbo/JavaFXView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 850);
        stage.setTitle("Knowledge Management System - Putusan Pengadilan Narkotika");
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(750);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}