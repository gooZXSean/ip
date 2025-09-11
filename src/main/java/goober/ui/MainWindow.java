package goober.ui;

import goober.Goober;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image gooberImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Goober goober;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Goober instance
     */
    public void setGoober(Goober g) {
        goober = g;
        DialogBox greeting = DialogBox.getGooberDialog(g.getResponse("hello"), gooberImage);
        dialogContainer.getChildren().addAll(greeting);
    }

    /**
     * Creates two dialog boxes, one answering user input and the other containing Goober's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = goober.getResponse(input);
        dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(input, userImage), DialogBox.getGooberDialog(response, gooberImage));
        userInput.clear();
    }
}
