package goober;

import java.io.IOException;

import goober.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Goober using FXML.
 */
public class Main extends Application {

    private final Goober goober = new Goober();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGoober(goober);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
