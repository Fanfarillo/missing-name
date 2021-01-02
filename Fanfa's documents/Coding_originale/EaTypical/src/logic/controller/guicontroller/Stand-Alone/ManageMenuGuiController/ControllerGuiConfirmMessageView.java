/**
 * Sample Skeleton for 'ConfirmMessageView.fxml' Controller Class
 */

package logic.controller.guicontroller.ManageMenuGuiController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerGuiConfirmMessageView {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="homeButton"
    private Button homeButton; // Value injected by FXMLLoader

    @FXML // fx:id="manageMenuButton"
    private Button manageMenuButton; // Value injected by FXMLLoader

    @FXML // fx:id="sponsorMenuButton"
    private Button sponsorMenuButton; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="nomeUtenteLabel"
    private Label nomeUtenteLabel; // Value injected by FXMLLoader

    @FXML // fx:id="doneButton"
    private Button doneButton; // Value injected by FXMLLoader

    @FXML // fx:id="discardAllTheChangesButton"
    private Button discardAllTheChangesButton; // Value injected by FXMLLoader

    @FXML // fx:id="keepManagingMenuButton"
    private Button keepManagingMenuButton; // Value injected by FXMLLoader

    @FXML
    void discardChanges(ActionEvent event) {
    	System.out.println("discard\n");
    }

    @FXML
    void done(ActionEvent event) {
    	System.out.println("done\n");
    }

    @FXML
    void goBack(ActionEvent event) {
    	System.out.println("back\n");
    }

    @FXML
    void goToHome(ActionEvent event) {
    	System.out.println("home\n");
    }

    @FXML
    void keepManagingMenu(ActionEvent event) {
    	System.out.println("keep\n");
    }

    @FXML
    void manageMenu(ActionEvent event) {
    	System.out.println("manage\n");
    }

    @FXML
    void sponsorMenu(ActionEvent event) {
    	System.out.println("sponsor\n");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'ConfirmMessageView.fxml'.";
        assert manageMenuButton != null : "fx:id=\"manageMenuButton\" was not injected: check your FXML file 'ConfirmMessageView.fxml'.";
        assert sponsorMenuButton != null : "fx:id=\"sponsorMenuButton\" was not injected: check your FXML file 'ConfirmMessageView.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'ConfirmMessageView.fxml'.";
        assert nomeUtenteLabel != null : "fx:id=\"nomeUtenteLabel\" was not injected: check your FXML file 'ConfirmMessageView.fxml'.";
        assert doneButton != null : "fx:id=\"doneButton\" was not injected: check your FXML file 'ConfirmMessageView.fxml'.";
        assert discardAllTheChangesButton != null : "fx:id=\"discardAllTheChangesButton\" was not injected: check your FXML file 'ConfirmMessageView.fxml'.";
        assert keepManagingMenuButton != null : "fx:id=\"keepManagingMenuButton\" was not injected: check your FXML file 'ConfirmMessageView.fxml'.";

    }
}
