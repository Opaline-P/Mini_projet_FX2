package ch.makery.address.view;

import ch.makery.address.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ch.makery.address.model.Student;

/**
 * Dialog to edit details of a student.
 *
 *  @author Group 35
 * IHM Project - Java FX programmming
 */
public class EditStudentController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField promoField;
    @FXML
    private TextField specialityField;
    @FXML
    private TextField birthyearField;
    @FXML
    private Spinner<Integer> birthYearSpinner;
    @FXML
    private ChoiceBox<String> promotionBox, optionBox;

    @FXML
    private Button editOkButton;
    @FXML
    private Button viewButton;

    private int birthYear;
    private final String[] promotion = {"L3","M1","M2"};
    private final String[] option = {"Imaging","Physio","Biotech"};


    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        //// Spinner ////
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1950, 2100, 1999);
        birthYearSpinner.setValueFactory(valueFactory);
        birthYear = birthYearSpinner.getValue();
        birthYearSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                birthYear = birthYearSpinner.getValue();
            }
        });

        //// ChoiceBox ////
        promotionBox.getItems().addAll(promotion);
        // pour mettre OnAction sur la choiceBox
        promotionBox.setOnAction(this::getPromotion);

        optionBox.getItems().addAll(option);
        // pour mettre OnAction sur la choiceBox
        optionBox.setOnAction(this::getOption);

    }

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
        if (mainApp.getState().equals("Add")){
            editOkButton.setText("Add");
        }
    }


    /**
     * Sets the student to be edited in the dialog.
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;

        idField.setText(Integer.toString(student.getID()));
        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1950, 2100, student.getBirthyear());
        birthYearSpinner.setValueFactory(valueFactory);

        promotionBox.setValue(student.getPromo());
        if (student.getPromo().equals("M1") || student.getPromo().equals("M2")) {
            optionBox.setValue(student.getSpeciality());
        }
        else {
            optionBox.setDisable(true);
            optionBox.setValue(null);
        }
    }

    public String getPromotion (ActionEvent e) {
        String promotion = promotionBox.getValue();
        if (promotion.equals("L3")) {
            optionBox.setDisable(true);
        }
        else {
            optionBox.setDisable(false);
        }
        return promotionBox.getValue();
    }

    public String getOption (ActionEvent e) {
        if (getPromotion(e).equals("M1") || getPromotion(e).equals("M2")) {
            return optionBox.getValue();
        }
        else {
            return null;
        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return okClicked boolean
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            confirm(dialogStage);
        }
    }

    /**
     * Asked a confirmation before addind a student
     * @param stage Stage
     */
    public void confirm (Stage stage) {
        // The pop up window before exiting
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        if (mainApp.getState().equals("Edit")) {
            alert.setHeaderText("You're about to edit a student !");
            alert.setContentText("Are you sure you want to edit this student ? ");
        }
        if (mainApp.getState().equals("Add")) {
            alert.setHeaderText("You're about to add a student !");
            alert.setContentText("Are you sure you want to add this student ? ");
        }
        if (alert.showAndWait().get() == ButtonType.OK) { // if user click on OK button
            student.setFirstName(firstNameField.getText());
            student.setLastName(lastNameField.getText());
            student.setID(Integer.parseInt(idField.getText()));
            student.setPromo(promotionBox.getValue());
            if (promotionBox.getValue().equals("L3")){
                student.setSpeciality(null);
            }
            else {
                student.setSpeciality(optionBox.getValue());
            }
            student.setBirthyear(birthYearSpinner.getValue());
            okClicked = true;

            if (mainApp.getState().equals("Edit")) {
                System.out.println("Student edit with success !");
            }
            if (mainApp.getState().equals("Add")) {
                mainApp.getStudentData().add(student); //on ajoute l'étudiant
                System.out.println("Student add with success !");
                //On affiche la page view avec le view disable
                mainApp.setState("View");
            }
            //On affiche la page View
            mainApp.showStudentOverview();
        }
        else {
            alert.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        if (mainApp.getState()=="Add") {
            //On affiche la page view avec le view disable
            mainApp.setState("View");
        }
        mainApp.showStudentOverview();
    }

    /**
     * Validates the user input in the text fields.
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (idField.getText() == null || idField.getText().length() == 0) {
            errorMessage += "No valid id!\n";
        }
        else {
            try {
                Integer.parseInt(idField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid id (must be an integer)!\n";
            }
        }

        if (promotionBox.getValue().equals("M1") || promotionBox.getValue().equals("M2")) {
            if (optionBox.getValue() == null || optionBox.getValue().length() == 0) {
                errorMessage += "No valid speciality!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Error message : " + errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}