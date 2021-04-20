/*
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Student;
import ch.makery.address.util.DateUtil;

*/
/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 *//*

public class PersonEditDialogController {

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
    private TextField birthYearField;



    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    */
/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     *//*

    @FXML
    private void initialize() {
    }

    */
/**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     *//*

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    */
/**
     * Sets the person to be edited in the dialog.
     *
     * @param student
     *//*

    public void setPerson(Student student) {
        this.student = student;

        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        //streetField.setText(person.getStreet());
        //postalCodeField.setText(Integer.toString(person.getPostalCode()));
        //cityField.setText(person.getCity());
        //birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
        idField.setText(Integer.toString(person.getID()));
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());

        promoField.setText(person.getPromo());
        specialityField.setText(person.getSpeciality());
        birthYearField.setText(Integer.toString(person.getBirthyear()));
    }

    */
/**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     *//*

    public boolean isOkClicked() {
        return okClicked;
    }

    */
/**
     * Called when the user clicks ok.
     *//*

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            student.setFirstName(firstNameField.getText());
            student.setLastName(lastNameField.getText());
            //person.setStreet(streetField.getText());
            //person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            //person.setCity(cityField.getText());
            //person.setBirthday(DateUtil.parse(birthdayField.getText()));
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setID(Integer.parseInt(idField.getText()));
            person.setPromo(promoField.getText());
            person.setSpeciality(specialityField.getText());
            person.setBirthyear(Integer.parseInt(birthYearField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    */
/**
     * Called when the user clicks cancel.
     *//*

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    */
/**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     *//*

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
        }else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(idField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid id (must be an integer)!\n";
            }
        }

        if (promoField.getText() == null || promoField.getText().length() == 0) {
            errorMessage += "No valid promo!\n";
        }

        if (promoField.getText().equals("M1") || promoField.getText().equals("M2")) {
            if (specialityField.getText() == null || specialityField.getText().length() == 0) {
                errorMessage += "No valid speciality!\n";
            }
        }

        if (birthYearField.getText() == null || birthYearField.getText().length() == 0) {
            errorMessage += "No valid birthyear!\n";
        } */
/*else {
            if (!DateUtil.validDate(birthyearField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }*//*


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}*/
