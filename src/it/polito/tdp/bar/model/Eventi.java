package it.polito.tdp.bar.model;

public class Eventi implements Comparable<Eventi>{
	
	public enum EventType {ARRIVO_GRUPPO_CLIENTI, USCITA_GRUPPO_CLIENTI};
	
	private int time;
	private int num_persone;
	private int durata;
	private float tolleranza;
	private EventType type;
	private int numeroTavolo;
	
	public Eventi(int time, int num_persone, int durata, float tolleranza, EventType type) {
		this.time = time;
		this.num_persone = num_persone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public int getNumeroTavolo() {
		return numeroTavolo;
	}

	public void setNumeroTavolo(int numeroTavolo) {
		this.numeroTavolo = numeroTavolo;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNum_persone() {
		return num_persone;
	}

	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	@Override
	public int compareTo(Eventi other) {
	
		return 0;
	}
	
	

}
