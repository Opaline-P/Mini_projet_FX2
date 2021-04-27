package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Student;
import ch.makery.address.view.RootLayoutController;
import ch.makery.address.view.EditStudentController;
import ch.makery.address.view.StudentOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

    public class MainApp extends Application {

        private Stage primaryStage;
        private BorderPane rootLayout;
        private String state;
        private RootLayoutController rootController;

        /**
         * The data as an observable list of Students.
         */
        private ObservableList<Student> studentData = FXCollections.observableArrayList();

        /**
         * Constructor
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
         * Returns the data as an observable list of Students.
         * @return
         */
        public ObservableList<Student> getStudentData() {
            return studentData;
        }

        @Override
        public void start(Stage primaryStage) {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("GPhyApp");

            initRootLayout();

            showStudentOverview();
        }

        /**
         * Initializes the root layout.
         */
        public void initRootLayout() {
            try {
                // Load root layout from fxml file.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ch.makery.address.MainApp.class.getResource("view/RootLayout.fxml"));
                //loader.setLocation(MainApp.class.getResource("C:\\Users\\opali\\Documents\\Cours\\M1 G_Phy\\S2\\IHM\\JavaFX\\JavaFXtuto\\src\\ch\\makery\\address\\view\\RootLayout.fxml"));
                rootLayout = (BorderPane) loader.load();

                // Show the scene containing the root layout.
                Scene scene = new Scene(rootLayout);
                //scene.getStylesheets().add(getClass().getResource("applicationStyle.css").toExternalForm());
                primaryStage.setScene(scene);
                //primaryStage.setHeight(400);
                //primaryStage.setWidth(700);

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
        public void showStudentOverview() {
            try {
                // Load student overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ch.makery.address.MainApp.class.getResource("view/StudentOverview.fxml"));
                AnchorPane studentOverview = (AnchorPane) loader.load();

                // Set student overview into the center of root layout.
                rootLayout.setCenter(studentOverview);
                rootController.setVisibleMessage(false);

                // Give the controller access to the main app.
                StudentOverviewController controller = loader.getController();
                controller.setMainApp(this);


            } catch (IOException e) {
                e.printStackTrace();
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
        public boolean showStudentEditDialog(Student student) {
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ch.makery.address.MainApp.class.getResource("view/EditStudent.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

            /*// Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);*/

                // Set student overview into the center of root layout.
                rootLayout.setCenter(page);
                rootController.setVisibleMessage(true);

                // Set the student into the controller.
                EditStudentController controller = loader.getController();
                //controller.setDialogStage(dialogStage);
                controller.setStudent(student);
                controller.setMainApp(this);

                // Show the dialog and wait until the user closes it
                //dialogStage.showAndWait();

                return controller.isOkClicked();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * Returns the main stage.
         *
         * @return primaryStage
         */
        public Stage getPrimaryStage() {return primaryStage;}

        /**
         * Returns the state
         *
         * @return state
         */
        public String getState() {return state;}

        /**
         * Setter the state
         *
         * @param state
         */
        public void setState(String state) {this.state=state;}


        public static void main(String[] args) {
            launch(args);
        }

    }
