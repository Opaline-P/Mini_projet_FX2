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
        if (mainApp.getState()=="Edit") {
            leftBorderAnchor.setPrefWidth(93);
            messageLabel.setText("You are editing a student information");
        }if (mainApp.getState()=="Add") {
            leftBorderAnchor.setPrefWidth(93);
            messageLabel.setText("You are adding a new student");
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
        /*//edit Button
        editButton.setDisable(false);
        //view Button
        viewButton.setDisable(false);
        //add Button
        addButton.setDisable(true);*/
        setDisabledButton();
        boolean okClicked = mainApp.showStudentEditDialog(tempStudent);
        /*if (okClicked) {
            mainApp.getStudentData().add(tempStudent);
        }
        setMainApp(this.mainApp);*/
    }


    /**
     * Called when the user clicks the edit button. Opens the list of students
     * to chose the one to edit.
     */
    @FXML
    private void clickEditButton() {
        //this.state = "Edit";
        mainApp.setState("Edit");
        /*//edit Button
        editButton.setDisable(true);
        //view Button
        viewButton.setDisable(false);
        //add Button
        addButton.setDisable(false);*/

        setDisabledButton();
        mainApp.showStudentOverview();
        //search Box
        /*handleClearSearchText();
        searchBox.setPromptText("Which student do you want to edit ?");
        searchAnchorPane.setLeftAnchor(searchStackPane, 100.0);
        searchAnchorPane.setRightAnchor(searchStackPane, 100.0);*/
    }

    /**
     * Called when the user clicks the edit button. Opens the list of students
     * to chose the one to edit.
     */
    @FXML
    private void clickViewButton() {
        //this.state = "View";
        mainApp.setState("View");
        /*//edit Button
        editButton.setDisable(false);
        //view Button
        viewButton.setDisable(true);
        //add Button
        addButton.setDisable(false);*/

        setDisabledButton();
        mainApp.showStudentOverview();
        //viewButton.setStyle("-fx-background-color: -secondary; -fx-text-fill: -primary");
        /*//search Box
        handleClearSearchText();
        searchBox.setPromptText("Search...");
        searchAnchorPane.setLeftAnchor(searchStackPane, 400.0);
        searchAnchorPane.setRightAnchor(searchStackPane, 20.0);*/
    }

    /**
     * Called when the user clicks the logo button. Opens the home page
     *
     */
    @FXML
    private void clickLogoButton() {
        //this.state = "View";
        mainApp.setState("Home");
        /*//edit Button
        editButton.setDisable(false);
        //view Button
        viewButton.setDisable(false);
        //add Button
        addButton.setDisable(false);*/
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
