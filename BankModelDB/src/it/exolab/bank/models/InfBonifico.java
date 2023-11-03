package it.exolab.bank.models;

public class InfBonifico {
	private Integer id;
	private String nome;
	private String cognome;
	private String ibanDestinatario;
	private String causale;
	
	private Transazione transazione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIbanDestinatario() {
		return ibanDestinatario;
	}

	public void setIbanDestinatario(String ibanDestinatario) {
		this.ibanDestinatario = ibanDestinatario;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public Transazione getTransazione() {
		return transazione;
	}

	public void setTransazione(Transazione transazione) {
		this.transazione = transazione;
	}

	public InfBonifico(String nome, String cognome, String ibanDestinatario, String causale, Transazione transazione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.ibanDestinatario = ibanDestinatario;
		this.causale = causale;
		this.transazione = transazione;
	}

	public InfBonifico() {
		super();
	}

	@Override
	public String toString() {
		return "InfBonifico [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", ibanDestinatario="
				+ ibanDestinatario + ", causale=" + causale + ", transazione=" + transazione + "]";
	}
	
	
	
}
