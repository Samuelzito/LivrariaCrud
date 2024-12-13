package edu.livraria.controller;

import edu.livraria.utils.PathFXML;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane contentArea;

    /**
     * Abre a tela de cadastro de livros.
     */
    @FXML
    public void menuCadastrarLivro() throws Exception {
        System.out.println("Abrindo tela de cadastro de livros...");
        AnchorPane formView = PathFXML.loadFXML("BookFormView.fxml");
        contentArea.getChildren().setAll(formView);
    }

    /**
     * Abre a tela de listagem de livros.
     */
    @FXML
    public void menuListarLivros() throws Exception {
        System.out.println("Abrindo tela de listagem de livros...");
        AnchorPane listView = PathFXML.loadFXML("BookListView.fxml");
        contentArea.getChildren().setAll(listView);
    }

    /**
     * Exibe informações sobre o sistema.
     */
    @FXML
    public void menuHelp() {
        System.out.println("Exibindo informações sobre o sistema...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre");
        alert.setHeaderText(null);
        alert.setContentText("Sistema de Gerenciamento de Livros\nDesenvolvido por [Seu Nome]");
        alert.showAndWait();
    }
}
