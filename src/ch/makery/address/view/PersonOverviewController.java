package ch.makery.address.view;

import ch.makery.address.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import javafx.collections.transformation.FilteredList;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.XMLFormatter;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Number> idColumn;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> promoColumn;
    @FXML
    private TableColumn<Person, String> specialityColumn;
    @FXML
    private TableColumn<Person, Number> birthyearColumn;

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
    private FilteredList<Person> filteredData;

    @FXML
    private Button editButton;

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
        birthyearColumn.setCellValueFactory(cellData -> cellData.getValue().birthyearProperty());
        promoColumn.setCellValueFactory(cellData -> cellData.getValue().promoProperty());
        specialityColumn.setCellValueFactory(cellData -> cellData.getValue().specialityProperty());
        editButton = new Button();

        // Clear person details.
        //showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        //personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

        //Management of the search box with predicate
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(createPredicate(newValue)));

        editButton.onActionProperty().addListener((observable, oldValue, newValue) -> this.editButton.setDisable(true));

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
        filteredData = new FilteredList<>(FXCollections.observableList(this.mainApp.getPersonData()));
        personTable.setItems(filteredData);
    }

    /**
     * Create the predicate
     * @param searchText the search bar text
     * @return ?? pas bien compris
     */
    private Predicate<Person> createPredicate(String searchText){
        return person -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsPerson(person, searchText);
        };
    }


    /**
     * Search if a person is containing the string in the search bar
     * @param person the person to compare with the search bar (id, names)
     * @param searchText the search bar text
     * @return true si the person is corresponding to the search false if not
     */
    private boolean searchFindsPerson(Person person, String searchText){
        return (person.getFirstName().toLowerCase().contains(searchText.toLowerCase())) ||
                (person.getLastName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(person.getID()).toString().contains(searchText); //ou .equals
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

    /*public void handleClearSearchText(ActionEvent event) {
        searchBox.setText("");
        event.consume();
    }*/


    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
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
        //this.editButton.setVisible(false);
    }
    /**
     * Called when the user clicks the person to edit. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
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
