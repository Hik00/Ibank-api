package it.exolab.bank.models;

public class StatoConto {
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
	@Override
	public String toString() {
		return "StatoConto [id=" + id + ", esito=" + esito + "]";
	}
	
	

}
