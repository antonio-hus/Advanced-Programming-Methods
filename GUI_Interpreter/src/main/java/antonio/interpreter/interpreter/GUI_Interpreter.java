package antonio.interpreter.interpreter;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI_Interpreter extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Create root group
        Group root = new Group();

        // Create & Configure Main Scene
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Toy Language Interpreter - Antonio Hus");
        stage.setScene(scene);

        // Launch Stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}