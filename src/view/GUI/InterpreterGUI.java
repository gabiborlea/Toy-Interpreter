package view.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GUI.window.MainWindow.MainWindowController;
import view.GUI.window.SelectionWindow.SelectionWindowController;

public class InterpreterGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(MainWindowController.getFXMLPath());
        stage.setTitle("Interpreter");
        stage.setScene(new Scene(root, 500, 300));
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
