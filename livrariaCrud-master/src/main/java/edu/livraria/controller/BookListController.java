package edu.livraria.controller;

import edu.livraria.model.entity.Livro;
import edu.livraria.model.services.LivroServices;
import edu.livraria.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class BookListController {

    @FXML
    private TableView<Livro> tableBooks;

    @FXML
    private TableColumn<Livro, Long> colId;

    @FXML
    private TableColumn<Livro, String> colTitulo;

    @FXML
    private TableColumn<Livro, String> colAutor;

    @FXML
    private TableColumn<Livro, Integer> colAno;

    @FXML
    private TableColumn<Livro, String> colGenero;

    private final EntityManager em = HibernateUtil.getEntityManager();
    private final LivroServices livroServices = new LivroServices(em);

    private ObservableList<Livro> livros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar colunas
        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colTitulo.setCellValueFactory(data -> data.getValue().tituloProperty());
        colAutor.setCellValueFactory(data -> data.getValue().autorProperty());
        colAno.setCellValueFactory(data -> data.getValue().anoPublicacaoProperty().asObject());
        colGenero.setCellValueFactory(data -> data.getValue().generoProperty());

        loadBooks();
    }

    private void loadBooks() {
        List<Livro> livroList = livroServices.listarTodos();
        livros.setAll(livroList);
        tableBooks.setItems(livros);
    }

    @FXML
    public void editBook() {
        Livro livroSelecionado = tableBooks.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            System.out.println("Editar livro: " + livroSelecionado.getTitulo());
            // Abrir tela de edição (integração futura)
        } else {
            showAlert("Nenhum livro selecionado para edição.");
        }
    }

    @FXML
    public void deleteBook() {
        Livro livroSelecionado = tableBooks.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente excluir o livro?", ButtonType.YES, ButtonType.NO);
            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                livroServices.excluir(livroSelecionado.getId());
                loadBooks();
                System.out.println("Livro excluído com sucesso!");
            }
        } else {
            showAlert("Nenhum livro selecionado para exclusão.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
