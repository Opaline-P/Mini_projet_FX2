package ch.makery.address.view;

import ch.makery.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HomePageController {
    @FXML
    private TextField textFieldSearch;

    @FXML
    private Button ButtonViewList;

    @FXML
    private Button ButtonEditStudent;

    @FXML
    private Button ButtonAddStudent;

    //Reference to the main application
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public HomePageController() {
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
