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

        tablePutusan.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldData, newData) -> {

                    if (newData != null) {

                        txtNomor.setText(newData.getNomorPerkara());

                        txtNama.setText(newData.getNamaTerdakwa());

                        txtJenis.setText(newData.getJenisNarkotika());

                        txtVonis.setText(String.valueOf(newData.getVonisHukuman()));

                        txtDenda.setText(String.valueOf(newData.getVonisDenda()));
                    }
                });
    }

    @FXML
    private void handleStatistik() {

        if (controller == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Controller belum terinisialisasi.");
            alert.showAndWait();
            return;
        }

        double rataRata = controller.getRataRataVonis();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Statistik");
        alert.setHeaderText(null);
        alert.setContentText("Rata-rata Vonis: " + rataRata + " bulan");

        alert.showAndWait();
    }

    @FXML
    private void handleTambah() {

        try {

            String nomor = txtNomor.getText();
            String nama = txtNama.getText();
            String jenis = txtJenis.getText();
            String vonis = txtVonis.getText();
            String denda = txtDenda.getText();

            if (controller != null) {
                controller.tambahPutusan(nomor, nama, jenis, vonis, denda);
                refreshTable(); clearForm();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukses");
            alert.setHeaderText(null);
            alert.setContentText("Data berhasil ditambahkan!");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleHapus() {

        Putusan selected = tablePutusan.getSelectionModel().getSelectedItem();

        if (selected == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Pilih data yang ingin dihapus!");
            alert.showAndWait();

            return;
        }

        controller.hapusPutusan(
                selected.getNomorPerkara()
        );

        refreshTable();
    }

    @FXML
    private void handleUpdate() {

        try {

            controller.updatePutusan(
                    txtNomor.getText(),
                    txtNama.getText(),
                    txtJenis.getText(),
                    txtVonis.getText(),
                    txtDenda.getText()
            );

            refreshTable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data berhasil diupdate");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}