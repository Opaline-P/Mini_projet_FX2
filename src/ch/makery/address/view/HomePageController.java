package ch.makery.address.view;
/**
 * Home page for the application
 *
 *  @author Group 35
 * IHM Project - Java FX programmming
 */
import ch.makery.address.MainApp;

public class HomePageController {

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
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
