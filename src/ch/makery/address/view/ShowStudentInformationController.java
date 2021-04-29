package ch.makery.address.view;
/**
 * Show student information page for a selected student
 *
 *  @author Group 35
 * IHM Project - Java FX programmming
 */
import ch.makery.address.MainApp;
import ch.makery.address.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ShowStudentInformationController {
    @FXML
    private ObservableList<Student> studentData = FXCollections.observableArrayList();
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
    private Student student;

    // Reference to the main application.
    private MainApp mainApp;
    private Stage dialogStage;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() { }

    /**
     * Sets the stage of this dialog.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.studentData = mainApp.getStudentData();
        //studentTable.setItems(mainApp.getStudentData());
    }

    /**
     * Show the student information on the ShowInformationPage.
     * @param student the student or null
     */
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
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteStudent() {
        confirm(dialogStage);
    }

    /**
     * Called when the user clicks on the edit button.
     */
    @FXML
    private void handleEditStudent() {
        mainApp.setState("Edit");
        confirm(dialogStage);
        mainApp.showStudentEditDialog(this.student);
    }

    /**
    * Called when the user clicks cancel.
    */
    @FXML
    private void handleCancel() {
        if(mainApp.getState()=="Show") {
            mainApp.setState("View");
            mainApp.showStudentOverview();
        }
    }

    /**
     * asked a confirmation before delete a student
     * @param stage Stage
     */
    public void confirm (Stage stage) {
        // pop up window before the delete
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");

        if (mainApp.getState().equals("Show")) {
            alert.setHeaderText("You're about to delete a student !");
            alert.setContentText("Are you sure you want to delete this student ? ");

            // if user click on OK button
            if (alert.showAndWait().get() == ButtonType.OK) {
                this.studentData.remove(this.student);
                mainApp.setState("View");
                mainApp.showStudentOverview();
            }
            else {
                alert.close();
            }

        }
        else if (mainApp.getState().equals("Edit")) {
            alert.setHeaderText("You're about to edit the student !");
            alert.setContentText("Are you sure you want to continue ? ");
        }

    }

}
