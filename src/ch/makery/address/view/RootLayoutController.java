package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RootLayoutController {

    @FXML
    private Button editButton;
    @FXML
    private Button viewButton;
    @FXML
    private Button addButton;
    @FXML
    private Label messageLabel;
    @FXML
    private AnchorPane leftBorderAnchor;

    // Reference to the main application.
    private MainApp mainApp;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RootLayoutController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() { }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    /**
     *
     * @param bool
     */
    public void setVisibleMessage(Boolean bool){
        messageLabel.setVisible(bool);
        editButton.setVisible(true);
        if (mainApp.getState().equals("Edit")) {
            leftBorderAnchor.setPrefWidth(93);
            messageLabel.setText("You are editing a student information");
        }
        if (mainApp.getState().equals("Add")) {
            leftBorderAnchor.setPrefWidth(93);
            messageLabel.setText("You are adding a new student");
        }if (mainApp.getState()=="Show") {
            leftBorderAnchor.setPrefWidth(93);
            messageLabel.setText("You are viewing a student information");
        }if (bool.equals(false)){
            leftBorderAnchor.setPrefWidth(7);
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Student tempStudent = new Student();
        mainApp.setState("Add");
        setDisabledButton();
        boolean okClicked = mainApp.showStudentEditDialog(tempStudent);
    }


    /**
     * Called when the user clicks the edit button. Opens the list of students
     * to chose the one to edit.
     */
    @FXML
    private void clickEditButton() {
        mainApp.setState("Edit");

        setDisabledButton();
        mainApp.showStudentOverview();
    }

    /**
     * Called when the user clicks the view button. Opens the list of students
     * to chose the one to view.
     */
    @FXML
    private void clickViewButton() {
        mainApp.setState("View");

        setDisabledButton();
        mainApp.showStudentOverview();
    }

    /**
     * Called when the user clicks the logo button. Opens the home page
     *
     */
    @FXML
    private void clickLogoButton() {
        mainApp.setState("Home");
        setDisabledButton();
        mainApp.showHomePage();

    }

    /**
     * Called when the user clicks a button. Disable false or true
     *
     */
    @FXML
    public void setDisabledButton(){
        if (mainApp.getState().equals("Home")){
            //edit Button
            editButton.setDisable(false);
            //view Button
            viewButton.setDisable(false);
            //add Button
            addButton.setDisable(false);

        }if (mainApp.getState().equals("View")){
            //edit Button
            editButton.setDisable(false);
            //view Button
            viewButton.setDisable(true);
            //add Button
            addButton.setDisable(false);

        }if (mainApp.getState().equals("Edit")){
            //edit Button
            editButton.setDisable(true);
            //view Button
            viewButton.setDisable(false);
            //add Button
            addButton.setDisable(false);

        }if (mainApp.getState().equals("Add")){
            //edit Button
            editButton.setDisable(false);
            //view Button
            viewButton.setDisable(false);
            //add Button
            addButton.setDisable(true);
        }
    }

}
