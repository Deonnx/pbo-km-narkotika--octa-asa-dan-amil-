package com.example.pbo.View;

import com.example.pbo.controller.KnowledgeController;
import com.example.pbo.model.Putusan;
import com.example.pbo.model.KnowledgeRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JavaFXController {

    @FXML
    private TextField txtNomor;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtJenis;

    @FXML
    private TextField txtVonis;

    @FXML
    private TextField txtDenda;

    @FXML
    private TableView<Putusan> tablePutusan;

    @FXML
    private TableColumn<Putusan, String> colNomor;

    @FXML
    private TableColumn<Putusan, String> colNama;

    @FXML
    private TableColumn<Putusan, String> colJenis;

    @FXML
    private TableColumn<Putusan, Integer> colVonis;

    @FXML
    private TableColumn<Putusan, Double> colDenda;

    private KnowledgeController controller;

    private void refreshTable() {

        ObservableList<Putusan> data = FXCollections.observableArrayList(controller.getDaftarSemua());
        tablePutusan.setItems(data);
    }

    private void clearForm() {
        txtNomor.clear();
        txtNama.clear();
        txtJenis.clear();
        txtVonis.clear();
        txtDenda.clear();
    }

    @FXML
    public void initialize() {

        controller = new KnowledgeController(new KnowledgeRepository());

        colNomor.setCellValueFactory(
                new PropertyValueFactory<>("nomorPerkara"));

        colNama.setCellValueFactory(
                new PropertyValueFactory<>("namaTerdakwa"));

        colJenis.setCellValueFactory(
                new PropertyValueFactory<>("jenisNarkotika"));

        colVonis.setCellValueFactory(
                new PropertyValueFactory<>("vonisHukuman"));

        colDenda.setCellValueFactory(
                new PropertyValueFactory<>("vonisDenda"));

        refreshTable();
    }
}