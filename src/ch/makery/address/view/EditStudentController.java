package ch.makery.address.view;

import ch.makery.address.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ch.makery.address.model.Student;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
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
    private Button editButton;
    @FXML
    private Button viewButton;

    private int birthYear;
    private final String[] promotion = {"L3","M1","M2"};
    private final String[] option = {"Imaging","Physio","Biotech"};


    private Stage dialogStage;
    private Student person;
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
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1950, 2100);
        //valueFactory.setValue(student.getBirthYear());
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
        promotionBox.setOnAction(this::getPromotion); // pour mettre OnAction sur la choiceBox

        optionBox.getItems().addAll(option);
        optionBox.setOnAction(this::getOption); // pour mettre OnAction sur la choiceBox

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
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    /**
     * Sets the person to be edited in the dialog.
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.person = student;

        idField.setText(Integer.toString(student.getID()));
        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());

        /*promoField.setText(student.getPromo());
        specialityField.setText(student.getSpeciality());
        birthyearField.setText(Integer.toString(student.getBirthyear()));
*/
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
            //optionBox.setValue(null);
        }
        else {
            optionBox.setDisable(false);
        }
        return promotionBox.getValue();
        //
        //return(promotion);
    }
    public String getOption (ActionEvent e) {
        if (getPromotion(e).equals("M1") || getPromotion(e).equals("M2")) {
            //String option = optionBox.getValue();
            //return option;
            return optionBox.getValue();
        }
        else {
            return null;
        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
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
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setID(Integer.parseInt(idField.getText()));
            //person.setPromo(promoField.getText());
            person.setPromo(promotionBox.getValue());
            //person.setSpeciality(specialityField.getText());
            if (promotionBox.getValue().equals("L3")){
                person.setSpeciality(null);
            }else{
                person.setSpeciality(optionBox.getValue());
            }
            //person.setBirthyear(Integer.parseInt(birthyearField.getText()));
            person.setBirthyear(birthYearSpinner.getValue());

            okClicked = true;
            if (mainApp.getState()=="Add") {
                mainApp.getStudentData().add(person); //on ajoute l'étudiant
                mainApp.setState("View"); //On affiche la page view avec le view disable
            }
            mainApp.showStudentOverview(); //On affiche la page View
            //dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        if (mainApp.getState()=="Add") {
            mainApp.setState("View"); //On affiche la page view avec le view disable
        }
        mainApp.showStudentOverview();
    }

    /**
     * Validates the user input in the text fields.
     *
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
        }else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(idField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid id (must be an integer)!\n";
            }
        }

        /*if (promoField.getText() == null || promoField.getText().length() == 0) {
            errorMessage += "No valid promo!\n";
        }*/

        if (promotionBox.getValue().equals("M1") || promotionBox.getValue().equals("M2")) {
            if (optionBox.getValue() == null || optionBox.getValue().length() == 0) {
                errorMessage += "No valid speciality!\n";
            }
        }
        /*
        if (birthyearField.getText() == null || birthyearField.getText().length() == 0) {
            errorMessage += "No valid birthyear!\n";
        } *//*else {
            if (!DateUtil.validDate(birthyearField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }*/

        if (errorMessage.length() == 0) {
            return true;
        } else {
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



    /**
     * Called when the user clicks the edit button. Opens the list of students
     * to chose the one to edit.
     *//*
    @FXML
    private void clickEditButton() {
        //edit Button
        editButton.setDisable(true);
        //view Button
        viewButton.setDisable(false);
    }*/

    /**
     * Called when the user clicks the edit button. Opens the list of students
     * to chose the one to edit.
     *//*
    @FXML
    private void clickViewButton() {
        //edit Button
        editButton.setDisable(false);
        //view Button
        viewButton.setDisable(true);
    }*/
}