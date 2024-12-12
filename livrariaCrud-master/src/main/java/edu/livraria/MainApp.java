package edu.livraria;

import edu.livraria.utils.PathFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(new FileInputStream(PathFXML.pathBase()+"\\MainView.fxml"));
            Scene scene = new Scene(root, 800,550);
            ScrollPane scrollPane = (ScrollPane) scene.getRoot();
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);

            primaryStage.setTitle("Menu Principal");
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
