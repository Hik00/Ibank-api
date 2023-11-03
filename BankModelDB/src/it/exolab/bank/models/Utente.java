package it.exolab.bank.models;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Utente {
    private Integer id;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String email;
    private String password;
    private String genere;

  
   private Ruolo ruolo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
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

	public String getDataNascita() {
	    if (dataNascita != null) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
	        return sdf.format(dataNascita);
	    } else {
	        return null; // o una stringa vuota a seconda delle esigenze
	    }
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", email=" + email + ", password=" + password + ", genere=" + genere
				+ ", ruolo=" + ruolo + "]";
	}



	public Utente(String nome, String cognome, Date dataNascita, String email, String password, String genere,
			Ruolo ruolo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.password = password;
		this.genere = genere;
		this.ruolo = ruolo;
	}
	
	public Utente(String nome, String cognome, Date dataNascita, String email, String password, String genere
			) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.password = password;
		this.genere = genere;

	}

	public Utente() {
		super();
	}

	public Utente(Integer id, String nome, String cognome) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	
	
	
}
