package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ch.makery.address.MainApp;
import ch.makery.address.model.Student;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.function.Predicate;

public class StudentOverviewController {
    @FXML
    private TableView<Student> personTable;
    @FXML
    private FilteredList<Student> filteredData;
    @FXML
    private TableColumn<Student, Number> idColumn;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> promoColumn;
    @FXML
    private TableColumn<Student, String> specialityColumn;
    @FXML
    private TableColumn<Student, Number> birthyearColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private TextField searchBox;
    @FXML
    private StackPane searchStackPane;
    @FXML
    private AnchorPane searchAnchorPane;

    @FXML
    private Button editButton;
    @FXML
    private Button viewButton;

    //private String state = "View";



    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StudentOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        //idColumn.setCellFactory(col -> new IntegerEditingCell());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        birthyearColumn.setCellValueFactory(cellData -> cellData.getValue().birthyearProperty());
        promoColumn.setCellValueFactory(cellData -> cellData.getValue().promoProperty());
        specialityColumn.setCellValueFactory(cellData -> cellData.getValue().specialityProperty());


        // Clear person details.
        //showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        //personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

        //Management of the search box with predicate
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(createPredicate(newValue)));

        //editButton.onActionProperty().addListener((observable, oldValue, newValue) -> this.editButton.setDisable(true));

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //personTable.setItems(this.mainApp.getPersonData());

        //Filtered list when we are searching a student
        filteredData = new FilteredList<>(FXCollections.observableList(this.mainApp.getStudentData()));
        personTable.setItems(filteredData);

        if (mainApp.getState()=="Edit") {
            clickEditButton();
        }if (mainApp.getState()=="View") {
            clickViewButton();
        }
    }

    /**
     * Create the predicate
     * @param searchText the search bar text
     * @return ?? pas bien compris
     */
    private Predicate<Student> createPredicate(String searchText){
        return student -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsPerson(student, searchText);
        };
    }


    /**
     * Search if a person is containing the string in the search bar
     * @param student the person to compare with the search bar (id, names)
     * @param searchText the search bar text
     * @return true si the person is corresponding to the search false if not
     */
    private boolean searchFindsPerson(Student student, String searchText){
        return (student.getFirstName().toLowerCase().contains(searchText.toLowerCase())) ||
                (student.getLastName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(student.getID()).toString().contains(searchText); //ou .equals
    }


    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    /*private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            //postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }*/

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    public void handleClearSearchText() {
        searchBox.setText("");
        //event.consume();
    }


    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Student tempStudent = new Student();
        mainApp.setState("Add");
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
        /*//this.state = "Edit";
        mainApp.setState("Edit");
        //edit Button
        editButton.setDisable(true);
        //view Button
        viewButton.setDisable(false);*/
        //search Box
        handleClearSearchText();
        searchBox.setPromptText("Which student do you want to edit ?");
        searchAnchorPane.setLeftAnchor(searchStackPane, 100.0);
        searchAnchorPane.setRightAnchor(searchStackPane, 100.0);
    }


    /**
     * Called when the user clicks the person to edit. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        //if (state=="Edit") {
        if (mainApp.getState()=="Edit") {
            Student selectedStudent = personTable.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                boolean okClicked = mainApp.showStudentEditDialog(selectedStudent);
                if (okClicked) {
                    //showPersonDetails(selectedPerson);
                }

            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Person Selected");
                alert.setContentText("Please select a person in the table.");

                alert.showAndWait();
            }
        }
    }

    /**
     * Called when the user clicks the edit button. Opens the list of students
     * to chose the one to edit.
     */
    @FXML
    private void clickViewButton() {
       /* //this.state = "View";
        mainApp.setState("View");
        //edit Button
        editButton.setDisable(false);
        //view Button
        viewButton.setDisable(true);*/
        //viewButton.setStyle("-fx-background-color: -secondary; -fx-text-fill: -primary");
        //search Box
        handleClearSearchText();
        searchBox.setPromptText("Search...");
        searchAnchorPane.setLeftAnchor(searchStackPane, 400.0);
        searchAnchorPane.setRightAnchor(searchStackPane, 20.0);
    }

}
