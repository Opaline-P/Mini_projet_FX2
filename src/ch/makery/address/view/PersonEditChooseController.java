package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonEditChooseController {

    // TODO : maj des données du tableau en fct de la classe Student
    @FXML
    private TableView<Student> personTable;
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

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonEditChooseController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // TODO : maj des données en fct de la classe Student
        // Initialize the person table with the two columns.
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        //idColumn.setCellFactory(col -> new IntegerEditingCell());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        birthYearColumn.setCellValueFactory(cellData -> cellData.getValue().birthYearProperty());
        promoColumn.setCellValueFactory(cellData -> cellData.getValue().promotionProperty());
        promoColumn.setCellValueFactory(cellData -> cellData.getValue().optionProperty());
        //specialityColumn.setCellValueFactory(cellData -> cellData.getValue().specialityProperty());

        // Clear person details.
        //showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        //personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getStudentData());
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

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    // TODO : obliger de faire le changement de controller ici
    @FXML
    private void handleNewPerson(ActionEvent e) {
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
        catch (IOException exception) {
            System.out.println(exception);
        }

        /*boolean okClicked = mainApp.showEditStudent(tempStudent);
        if (okClicked) {
            mainApp.getStudentData().add(tempStudent);
        }*/
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    // TODO obliger de faire le changement de controller ici et non pas depuis main pck sinon
    //  les données de l'étudiant en question sont perdues
    @FXML
    private void handleEditPerson(ActionEvent e) {
        Student selectedStudent = personTable.getSelectionModel().getSelectedItem();
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

        }
        else {
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
