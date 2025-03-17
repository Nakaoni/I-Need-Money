module desktop {
    requires javafx.controls;
    requires javafx.fxml;

    opens fr.nakaoni.inm.desktop to javafx.fxml;
    exports fr.nakaoni.inm.desktop;
}
