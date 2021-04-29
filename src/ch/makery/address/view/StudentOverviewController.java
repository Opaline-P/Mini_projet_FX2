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
    private TableView<Student> studentTable;
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
    private TextField searchBox;
    @FXML
    private StackPane searchStackPane;
    @FXML
    private AnchorPane searchAnchorPane;

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
        // Initialize the student table with the 6 columns.
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        birthyearColumn.setCellValueFactory(cellData -> cellData.getValue().birthyearProperty());
        promoColumn.setCellValueFactory(cellData -> cellData.getValue().promoProperty());
        specialityColumn.setCellValueFactory(cellData -> cellData.getValue().specialityProperty());

        //Management of the search box with predicate
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(createPredicate(newValue)));
    }

    /**
     * Is called by the main application to give a reference back to itself and use this reference.
     *
     * @param mainApp the mainApp used
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        //Filtered list when we are searching a student
        filteredData = new FilteredList<>(FXCollections.observableList(this.mainApp.getStudentData()));
        studentTable.setItems(filteredData);

        //Maneged the position of the search bar
        if (mainApp.getState()=="Edit") {
            clickEditButton();
        }if (mainApp.getState()=="View") {
            clickViewButton();
        }
    }

    /**
     * Create the predicate
     * @param searchText the search bar text
     * @return the predicate
     */
    private Predicate<Student> createPredicate(String searchText){
        return student -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsStudent(student, searchText);
        };
    }


    /**
     * Search if a student is containing the string in the search bar
     * @param student the student to compare with the search bar (id, names)
     * @param searchText the search bar text
     * @return true if the student is corresponding to the search false if not
     */
    private boolean searchFindsStudent(Student student, String searchText){
        return (student.getFirstName().toLowerCase().contains(searchText.toLowerCase())) ||
                (student.getLastName().toLowerCase().contains(searchText.toLowerCase())) ||
                Integer.valueOf(student.getID()).toString().contains(searchText); //ou .equals
    }


    /**
     * Called when the user click the cross next to the search bar to clear it
     */
    public void handleClearSearchText() {
        searchBox.setText("");
    }


    /**
     * Called when the user clicks the student to edit or to show. Opens a dialog to edit
     * details for the selected student.
     */
    @FXML
    private void handleEditStudent() {
        if (mainApp.getState()=="Edit") { //on edit table we open the edit dialog
            Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                boolean okClicked = mainApp.showStudentEditDialog(selectedStudent);
            }
            else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Student Selected");
                alert.setContentText("Please select a student in the table.");

                alert.showAndWait();
            }
        }else if (mainApp.getState()=="View") { //on view table we open the show student dialog
            Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                mainApp.setState("Show");
                mainApp.showStudentDialog(selectedStudent);
            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Student Selected");
                alert.setContentText("Please select a student in the table.");

                alert.showAndWait();
            }
        }
    }

    /**
     * Called to edit the search bar for the edit version
     *
     */
    @FXML
    private void clickEditButton() {
        //search Box
        handleClearSearchText();
        searchBox.setPromptText("Which student do you want to edit ?");
        searchAnchorPane.setLeftAnchor(searchStackPane, 100.0);
        searchAnchorPane.setRightAnchor(searchStackPane, 100.0);
    }


    /**
     * Called to edit the search bar for the view version
     *
     */
    @FXML
    private void clickViewButton() {
        //search Box
        handleClearSearchText();
        searchBox.setPromptText("Search...");
        searchAnchorPane.setLeftAnchor(searchStackPane, 400.0);
        searchAnchorPane.setRightAnchor(searchStackPane, 20.0);
    }

}
