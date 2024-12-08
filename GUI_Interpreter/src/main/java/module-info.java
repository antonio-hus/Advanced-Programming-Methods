module antonio.interpreter.interpreter {
    requires javafx.controls;
    requires javafx.fxml;


    opens antonio.interpreter.interpreter to javafx.fxml;
    exports antonio.interpreter.interpreter;
}