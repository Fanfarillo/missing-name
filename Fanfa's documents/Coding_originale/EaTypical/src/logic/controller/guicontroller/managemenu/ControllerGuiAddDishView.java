/**
 * Sample Skeleton for 'AddDish.fxml' Controller Class
 */

package logic.controller.guicontroller.managemenu;

import logic.controller.guicontroller.OwnerBaseGuiController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import logic.engineeringclasses.bean.managerestaurant.*;
import logic.engineeringclasses.others.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * 
 * @author Luca Capotombolo
 *
 */


public class ControllerGuiAddDishView extends OwnerBaseGuiController{
	
	private List<String> listaP;
	private List<String> listaR;
	private int errorDish;
	private BeanErrorDishAlreadyExists beanErrorDishAlreadyExists;
	
	public ControllerGuiAddDishView(List<String> listP, List<String> listR,int errorDish, BeanErrorDishAlreadyExists beanErrorDishAlreadyExists,Session bs) {
    	super(bs);
		this.listaP = listP;
    	this.listaR = listR;
    	this.errorDish = errorDish;
    	this.beanErrorDishAlreadyExists =  beanErrorDishAlreadyExists;
    }

	private static final String MANCANTE = "Mancante";
	@FXML
	private Label campoMancantePiatto;
	
	@FXML
	private Label campoMancanteRistorante;
	
	@FXML
	private Label campoMancantePrezzo;
	
	@FXML
	private Label campoMancanteRicetta;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;   

    @FXML // fx:id="nomeUtenteLabel"
    private Label nomeUtenteLabel; // Value injected by FXMLLoader

    @FXML // fx:id="scegliPiattoBox"
    private ChoiceBox<String> scegliPiattoBox; // Value injected by FXMLLoader

    @FXML // fx:id="priceTextField"
    private TextField priceTextField; // Value injected by FXMLLoader

    @FXML // fx:id="veganCheckBox"
    private CheckBox veganCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="celiacCheckBox"
    private CheckBox celiacCheckBox; // Value injected by FXMLLoader
    
    @FXML
    private Label errorePiattoLabel;

    @FXML // fx:id="okButton"
    private Button okButton; // Value injected by FXMLLoader

    @FXML // fx:id="scegliRistorante"
    private ChoiceBox<String> scegliRistorante; // Value injected by FXMLLoader

    @FXML // fx:id="contenutoRicetta"
    private TextArea contenutoRicetta; // Value injected by FXMLLoader
    
    
    
   @FXML
    void goToConfirmMessageView(ActionEvent event) throws  IOException {
    	
    	//dichiaro ed inizializzo il contatore
    	int count = 0;
    	
    	//definisco ed inizializzo i parametri del costruttore del controller grafico della view successiva
    	String content = contenutoRicetta.getText();
    	String restaurant = scegliRistorante.getValue();
    	String dish = scegliPiattoBox.getValue(); 
    	String prezzoString = priceTextField.getText();    	
    	boolean vegan = veganCheckBox.isSelected();
    	boolean celiac = celiacCheckBox.isSelected();
    	
    	
    	if(prezzoString.equals(""))
    	{
    		campoMancantePrezzo.setText(MANCANTE);
    		count++;
    	}else {
    		
    		campoMancantePrezzo.setText("");
    	}
    		
    	
    	if(content.equals(""))
    	{    		
    		campoMancanteRicetta.setText(MANCANTE);
    		count++;
    	}else {
    		campoMancanteRicetta.setText("");
    		
    	}
    	
    	if(restaurant==null)
    	{
    		campoMancanteRistorante.setText(MANCANTE);
    		count++;
    	}else {
    		campoMancanteRistorante.setText("");
    	}
    	if(dish==null)
    	{
    		campoMancantePiatto.setText(MANCANTE);
    		count++;
    	}else {
    		campoMancantePiatto.setText("");
    	}
    	
    	//verifico se vi ÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Â ÃƒÂ¢Ã¢â€šÂ¬Ã¢â€žÂ¢ÃƒÆ’Ã†â€™ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¨ almeno un campo che non ÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Â ÃƒÂ¢Ã¢â€šÂ¬Ã¢â€žÂ¢ÃƒÆ’Ã†â€™ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â¨ stato riempito
    	if(count>0) return;
    	
    	//faccio la conversione del prezzo essendo sicuramente diverso da empty string
    	double prezzo = Double.parseDouble(prezzoString);
    	
    	//cambio scena settando i giusti valori
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/logic/view/standalone/managemenu/ConfirmMessageView.fxml"));
    	BeanDish beanAddDish = new BeanDish(dish, restaurant, content, vegan, celiac, prezzo, 0);
    	loader.setControllerFactory(c -> new ControllerGuiConfirmMessageView(beanAddDish,bs));
    	Parent rootParent = loader.load();
    	myAnchorPane.getChildren().setAll(rootParent);
    }

    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
        assert scegliPiattoBox != null : "fx:id=\"scegliPiattoBox\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert priceTextField != null : "fx:id=\"priceTextField\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert veganCheckBox != null : "fx:id=\"veganCheckBox\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert celiacCheckBox != null : "fx:id=\"celiacCheckBox\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert scegliRistorante != null : "fx:id=\"scegliRistorante\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert contenutoRicetta != null : "fx:id=\"contenutoRicetta\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert campoMancantePiatto != null : "fx:id=\"campoMancantePiatto\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert campoMancanteRicetta != null : "fx:id=\"campoMancanteRicetta\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert campoMancantePrezzo != null : "fx:id=\"campoMancantePrezzo\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert campoMancanteRicetta != null : "fx:id=\"campoMancanteRicetta\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert errorePiattoLabel != null : "fx:id=\"errorePiattoLabel\" was not injected: check your FXML file 'AddDish.fxml'.";
		assert myAnchorPane != null : "fx:id=\"myAnchorPane\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert sponsorRestaurantButton != null : "fx:id=\"sponsorRestaurantButton\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert manageMenuButton != null : "fx:id=\"manageMenuButton\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'AddDish.fxml'.";
        assert nomeUtenteLabel != null : "fx:id=\"nomeUtenteLabel\" was not injected: check your FXML file 'AddDish.fxml'.";
        
        //carico i piatti che puÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â² scegliere
        for(int i = 0; i<this.listaP.size();i++) {
        	scegliPiattoBox.getItems().add(this.listaP.get(i));
        }
        //carico i ristoranti che puÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â² scegliere
        for(int i = 0; i<this.listaR.size();i++) {
        	scegliRistorante.getItems().add(this.listaR.get(i));
        }
        //imposto il nome utente
        nomeUtenteLabel.setText(bs.getUser().getUsername());
        
        //verifico se provengo da un errore di inserimento
        if(this.errorDish == 0) {
        	errorePiattoLabel.setText(this.beanErrorDishAlreadyExists.getMess());
        }
    }
}
