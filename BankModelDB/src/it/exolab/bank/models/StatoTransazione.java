package it.exolab.bank.models;

public class StatoTransazione {
	private Integer id;
	private String esito;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public StatoTransazione(String esito) {
		super();
		this.esito = esito;
	}
	public StatoTransazione() {
		super();
	}
	public StatoTransazione(Integer id, String esito) {
		super();
		this.id = id;
		this.esito = esito;
	}
	
	@Override
	public String toString() {
		return "StatoTransazione [id=" + id + ", esito=" + esito + "]";
	}
	
	

}
