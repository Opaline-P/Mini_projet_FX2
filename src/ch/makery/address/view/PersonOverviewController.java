package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ch.makery.address.MainApp;
import ch.makery.address.model.Student;
import javafx.collections.transformation.FilteredList;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.XMLFormatter;

public class PersonOverviewController {
    @FXML
    private TableView<Student> studentTable;
    private TableView<Person> personTable;
    @FXML
    private FilteredList<Person> filteredData;
    @FXML
    private TableColumn<Student, Number> idColumn;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, Number> birthYearColumn;
    @FXML
    private TableColumn<Student, String> promoColumn;
    @FXML
    private TableColumn<Student, String> optionColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private TextField searchBox;

    @FXML
    private FilteredList<Student> filteredData;
    private Button editButton;
    @FXML
    private Button viewButton;

    private String state = "View";



    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
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
        birthYearColumn.setCellValueFactory(cellData -> cellData.getValue().birthYearProperty());
        promoColumn.setCellValueFactory(cellData -> cellData.getValue().promotionProperty());
        optionColumn.setCellValueFactory(cellData -> cellData.getValue().optionProperty());


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
        studentTable.setItems(filteredData);
    }

    /**
     * Create the predicate
     * @param searchText the search bar text
     * @return ?? pas bien compris
     */
    private Predicate<Student> createPredicate(String searchText){
        return person -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsPerson(person, searchText);
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
        int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentTable.getItems().remove(selectedIndex);
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
    private void handleNewPerson(ActionEvent e){
        Student tempStudent = new Student();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EditStudent.fxml"));
            Parent root = loader.load();

            // changer de scene
            Stage stage = new Stage();
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // relier au contoller
            EditStudentController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setStudent(tempStudent);

            stage.show();
            /* if (okClicked) {
                showPersonDetails(selectedStudent);
            }*/
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        /*boolean okClicked = mainApp.showEditStudent(tempStudent);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
        setMainApp(this.mainApp);
    }

    /**
     * Called when the user clicks the edit button. Opens the list of students
     * to chose the one to edit.
     */
    @FXML
    private void clickEditButton() {
        this.state = "Edit";
        handleClearSearchText();
        searchBox.setPromptText("Qui editer ?");
        editButton.setDisable(true);
        viewButton.setDisable(false);
            mainApp.getStudentData().add(tempStudent);
        }*/
    }


    /**
     * Called when the user clicks the person to edit. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        if (state=="Edit") {
            Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
                if (okClicked) {
                    //showPersonDetails(selectedPerson);
                }
    private void handleEditPerson(ActionEvent e) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EditStudent.fxml"));
                Parent root = loader.load();

                // changer de scene
                Stage stage = new Stage();
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);

                // relier au contoller
                EditStudentController controller = loader.getController();
                controller.setDialogStage(stage);
                controller.setStudent(selectedStudent);

                stage.show();
            /* if (okClicked) {
                showPersonDetails(selectedStudent);
            }*/
            }
            catch (Exception exception) {
                System.out.println(exception);
            }

            /*boolean okClicked = mainApp.showEditStudent(selectedStudent);
            if (okClicked) {
                //showPersonDetails(selectedPerson);
            }*/

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
        this.state = "View";
        handleClearSearchText();
        searchBox.setPromptText("Quel étudiant ?");
        editButton.setDisable(false);
        viewButton.setDisable(true);
    }

}
