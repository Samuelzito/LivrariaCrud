package edu.Livraria.controller;

import edu.Livraria.model.entity.Livro;
import edu.Livraria.model.services.LivroServices;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LivroController {

    @FXML
    private TableView<Livro> tabelaLivros;
    @FXML
    private TableColumn<Livro, String> colunaTitulo;
    @FXML
    private TableColumn<Livro, String> colunaAutor;
    @FXML
    private TableColumn<Livro, Integer> colunaAno;
    @FXML
    private TableColumn<Livro, String> colunaGenero;

    @FXML
    private TextField campoTitulo, campoAutor, campoAno, campoGenero, campoBusca;
    @FXML
    private Button btnCadastrar, btnAtualizar, btnExcluir, btnExportar, btnBuscar;

    private LivroServices livroServices;

    @FXML
    public void initialize() {
        livroServices = new LivroServices(); // Inicialize seu serviço de livros aqui.

        colunaTitulo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitulo()));
        colunaAutor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAutor()));
        colunaAno.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAnoPublicacao()).asObject());
        colunaGenero.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGenero()));

        listarLivros();
    }

    @FXML
    private void cadastrarLivro() {
        if (validarCampos()) {
            Livro livro = new Livro();
            livro.setTitulo(campoTitulo.getText());
            livro.setAutor(campoAutor.getText());
            livro.setAnoPublicacao(Integer.parseInt(campoAno.getText()));
            livro.setGenero(campoGenero.getText());
            livroServices.salvar(livro);
            listarLivros();
            limparCampos();
        }
    }

    @FXML
    private void atualizarLivro() {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null && validarCampos()) {
            livroSelecionado.setTitulo(campoTitulo.getText());
            livroSelecionado.setAutor(campoAutor.getText());
            livroSelecionado.setAnoPublicacao(Integer.parseInt(campoAno.getText()));
            livroSelecionado.setGenero(campoGenero.getText());
            livroServices.atualizar(livroSelecionado);
            listarLivros();
            limparCampos();
        }
    }

    @FXML
    private void excluirLivro() {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            Alert confirmacao = new Alert(AlertType.CONFIRMATION, "Deseja realmente excluir este livro?");
            Optional<ButtonType> resultado = confirmacao.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                livroServices.excluir(livroSelecionado.getId());
                listarLivros();
                limparCampos();
            }
        }
    }

    @FXML
    private void listarLivros() {
        List<Livro> livros = livroServices.listarTodos();
        tabelaLivros.setItems(FXCollections.observableArrayList(livros));
    }

    @FXML
    private void buscarLivro() {
        String filtro = campoBusca.getText().toLowerCase();
        List<Livro> livrosFiltrados = livroServices.listarTodos().stream()
                .filter(livro -> livro.getTitulo().toLowerCase().contains(filtro) ||
                        livro.getAutor().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
        tabelaLivros.setItems(FXCollections.observableArrayList(livrosFiltrados));
    }

    @FXML
    private void exportarCsv() {
        try (PrintWriter writer = new PrintWriter("livros.csv")) {
            List<Livro> livros = livroServices.listarTodos();
            for (Livro livro : livros) {
                writer.println(String.format("%s,%s,%d,%s", livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao(), livro.getGenero()));
            }
            Alert alerta = new Alert(AlertType.INFORMATION, "Dados exportados com sucesso!");
            alerta.show();
        } catch (Exception e) {
            Alert alerta = new Alert(AlertType.ERROR, "Erro ao exportar os dados!");
            alerta.show();
        }
    }

    private void limparCampos() {
        campoTitulo.clear();
        campoAutor.clear();
        campoAno.clear();
        campoGenero.clear();
        campoBusca.clear();
    }

    private boolean validarCampos() {
        if (campoTitulo.getText().isEmpty() || campoAutor.getText().isEmpty() ||
                campoAno.getText().isEmpty() || campoGenero.getText().isEmpty()) {
            Alert alerta = new Alert(AlertType.WARNING, "Por favor, preencha todos os campos!");
            alerta.show();
            return false;
        }
        return true;
    }
}
