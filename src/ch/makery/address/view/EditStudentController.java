package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Student;
import ch.makery.address.util.DateUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditStudentController implements Initializable {
    @FXML
    private TextField firstNameTextField, lastNameTextField;
    @FXML
    private Spinner<Integer> birthYearSpinner;
    @FXML
    private ChoiceBox<String> promotionBox, optionBox;
    @FXML
    private ImageView studentImageView;
    @FXML
    private Button imageButton, editButton, cancelButton;

    private int birthYear;
    private final String[] promotion = {"L3","M1","M2"};
    private final String[] option = {"Imaging","Physio","Biotech"};

    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    // Reference to the main application.
    private MainApp mainApp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //// Spinner ////
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1950, 2100);
        valueFactory.setValue(student.getBirthYear());

        birthYearSpinner.setValueFactory(valueFactory);

        birthYear = birthYearSpinner.getValue();
        // myLabel.setText(Integer.toString(currentValue));

        birthYearSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                birthYear = birthYearSpinner.getValue();
                //myLabel.setText(Integer.toString(currentValue));
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
     * Sets the person to be edited in the dialog.
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;

        firstNameTextField.setText(student.getFirstName());
        lastNameTextField.setText(student.getLastName());
        promotionBox.setValue(student.getPromotion());
        if (student.getPromotion().equals("M1") || student.getPromotion().equals("M2")) {
            optionBox.setValue(student.getOption());
        }
        else {
            optionBox.setDisable(true);
            optionBox.setValue(null);
        }
    }

    public String getPromotion (ActionEvent e) {
        //String promotion = promotionBox.getValue();
        //return(promotion);
        return promotionBox.getValue();
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
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleEdit() {
        if (isInputValid()) {
            student.setFirstName(firstNameTextField.getText());
            student.setLastName(lastNameTextField.getText());
            student.setBirthYear(birthYearSpinner.getValue());
            student.setPromotion(promotionBox.getValue());
            student.setOption(optionBox.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Error message : " + errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
