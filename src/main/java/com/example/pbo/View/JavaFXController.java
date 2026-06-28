package com.example.pbo.View;

import com.example.pbo.controller.KnowledgeController;
import com.example.pbo.model.Putusan;
import com.example.pbo.model.KnowledgeRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.Node;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.ParallelTransition;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class JavaFXController {

    // ================= TEXT FIELD =================

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
    private TextField txtCari;

    @FXML
    private VBox legendBox;


    // ================= LABEL STATISTIK =================

    @FXML
    private Label lblTotalPutusan;

    @FXML
    private Label lblRataVonis;

    @FXML
    private Label lblTotalDenda;

    @FXML
    private Label lblStatTotal;

    @FXML
    private Label lblJenisTerbanyak;

    @FXML
    private Label lblStatRataVonis;

    @FXML
    private VBox insightBox;


    // ================= TABLE =================

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

    // ================= HALAMAN =================

    @FXML
    private VBox dashboardPane;

    @FXML
    private HBox dataPane;

    @FXML
    private VBox statistikPane;

    @FXML
    private PieChart pieJenis;

    @FXML
    private BarChart<String, Number> barVonis;

    // ================= DASHBOARD =================

    @FXML
    private VBox activityBox;

    // ================= CONTROLLER =================

    private KnowledgeController controller;

    // ================= WARNA PIE CHART =================

    private static final String[] COLORS = {

            "#2563EB",
            "#60A5FA",
            "#1D4ED8",
            "#93C5FD",
            "#3B82F6",
            "#1E40AF",
            "#BFDBFE"

    };

    @FXML
    private void showDashboard(){

        showPane(dashboardPane);

    }

    @FXML
    private void showData(){

        showPane(dataPane);

    }

    @FXML
    private void showStatistik(){

        showPane(statistikPane);

    }

    private void refreshTable() {

        ObservableList<Putusan> data = FXCollections.observableArrayList(controller.getDaftarSemua());
        tablePutusan.setItems(data);
    }

    private void refreshAll() {

        refreshTable();
        updateDashboard();
        updateActivity();
        updateChart();
        updateInsight();

    }

    private void showPane(Pane pane) {

        dashboardPane.setVisible(false);
        dashboardPane.setManaged(false);

        dataPane.setVisible(false);
        dataPane.setManaged(false);

        statistikPane.setVisible(false);
        statistikPane.setManaged(false);

        pane.setVisible(true);
        pane.setManaged(true);

        // posisi awal sedikit di kanan
        pane.setOpacity(0);
        pane.setTranslateX(40);

        FadeTransition fade =
                new FadeTransition(Duration.millis(350), pane);

        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition slide =
                new TranslateTransition(Duration.millis(350), pane);

        slide.setFromX(40);
        slide.setToX(0);

        ParallelTransition animation =
                new ParallelTransition(fade, slide);

        animation.play();
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

        refreshAll();
        updateStatistikCard();


        pieJenis.setAnimated(false);
        pieJenis.setLegendVisible(true);
        pieJenis.setLabelsVisible(true);

        barVonis.setAnimated(false);
        barVonis.setLegendVisible(false);

        showDashboard();

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
    private void handleCari() {

        String keyword = txtCari.getText().trim();

        if(keyword.isEmpty()){
            refreshTable();
            return;
        }

        ObservableList<Putusan> data = FXCollections.observableArrayList(controller.cariPutusan(keyword));

        tablePutusan.setItems(data);
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
                refreshAll();
                updateStatistikCard();
                clearForm();
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

        controller.hapusPutusan(selected.getNomorPerkara());

        refreshAll();
        updateStatistikCard();
        clearForm();

        tablePutusan.getSelectionModel().clearSelection();
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

            refreshAll();
            updateStatistikCard();
            clearForm();

            tablePutusan.getSelectionModel().clearSelection();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data berhasil diupdate");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void updateDashboard() {

        // Total Putusan
        lblTotalPutusan.setText(
                String.valueOf(controller.getTotalPutusan())
        );

        // Rata-rata Vonis
        lblRataVonis.setText(String.format("%.1f Bulan",
                controller.getRataRataVonis())
        );

        // Total Denda
        lblTotalDenda.setText(formatRupiahDashboard(
                controller.getTotalDenda())
        );
    }

    private void updateStatistikCard() {

        // ================= TOTAL PUTUSAN =================

        lblStatTotal.setText(
                String.valueOf(controller.getTotalPutusan())
        );

        // ================= RATA-RATA VONIS =================

        lblStatRataVonis.setText(
                String.format("%.1f Bulan",
                        controller.getRataRataVonis())
        );

        // ================= JENIS TERBANYAK =================

        Map<String, Integer> jumlahJenis = new HashMap<>();

        for (Putusan p : controller.getDaftarSemua()) {

            jumlahJenis.merge(
                    p.getJenisNarkotika(),
                    1,
                    Integer::sum
            );
        }

        String jenisTerbanyak = "-";
        int maksimum = 0;

        for (Map.Entry<String, Integer> entry : jumlahJenis.entrySet()) {

            if (entry.getValue() > maksimum) {

                maksimum = entry.getValue();
                jenisTerbanyak = entry.getKey();

            }

        }

        lblJenisTerbanyak.setText(jenisTerbanyak);
    }

    private void updateInsight() {

        insightBox.getChildren().clear();

        int total = controller.getTotalPutusan();

        String jenisTerbanyak = controller.getJenisTerbanyak();

        double rata = controller.getRataRataVonis();

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(new Locale("id","ID"));

        String totalDenda =
                rupiah.format(controller.getTotalDenda());

        Label l1 = new Label(
                "✓ Total putusan yang tersimpan sebanyak "
                        + total + " perkara."
        );

        Label l2 = new Label(
                "✓ Jenis narkotika yang paling banyak muncul adalah "
                        + jenisTerbanyak + "."
        );

        Label l3 = new Label(
                "✓ Rata-rata lama hukuman adalah "
                        + String.format("%.1f", rata)
                        + " bulan."
        );

        Label l4 = new Label(
                "✓ Total akumulasi denda mencapai "
                        + totalDenda + "."
        );

        Label l5 = new Label(
                "✓ Statistik diperbarui secara otomatis setiap terjadi perubahan data."
        );

        l1.getStyleClass().add("insight-text");
        l2.getStyleClass().add("insight-text");
        l3.getStyleClass().add("insight-text");
        l4.getStyleClass().add("insight-text");
        l5.getStyleClass().add("insight-text");

        insightBox.getChildren().addAll(
                l1,
                l2,
                l3,
                l4,
                l5
        );
    }

    private void updateActivity() {

        activityBox.getChildren().clear();

        ObservableList<Putusan> daftar =
                FXCollections.observableArrayList(controller.getDaftarSemua());

        if (daftar.isEmpty()) {

            Label kosong = new Label("Belum ada aktivitas.");
            kosong.getStyleClass().add("activity-text");

            activityBox.getChildren().add(kosong);
            return;
        }

        int jumlah = Math.min(5, daftar.size());

        for (int i = daftar.size() - 1; i >= daftar.size() - jumlah; i--) {

            Putusan p = daftar.get(i);

            VBox item = new VBox(4);
            item.getStyleClass().add("activity-item");

            Label nomor = new Label("📄 Nomor : " + p.getNomorPerkara());
            Label nama = new Label("👤 Nama : " + p.getNamaTerdakwa());
            Label jenis = new Label("💊 Jenis : " + p.getJenisNarkotika());

            nomor.getStyleClass().add("activity-title");
            nama.getStyleClass().add("activity-text");
            jenis.getStyleClass().add("activity-text");

            item.getChildren().addAll(
                    nomor,
                    nama,
                    jenis,
                    new Separator()
            );

            activityBox.getChildren().add(item);
        }
    }

    private void updateChart() {

        // ================= PIE CHART =================

        pieJenis.getData().clear();
        legendBox.getChildren().clear();

        Map<String,Integer> jumlahJenis = new HashMap<>();

        for(Putusan p : controller.getDaftarSemua()){

            jumlahJenis.merge(
                    p.getJenisNarkotika(),
                    1,
                    Integer::sum
            );

        }

        int total = controller.getDaftarSemua().size();

        if(total==0){
            return;
        }

        int index=0;

        for(Map.Entry<String,Integer> entry : jumlahJenis.entrySet()){

            double persen =
                    entry.getValue()*100.0/total;

            PieChart.Data data =

                    new PieChart.Data(
                            entry.getKey(),
                            entry.getValue()
                    );

            pieJenis.getData().add(data);

            // ================= CUSTOM LEGEND =================

            HBox row = new HBox(10);

            Label color = new Label("⬤");
            color.setStyle(
                    "-fx-text-fill:"+COLORS[index % COLORS.length]+";" +
                            "-fx-font-size:18;"
            );

            Label text = new Label(

                    entry.getKey()
                            +"   "
                            +entry.getValue()
                            +" Putusan ("
                            +String.format("%.1f",persen)
                            +"%)"

            );

            text.getStyleClass().add("activity-text");

            row.getChildren().addAll(color,text);

            legendBox.getChildren().add(row);

            index++;

        }

        Platform.runLater(() -> {

            int i=0;

            for(PieChart.Data data : pieJenis.getData()){

                Node node=data.getNode();

                node.setStyle(

                        "-fx-pie-color:"
                                +COLORS[i % COLORS.length]
                                +";"

                );

                Tooltip.install(

                        node,

                        new Tooltip(

                                data.getName()

                        )

                );

                ScaleTransition in=
                        new ScaleTransition(Duration.millis(180),node);

                in.setToX(1.08);
                in.setToY(1.08);

                ScaleTransition out=
                        new ScaleTransition(Duration.millis(180),node);

                out.setToX(1);
                out.setToY(1);

                node.setOnMouseEntered(e->in.playFromStart());
                node.setOnMouseExited(e->out.playFromStart());

                i++;
            }

        });


        // ================= BAR CHART =================

        barVonis.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        Map<String, Double> totalVonis = new HashMap<>();
        Map<String, Integer> jumlahData = new HashMap<>();

        for (Putusan p : controller.getDaftarSemua()) {

            totalVonis.merge(
                    p.getJenisNarkotika(),
                    (double) p.getVonisHukuman(),
                    Double::sum
            );

            jumlahData.merge(
                    p.getJenisNarkotika(),
                    1,
                    Integer::sum
            );
        }

        for (String jenis : totalVonis.keySet()) {

            double rata =
                    totalVonis.get(jenis) /
                            jumlahData.get(jenis);

            XYChart.Data<String, Number> data =
                    new XYChart.Data<>(jenis, rata);

            data.nodeProperty().addListener((obs, oldNode, newNode) -> {

                if (newNode != null) {

                    newNode.setStyle("-fx-bar-fill:#3B82F6;");

                    Tooltip.install(
                            newNode,
                            new Tooltip(
                                    jenis +
                                            "\nRata-rata Vonis : " +
                                            String.format("%.1f", rata) +
                                            " Bulan"
                            )
                    );
                }
            });

            series.getData().add(data);
        }

        barVonis.getData().add(series);
    }

    private String formatRupiahDashboard(double nilai) {

        if (nilai >= 1_000_000_000_000L) {
            return String.format("Rp %.1f Triliun",
                    nilai / 1_000_000_000_000.0);
        }

        if (nilai >= 1_000_000_000) {
            return String.format("Rp %.1f Miliar",
                    nilai / 1_000_000_000.0);
        }

        if (nilai >= 1_000_000) {
            return String.format("Rp %.1f Juta",
                    nilai / 1_000_000.0);
        }

        if (nilai >= 1_000) {
            return String.format("Rp %.1f Ribu",
                    nilai / 1_000.0);
        }

        return String.format("Rp %.0f", nilai);
    }
}