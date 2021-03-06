package logic.controller.guicontroller.chooserestaurant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import logic.controller.guicontroller.ItalianViewBaseGuiController;
import logic.engineeringclasses.exceptions.EmptyFieldException;
import logic.engineeringclasses.others.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerGuiItalianViewCityCR extends ItalianViewBaseGuiController{
	
	ObservableList<String> list=FXCollections.observableArrayList();
	
	private String restViewPage = "/logic/view/standalone/ChooseRestaurant/RestaurantView.fxml";
	
	
	public ControllerGuiItalianViewCityCR(Session bs)
	{
		super(bs);
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button searchButton;

    @FXML
    private Label nomeUtente;
    
    @FXML
    private Label noCityLabel;

    @FXML
    private ChoiceBox<String> choiceBox;


    
 

    @FXML
    void search(ActionEvent event) throws IOException {
    	try    	
    	{
	    	String city=choiceBox.getValue();
	    	if(city==null){
				throw new EmptyFieldException("There is no city selected.");
			}	    	
	    	FXMLLoader loader=new FXMLLoader(getClass().getResource(this.restViewPage));
			loader.setControllerFactory(c -> new ControllerGuiRestaurantView(city, bs));
			Parent root=loader.load();
			myAnchorPane.getChildren().setAll(root);
    	}
    	catch(EmptyFieldException efe)
    	{
    		this.noCityLabel.setText(efe.getMessage());
    		this.noCityLabel.setVisible(true);
    	}
    }

    

    @FXML
    void initialize() {
    	assert myAnchorPane != null : "fx:id=\"pane\" was not injected: check your FXML file 'TripSettingsView.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'ItalianViewCity.fxml'.";
        assert scheduleTripButton != null : "fx:id=\"scheduleTripButton\" was not injected: check your FXML file 'ItalianViewCity.fxml'.";
        assert chooseRestaurantButton != null : "fx:id=\"chooseRestaurantButton\" was not injected: check your FXML file 'ItalianViewCity.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'ItalianViewCity.fxml'.";
        assert nomeUtente != null : "fx:id=\"nomeUtente\" was not injected: check your FXML file 'ItalianViewCity.fxml'.";    
        commonInitializeOperations();
        if(this.bs.getUser()!=null)
        	nomeUtente.setText(this.bs.getUser().getUsername());
        else
        	nomeUtente.setText("Not logged");
        assert noCityLabel != null : "fx:id=\"noCityLabel\" was not injected: check your FXML file 'ItalianViewCity.fxml'.";
        assert mustLoginLabel != null : "fx:id=\"mustLoginLabel\" was not injected: check your FXML file 'ItalianViewCity.fxml'.";


    }
}
