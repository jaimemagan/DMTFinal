module com.example.dmt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.dmt to javafx.fxml;
    exports com.example.dmt;
}