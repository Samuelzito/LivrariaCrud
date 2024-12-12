package edu.livraria;

import edu.livraria.utils.PathFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.FileInputStream;

public class MainApp extends Application {
    @Getter
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega a tela principal
        System.out.println(PathFXML.pathBase());
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = fxmlLoader.load(new FileInputStream(PathFXML.pathBase() + "\\MainView.fxml"));
        Scene scene = new Scene(root, 800, 550);

        // Configuração do stage
        primaryStage.setTitle("Menu Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
