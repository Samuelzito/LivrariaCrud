package edu.livraria.controller;

import edu.livraria.model.entity.Livro;
import edu.livraria.model.services.LivroServices;
import edu.livraria.utils.HibernateUtil;
import edu.livraria.utils.PathFXML;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
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

    public void editBook() throws IOException {
        Livro livroSelecionado = tableBooks.getSelectionModel().getSelectedItem();

        if (livroSelecionado == null) {
            throw new IllegalStateException("Nenhum livro selecionado para edição.");
        }

        // Caminho absoluto ou dinâmico para o arquivo FXML
        String fxmlPath = PathFXML.pathBase() + "\\BookEditView.fxml";

        // Carregar o arquivo FXML usando FileInputStream
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(new FileInputStream(fxmlPath));

        // Configura o controlador da tela de edição
        BookEditController controller = loader.getController();
        controller.setLivro(livroSelecionado);

        // Configura e exibe a nova janela como modal
        Stage stage = new Stage();
        stage.setTitle("Editar Livro");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Atualiza a tabela após a edição
        loadBooks();
    }



    @FXML
    public void deleteBook() {
        Livro livroSelecionado = tableBooks.getSelectionModel().getSelectedItem();

        if (livroSelecionado == null) {
            showAlert("Nenhum livro selecionado para exclusão.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente excluir o livro?", ButtonType.YES, ButtonType.NO);
        if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            livroServices.excluir(livroSelecionado.getId());
            loadBooks();
            System.out.println("Livro excluído com sucesso!");
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
