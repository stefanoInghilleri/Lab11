package it.polito.tdp.bar;

import it.polito.tdp.bar.model.Eventi;
import it.polito.tdp.bar.model.Model;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class BarController {
	
	Map<Integer,Integer> tavoliPresenti;
	Model model;
	Eventi e;
	int time=0;
	int num_persone=0;
	int durata=0;
	float tolleranza=0;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	model.getTimeManager().clear();
    }

    @FXML
    void doSimula(ActionEvent event) {
    	    	
    	List<String> result=model.simula(tavoliPresenti);
    	  for(String s: result)
    		txtResult.appendText(s+"\n");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Bar.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Bar.fxml'.";
        time=0;
    	num_persone=0;
    	durata=0;
    	tolleranza=0;
    	tavoliPresenti=new TreeMap<Integer,Integer>();

    }


	public void setModel(Model model) {
		this.model=model;
		tavoliPresenti.put(11, 2000);
		tavoliPresenti.put(10, 2);
		tavoliPresenti.put(8, 4);
		tavoliPresenti.put(6, 4);
		tavoliPresenti.put(4, 5);
    	
    	for(int i=0;i<2000;i++){
    		num_persone=(int)(1+Math.random()*10);
    		durata=(int)(100+Math.random()*60);
    		tolleranza=(float) Math.random();
    		e=new Eventi(time, num_persone, durata, tolleranza);
    		model.addEvento(e);
    		time+=(int)(1+Math.random()*10);
    	}

	}
	
}
