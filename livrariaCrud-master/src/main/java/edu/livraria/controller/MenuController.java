package edu.livraria.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    public void abrirTelaGerenciarLivros() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/livraria/view/MainView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gerenciar Livros");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sairAplicacao() {
        System.exit(0); // Encerra a aplicação
    }
}
