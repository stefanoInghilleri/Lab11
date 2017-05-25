package it.polito.tdp.bar.model;

import java.util.*;
import java.util.stream.Stream;

import it.polito.tdp.bar.model.Eventi.EventType;

public class Model {
	
	//parametri di simulazione
	private Map<Integer,Integer> tavoliTotali;
	private NavigableMap<Integer,Eventi> timeManager;
	
	//stato del modello
	//private List<Integer> tavoliLiberi;
	int tempoDiUscita=0;
	
	//variabili di interesse
	private Statistiche statistica;
	
	//lista degli eventi
	PriorityQueue<Eventi>queue;
	private List<Eventi> coda=new ArrayList<Eventi>();
	
	public Model(){
		this.queue = new PriorityQueue<>() ;
		this.statistica = new Statistiche();
		this.timeManager=new TreeMap<>();
	}


	public List<String> simula(Map<Integer,Integer> tavoliPresenti) {
		tavoliTotali=new TreeMap<Integer,Integer>(tavoliPresenti);
		List<String> result=new LinkedList<String>();
		int j=0;
		int f=0;
		while(1>0){
			//System.out.println("la coda grande" + coda.size());
			if (timeManager.higherKey(j) ==null && coda.size()==0) {
				break;
				}
			if(coda.size()==0) {
				j=timeManager.higherKey(j);
				f=1;
			} else {
				if(timeManager.higherKey(j)==null) {
					f=0;
				} else {
					if(timeManager.higherKey(j)<coda.get(0).getTime()){
						j=timeManager.higherKey(j);
						f=1;
					} else {
						f=0;				
					}
				}
			}
			//System.out.println("f vale" +f);	
			if (f==1){
				this.assegnaTavolo(timeManager.get(j));
				result.add(statistica.toString());
				} else {
			//		System.out.println("liberato"+timeManager.get(j).getTime());
					this.liberaTavolo(coda.get(0));
					coda.remove(0);
			}
		}		
		return result;
	}


	private void liberaTavolo(Eventi e) {
		tavoliTotali.put(e.getNumeroTavolo(), tavoliTotali.get(e.getNumeroTavolo())+1);		
	}

	private void assegnaTavolo(Eventi e) {
		
		statistica.setNumero_totale_clienti(e.getNum_persone());
			
		for(int j: tavoliTotali.keySet()){
			if ((e.getNum_persone()<=j)&&(tavoliTotali.get(j)>0)){
				
		/*		if((2*e.getNum_persone()<=j)) {
					j=11;
			}*/
			System.out.println(j);	
				e.setNumeroTavolo(j);
				tavoliTotali.put(j, tavoliTotali.get(j)-1);
				break;
			}
		}
		
		if(e.getNumeroTavolo()==11){
			float tolleranzaGenerata=(float) Math.random();
		//	System.out.println("t1: "+tolleranzaGenerata+"-->");
			if(e.getTolleranza()<tolleranzaGenerata){
				statistica.setNumero_clienti_insoddisfatti(e.getNum_persone());
				this.liberaTavolo(e);
	//			System.out.println("scartato "+e.getTime());
				return;
			}
		}	
		
		statistica.setClienti_soddisfatti(e.getNum_persone());
		e.setType(EventType.USCITA_GRUPPO_CLIENTI);	
		
		coda.add(coda.size(), e);
	
		timeManager.put(e.getTime()+e.getDurata(), e);
//		System.out.println("Aggiunto "+ e.getTime()+" da liberare "+(e.getTime()+e.getDurata()));

	}

	public void addEvento(Eventi e) {
		timeManager.put(e.getTime(), e);
	}

}
