package edu.livraria.controller;

import edu.livraria.model.entity.Livro;
import edu.livraria.model.services.LivroServices;
import edu.livraria.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookFormController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtAnoPublicacao;

    @FXML
    private TextField txtGenero;

    private final EntityManager em = HibernateUtil.getEntityManager();
    private final LivroServices livroServices = new LivroServices(em);

    @FXML
    public void saveBook() {
        try {
            Livro livro = new Livro();
            livro.setTitulo(txtTitulo.getText());
            livro.setAutor(txtAutor.getText());
            livro.setAnoPublicacao(Integer.parseInt(txtAnoPublicacao.getText()));
            livro.setGenero(txtGenero.getText());

            livroServices.salvar(livro);

            System.out.println("Livro salvo com sucesso!");
            clearFields(); // Método para limpar os campos após salvar
        } catch (Exception e) {
            System.out.println("Erro ao salvar o livro: " + e.getMessage());
        }
    }

    @FXML
    public void cancelAction() {
        System.out.println("Ação cancelada.");
        clearFields();
    }

    private void clearFields() {
        txtTitulo.clear();
        txtAutor.clear();
        txtAnoPublicacao.clear();
        txtGenero.clear();
    }
}
