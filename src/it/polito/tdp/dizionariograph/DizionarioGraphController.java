package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumeroLettere;

    @FXML
    private TextField txtParolaDaCercare;

    @FXML
    private TextArea txtRisultati;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	txtRisultati.clear();
    	model.createGraph(Integer.parseInt(txtNumeroLettere.getText()));
    	txtRisultati.appendText(model.grafo.toString());

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtNumeroLettere.clear();
    	txtParolaDaCercare.clear();
    	txtRisultati.clear();

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	txtRisultati.clear();
    	txtRisultati.appendText("Il grado massimo è  "+model.findMaxDegree()+ "\n");
    	txtRisultati.appendText("La parola con grado massimo è " + model.findMaxWord()+"\n");
    	txtRisultati.appendText("I suoi vicini sono  " + model.displayNeighbours(model.findMaxWord()).toString());
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	txtRisultati.clear();
    	if(model.grafo==null) {
    		txtRisultati.appendText("Inserisci un numero di lettere e crea il grafo ");
    	}
    	
    	txtRisultati.appendText("I vicini di "+txtParolaDaCercare.getText()+ " sono : " +model.displayNeighbours(txtParolaDaCercare.getText()).toString());
    	
    	

    }

    @FXML
    void initialize() {
        assert txtNumeroLettere != null : "fx:id=\"txtNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParolaDaCercare != null : "fx:id=\"txtParolaDaCercare\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtRisultati != null : "fx:id=\"txtRisultati\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
    public void setModel(Model model) {

		this.model = model;
	

	}
}
