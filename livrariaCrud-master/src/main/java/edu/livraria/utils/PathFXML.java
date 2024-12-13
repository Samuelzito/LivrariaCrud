package edu.livraria.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathFXML {

    // Define um caminho base absoluto para o projeto
    public static String pathBase() {
        return "C:/Users/Super/IdeaProjects/LivrariaCrud/livrariaCrud-master/src/main/java/edu/livraria/view";
    }

    // Método para carregar arquivos FXML
    public static AnchorPane loadFXML(String fxmlName) throws Exception {
        Path fullPath = Paths.get(pathBase(), fxmlName); // Junta o caminho base com o nome do arquivo
        System.out.println("Caminho completo para FXML: " + fullPath.toAbsolutePath()); // Exibe o caminho completo
        FXMLLoader loader = new FXMLLoader(fullPath.toUri().toURL()); // Converte o caminho para URL
        return loader.load(); // Retorna o conteúdo carregado
    }
}
