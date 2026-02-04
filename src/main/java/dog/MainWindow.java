package dog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dog dog;

    private final Image userImage = new Image(getClass().getResourceAsStream("/images/Dog1.jpg"));
    private final Image dogImage = new Image(getClass().getResourceAsStream("/images/Dog2.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDog(Dog d) {
        dog = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dog.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDogDialog(response, dogImage)
        );
        userInput.clear();
    }
}
