/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAutoComp"
    private Button btnAutoComp; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorsi"
    private Button btnCercaIscrittiCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="choiceCorso"
    private ChoiceBox<Corso> choiceCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader
    
    @FXML
    void autoCompilazione(ActionEvent event) {
    	
    	String matricola = this.txtMatricola.getText();
    	
    	this.txtNome.setText(this.model.getStudente(matricola).getNome());
    	this.txtCognome.setText(this.model.getStudente(matricola).getCognome());

    }

    @FXML
    void cercaCorsi(ActionEvent event) {
    	
    	
    	String matricola = this.txtMatricola.getText();
    	
    	if (matricola == "") {
    		this.txtRisultato.setText("Nessuna matricola inserita");
    	}
    	
    	List<Corso> result = new ArrayList<Corso>();
    	
    	result = this.model.getCorsiPerMatricola(matricola);
    	
    	this.txtRisultato.clear();
    	
    	for(Corso c: result) {
    		this.txtRisultato.appendText(c.toString()+"\n");
    	}
    	

    }

    @FXML
    void cercaIscritti(ActionEvent event) {
    	
    	try {
    	Corso nomeCorso = this.choiceCorso.getValue();
        List<Studente> studentiXCorso = new ArrayList<Studente>();
    	
    	studentiXCorso = this.model.getStudentiIscrittiAlCorso(nomeCorso);
    	
    	this.txtRisultato.clear();
    	
    	for(Studente s : studentiXCorso) {
    		this.txtRisultato.appendText(s.toString()+"\n");
    	}
    	
    	}catch(NullPointerException e ) {
    		this.txtRisultato.setText("Nessun corso selezionato");
    		
    		
    	}
    	
    }

    @FXML
    void iscrivi(ActionEvent event) {
    	
    	String nomeCorso = this.txtNome.getText();
    	String matricola = this.txtMatricola.getText();
    	
    	boolean cntrl = this.model.inscriviStudenteACorso(matricola, nomeCorso);
    	
    	this.txtRisultato.clear();
    	if (cntrl == true) {
    		this.txtRisultato.setText("Studente iscritto al corso");
    	}
    	else {
    		this.txtRisultato.setText("Non iscritto");
    	}
    }

    @FXML
    void reset(ActionEvent event) {
    	
    	this.txtRisultato.clear();
    	this.txtRisultato.clear();

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	
    	List<Corso> riempimentoChoice = new ArrayList<Corso>();
        riempimentoChoice = this.model.getTuttiCorsi();
        
        for(Corso c: riempimentoChoice) {
        	this.choiceCorso.getItems().add(c);
        }
        
        this.choiceCorso.getItems().add(null);
        
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAutoComp != null : "fx:id=\"btnAutoComp\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorsi != null : "fx:id=\"btnCercaIscrittiCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert choiceCorso != null : "fx:id=\"choiceCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }

}
