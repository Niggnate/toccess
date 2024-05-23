module com.nursoft.toccess {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.nursoft.toccess to javafx.fxml;
    exports com.nursoft.toccess;
}