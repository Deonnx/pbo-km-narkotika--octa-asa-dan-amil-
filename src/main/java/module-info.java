module com.example.pbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;


    opens com.example.pbo.App to javafx.graphics;
    exports com.example.pbo.App;


    opens com.example.pbo.View to javafx.fxml;
    exports com.example.pbo.View;

    exports com.example.pbo.controller;
    exports com.example.pbo.model;
}
}