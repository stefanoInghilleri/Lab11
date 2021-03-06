package it.polito.tdp.bar.model;

public class Statistiche {
	
	private int numero_totale_clienti;
	private int clienti_soddisfatti;
	private int numero_clienti_insoddisfatti;

	public Statistiche(){
		this.numero_totale_clienti=0;
		this.clienti_soddisfatti=0;
		this.numero_clienti_insoddisfatti=0;
	}
	
	public int getNumero_totale_clienti() {
		return numero_totale_clienti;
	}

	public void setNumero_totale_clienti(int numero_totale_clienti) {
		this.numero_totale_clienti += numero_totale_clienti;
	}

	public int getClienti_soddisfatti() {
		return clienti_soddisfatti;
	}

	public void setClienti_soddisfatti(int clienti_soddisfatti) {
		this.clienti_soddisfatti += clienti_soddisfatti;
	}

	public int getNumero_clienti_insoddisfatti() {
		return numero_clienti_insoddisfatti;
	}

	public void setNumero_clienti_insoddisfatti(int numero_clienti_insoddisfatti) {
		this.numero_clienti_insoddisfatti += numero_clienti_insoddisfatti;
	}

	@Override
	public String toString() {
		return "Statistiche [numero_totale_clienti=" + numero_totale_clienti + ", clienti_soddisfatti="
				+ clienti_soddisfatti + ", numero_clienti_insoddisfatti=" + numero_clienti_insoddisfatti + "]";
	};
	
	

}
