package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Student;
import ch.makery.address.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * MainApp for the application
 *
 *  @author Group 35
 * IHM Project - Java FX programmming
 */


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private String state; //to manage disabled buttons and others viewing
    private RootLayoutController rootController;

    /**
     * The data as an observable list of Students.
     */
    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    /**
     * Constructor of the MainApp
     */
    public MainApp() {
        // Add some sample data
        studentData.add(new Student(123, "Hans", "Muster", 1998, "M2", "Imaging"));
        studentData.add(new Student(4, "Werner", "Meyer", 1997, "M2", "Biotech"));
        studentData.add(new Student(185, "Martin", "Mueller", 1998, "M2", "Physio"));
        studentData.add(new Student(14, "Ruth", "Mueller", 1999, "M1", "Physio"));
        studentData.add(new Student(489, "Lydia", "Kunz", 1999, "M1", "Biotech"));
        studentData.add(new Student(487, "Stefan", "Meier", 1997, "M1", "Imaging"));
        studentData.add(new Student(456, "Heinz", "Kurz", 1998, "L3", null));
        studentData.add(new Student(785, "Cornelia", "Meier", 2000, "L3", null));
        studentData.add(new Student(1578, "Anna", "Best", 2000, "L3", null));
    }

    /**
     * Getter of StudentData
     * @return the data as an observable list of Students.
     */
    public ObservableList<Student> getStudentData() {
        return studentData;
    }

    /**
     * Start the application
     * @param primaryStage the primary stage used for the application
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GPhyApp");

        //Begin with the home page
        this.state = "Home";
        initRootLayout();
        showHomePage();

        // to ask a confirmation before exit
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });
    }


    /**
     * asked a confirmation before exiting
     * @param stage Stage
     */
    public void logout (Stage stage) {
        // pop up window before exiting
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit !");
        alert.setContentText("Are you sure you want to exit ? ");

        if (alert.showAndWait().get() == ButtonType.OK) { // if user click on OK button
            System.out.println("You successfully exited !");
            stage.close();
        }
    }


    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ch.makery.address.MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            rootController = loader.getController();
            rootController.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Shows the student overview inside the root layout.
     */
    public void showHomePage() {
        try {
            // Load student overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/HomePage.fxml"));
            AnchorPane HomePage = (AnchorPane) loader.load();

            // Set home page into the center of root layout.
            rootLayout.setCenter(HomePage);
            rootController.setVisibleMessage(false);
            rootController.setDisabledButton();

            // Give the controller access to the main app.
            HomePageController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Shows the student overview inside the root layout.
     */
    public void showStudentOverview() {
        try {
            // Load student overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ch.makery.address.MainApp.class.getResource("view/StudentOverview.fxml"));
            AnchorPane studentOverview = (AnchorPane) loader.load();

            // Set student overview into the center of root layout.
            rootLayout.setCenter(studentOverview);
            rootController.setVisibleMessage(false);
            rootController.setDisabledButton();

            // Give the controller access to the main app.
            StudentOverviewController controller = loader.getController();
            controller.setMainApp(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified student or to add a student. If the user
     * clicks OK, the changes are saved into the provided student object and true
     * is returned.
     *
     * @param student the student object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showStudentEditDialog(Student student) {
        try {
            // Load student edit dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ch.makery.address.MainApp.class.getResource("view/EditStudent.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Set edit and add dialog into the center of root layout.
            rootLayout.setCenter(page);
            rootController.setVisibleMessage(true);
            rootController.setDisabledButton();

            // Set the student into the controller.
            EditStudentController controller = loader.getController();
            controller.setStudent(student);
            controller.setMainApp(this);

            return controller.isOkClicked();
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to edit details for the specified student. If the user
     * clicks OK, the changes are saved into the provided student object and true
     * is returned.
     *
     * @param student the student object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public void showStudentDialog(Student student) {
        try {
            // Load show student dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ch.makery.address.MainApp.class.getResource("view/ShowStudentInformation.fxml"));
            AnchorPane showStudent = (AnchorPane) loader.load();

            // Set the show student dialog into the center of root layout.
            rootLayout.setCenter(showStudent);
            rootController.setVisibleMessage(true);
            rootController.setDisabledButton();

            // Set the student into the controller.
            ShowStudentInformationController controller = loader.getController();
            controller.getStudent(student);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter of primaryStage
     *
     * @return primaryStage the main stage.
     */
    public Stage getPrimaryStage() {return primaryStage;}

    /**
     * Getter of the state
     *
     * @return state the state
     */
    public String getState() {return state;}

    /**
     * Setter the state
     *
     * @param state the state
     */
    public void setState(String state) {this.state=state;}


    /**
     * The main of MainApp
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
