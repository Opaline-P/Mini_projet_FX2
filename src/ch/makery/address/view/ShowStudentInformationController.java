package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Student;
import ch.makery.address.util.DateUtil;
import javafx.beans.property.IntegerProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowStudentInformationController {
    @FXML
    private TableView<Student> personTable;
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
    private Label birthdayLabel;

    private Student person;


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
        this.person = student;

        idLabel.setText(Integer.toString(student.getID()));
        firstNameLabel.setText(student.getFirstName());
        lastNameLabel.setText(student.getLastName());
        birthdayLabel.setText(Integer.toString(student.getBirthyear()));

        /*promoField.setText(student.getPromo());
        specialityField.setText(student.getSpeciality());
        birthyearField.setText(Integer.toString(student.getBirthyear()));
*/
        promoLabel.setText(student.getPromo());
        if (student.getPromo().equals("M1") || student.getPromo().equals("M2")) {
            specialityLabel.setText(student.getSpeciality());
        }
        else {
            specialityLabel.setText(null);
        }
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        if (mainApp.getState()=="View") {
            Student selectedStudent = personTable.getSelectionModel().getSelectedItem();
            personTable.getItems().remove(selectedStudent);
        }
    }


    private void handleEditPerson() {
        if (mainApp.getState() == "View") {
            Student selectedStudent = personTable.getSelectionModel().getSelectedItem();
        }
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
