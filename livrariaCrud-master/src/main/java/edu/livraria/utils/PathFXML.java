package edu.livraria.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.nio.file.Paths;

public class PathFXML {

    public static String pathBase() {
        return Paths.get("livrariaCrud-master/src/main/java/edu/livraria/view").toAbsolutePath().toString();
    }

    public static AnchorPane loadFXML(String fxmlName) {
        try {
            String fullPath = Paths.get(pathBase(), fxmlName).toUri().toString();
            FXMLLoader loader = new FXMLLoader(Paths.get(fullPath).toUri().toURL());
            return loader.load();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar FXML: " + fxmlName, e);
        }
    }
}
