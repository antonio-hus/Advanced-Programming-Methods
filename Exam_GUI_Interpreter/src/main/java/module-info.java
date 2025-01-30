module antonio.interpreter.interpreter {
    requires javafx.controls;
    requires javafx.fxml;

    exports antonio.interpreter.interpreter;
    exports antonio.interpreter.interpreter.services;
    exports antonio.interpreter.interpreter.domain.statements;

    opens antonio.interpreter.interpreter.services to javafx.fxml;
}
