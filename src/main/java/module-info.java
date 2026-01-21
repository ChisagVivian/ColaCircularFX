module ec.edu.espoch.colaimplementada {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espoch.colacircular to javafx.fxml;
    exports ec.edu.espoch.colacircular;
}
