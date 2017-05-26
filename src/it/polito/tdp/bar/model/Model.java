package it.polito.tdp.bar.model;

import java.util.*;
import java.util.stream.Stream;

import it.polito.tdp.bar.model.Eventi;

public class Model {
	
	//parametri di simulazione
	private Map<Integer,Integer> tavoliTotali;
	
	//stato del modello
	private NavigableMap<Integer,Eventi> timeManager;
	private int tempo;
	
	//variabili di interesse
	private Statistiche statistica;
	
	//lista degli eventi
	private List<Eventi> coda;
	
	public Model(){
		this.statistica = new Statistiche();
		this.timeManager=new TreeMap<>();
		this.tempo=0;
		this.coda=new ArrayList<Eventi>();
	}


	public void addEvento(Eventi e) {
		timeManager.put(e.getTime(), e);
	}
	
	public NavigableMap<Integer, Eventi> getTimeManager() {
		return timeManager;
	}

	public List<String> simula(Map<Integer,Integer> tavoliPresenti) {
		tavoliTotali=new TreeMap<Integer,Integer>(tavoliPresenti);
		List<String> result=new LinkedList<String>();
		int flag=0;
		while(1>0){
			
			if (timeManager.higherKey(tempo) ==null && coda.size()==0)
				break;
			
			if(coda.size()==0) {
				tempo=timeManager.higherKey(tempo);
				flag=1;
			} else {
				if(timeManager.higherKey(tempo)==null) 
					flag=0;
				 else {
					if(timeManager.higherKey(tempo)<(coda.get(0).getTime())+coda.get(0).getDurata()){
						tempo=timeManager.higherKey(tempo);
						flag=1;
					} else 
						flag=0;				
				}
			}
		
			if (flag==1){
				this.assegnaTavolo(timeManager.get(tempo));
				result.add(statistica.toString());
				} else {
					this.liberaTavolo(coda.get(0));
					coda.remove(0);
			}
		}		
		return result;
	}


	private void liberaTavolo(Eventi e) {
		tavoliTotali.replace(e.getNumeroTavolo(), tavoliTotali.get(e.getNumeroTavolo())+1);		
	}

	private void assegnaTavolo(Eventi e) {
		
		statistica.setNumero_totale_clienti(e.getNum_persone());
		for(int j: tavoliTotali.keySet()){
			if ((e.getNum_persone()<=j)&&(tavoliTotali.get(j)>0)){
				
				if((0.5*j>=e.getNum_persone())) {
					j=11;
				}
		
				e.setNumeroTavolo(j);
				tavoliTotali.replace(j, tavoliTotali.get(j)-1);
				break;
			}
		}
		
		if(e.getNumeroTavolo()==11){
			float tolleranzaGenerata=(float) Math.random();
			if(e.getTolleranza()<tolleranzaGenerata){
				statistica.setNumero_clienti_insoddisfatti(e.getNum_persone());
				this.liberaTavolo(e);
				return;
			}
		}	
		
		statistica.setClienti_soddisfatti(e.getNum_persone());
		coda.add(coda.size(), e);
		timeManager.put(e.getTime()+e.getDurata(), e);
	}
}
