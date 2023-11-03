package it.exolab.bank.models;

public class TipoTransazione {
	private Integer id;
	private String tipo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public TipoTransazione(String tipo) {
		super();
		this.tipo = tipo;
	}
	public TipoTransazione() {
		super();
	}
	
	@Override
	public String toString() {
		return "TipoTransazione [id=" + id + ", tipo=" + tipo + "]";
	}
	
	

}
