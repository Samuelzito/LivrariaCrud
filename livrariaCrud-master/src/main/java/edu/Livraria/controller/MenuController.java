package edu.Livraria.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MenuController {

    @FXML
    public void abrirTelaGerenciarLivros(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gerenciar Livros");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sairAplicacao(ActionEvent event) {
        System.exit(0); // Encerra a aplicação.
    }
}
