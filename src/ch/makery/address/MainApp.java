package ch.makery.address;

import java.io.IOException;

import ch.makery.address.view.HomePageController;
import ch.makery.address.model.Student;
import ch.makery.address.view.EditStudentController;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    // The data as an observable list of Persons.
    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // TODO : maj des données etudiant en fct de la classe Student
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
     * Returns the data as an observable list of Persons.
     */
    public ObservableList<Student> getStudentData() {
        return studentData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GPhyApp");

        initRootLayout();

        //showHomePage();

        showPersonOverview();

        // to ask a confirmation before exit
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });
    }

    // TODO : rajouter pour demander une confirmation de fermeture de la fenêtre
    /**
     * asked a confirmation before exiting
     * @param stage Stage
     */
    public void logout (Stage stage) {
        // pop up window before exiting
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit !");
        alert.setContentText("Are you sure you want to exit ? All information not saved will be lost.");

        if (alert.showAndWait().get() == ButtonType.OK) { // if user click on OK button
            System.out.println("You successfully exited !");
            stage.close();
        }
    }

    /**
     * Initializes the root layout.
     */
    /*public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/EditStudent.fxml"));
            Parent root = loader.load();
            //loader.setLocation(MainApp.class.getResource("C:\\Users\\opali\\Documents\\Cours\\M1 G_Phy\\S2\\IHM\\JavaFX\\JavaFXtuto\\src\\ch\\makery\\address\\view\\RootLayout.fxml"));

            // Show the scene containing the root layout.
            Scene scene = new Scene(root, 700, 400);
            //scene.getStylesheets().add(getClass().getResource("applicationStyle.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            //loader.setLocation(MainApp.class.getResource("C:\\Users\\opali\\Documents\\Cours\\M1 G_Phy\\S2\\IHM\\JavaFX\\JavaFXtuto\\src\\ch\\makery\\address\\view\\RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add(getClass().getResource("applicationStyle.css").toExternalForm());
            primaryStage.setScene(scene);
            //primaryStage.setHeight(400);
            //primaryStage.setWidth(700);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the homepage overview inside the root layout.
     */
    public void showHomePage() {
        try {
            // Load homePage overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/HomePage.fxml"));
            AnchorPane HomePage = loader.load();

            // Set HomePage overview into the center of root layout.
            rootLayout.setCenter(HomePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param student the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    // TODO : il lui faut un ActionEvent pour que qqc se passe donc ça bloque si on le laisse dans le main
    public boolean showEditStudent(ActionEvent e, Student student) {
        try {
            /*// Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/EditStudent.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller
            EditStudentController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();*/

            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/EditStudent.fxml"));
            Parent root = loader.load();

            // changer de scene
            Stage editStage = new Stage();
            editStage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            editStage.setScene(scene);

            // relier au contoller
            EditStudentController controller = loader.getController();
            controller.setDialogStage(editStage);
            controller.setStudent(student);

            editStage.show();

            // TODO : obliger de laisser un return mais je ne m'en sert pas
            return controller.isOkClicked();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
