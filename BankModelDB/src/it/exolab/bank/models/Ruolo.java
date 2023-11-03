package it.exolab.bank.models;

public class Ruolo {
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
	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", tipo=" + tipo + "]";
	}
    

    
}

