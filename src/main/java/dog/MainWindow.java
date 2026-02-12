package dog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI window of the Dog chatbot.
 * Manages user input, displays messages, and handles interactions with the Dog logic.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_IMAGE_PATH = "/images/Dog1.jpg";
    private static final String DOG_IMAGE_PATH = "/images/Dog2.jpg";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dog dog;
    private final Image userImage;
    private final Image dogImage;

    /**
     * Constructs a MainWindow and preloads the user and dog avatars.
     */
    public MainWindow() {
        this.userImage = new Image(getClass().getResourceAsStream(USER_IMAGE_PATH));
        this.dogImage = new Image(getClass().getResourceAsStream(DOG_IMAGE_PATH));
    }

    /**
     * Initializes the GUI component binding.
     * Binds the scroll pane's vertical scroll position to the dialog container's height.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setOnAction(event -> handleUserInput());
    }

    /**
     * Sets the Dog instance for this GUI to use.
     *
     * @param d the Dog chatbot instance (must not be null)
     * @throws IllegalArgumentException if d is null
     */
    public void setDog(Dog d) {
        if (d == null) {
            throw new IllegalArgumentException("Dog instance cannot be null");
        }
        dog = d;
    }

    /**
     * Handles user input from the text field or send button.
     * Sends the input to the Dog instance, displays the user's message and the response,
     * and clears the input field.
     */
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
