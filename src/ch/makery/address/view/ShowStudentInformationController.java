package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ShowStudentInformationController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label promoLabel;
    @FXML
    private Label specialityLabel;
    @FXML
    private Label optionLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private FilteredList<Student> filteredData;

    private Student student;


    // Reference to the main application.
    private MainApp mainApp;
    private Stage dialogStage;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Fills all text fields to show details about the person.
     *
     * @param student the student or null
     */
    /*private void showPersonDetails(Student student) {
        if (student != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(student.getFirstName());
            lastNameLabel.setText(student.getLastName());
            promoLabel.setText(student.getPromo());
            if (student.getPromo().equals("M1") || student.getPromo().equals("M2")) {
                specialityLabel.setText(student.getSpeciality());
            }
            else {
                specialityLabel.setText(null);
            }

            birthdayLabel.setText(Integer.toString(student.getBirthyear()));

        }
    }*/

    public void getStudent (Student student) {
        this.student = student;

        idLabel.setText(Integer.toString(student.getID()));
        firstNameLabel.setText(student.getFirstName());
        lastNameLabel.setText(student.getLastName());
        birthdayLabel.setText(Integer.toString(student.getBirthyear()));

        promoLabel.setText(student.getPromo());
        if (student.getPromo().equals("M1") || student.getPromo().equals("M2")) {
            specialityLabel.setText(student.getSpeciality());
        }
        else {
            specialityLabel.setText(null);
            optionLabel.setVisible(false);
        }
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        studentTable.setItems(mainApp.getStudentData());
    }

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

    @FXML
    private void handleEditPerson() {
        mainApp.setState("Edit");
        mainApp.showStudentEditDialog(this.student);
    }

    /**
    * Called when the user clicks cancel.
    */
    @FXML
    private void handleCancel() {
        mainApp.setState("View"); //On affiche la page view avec le view disable
        mainApp.showStudentOverview();
    }

}
