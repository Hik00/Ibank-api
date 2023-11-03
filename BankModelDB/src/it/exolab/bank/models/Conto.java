package it.exolab.bank.models;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Conto {
	private Integer id;
	private String iban;
	private int saldo;
	private Date dataApertura;
	private Date dataEsito;
	
	private Utente utente;
	private StatoConto statoConto;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIban() {
		return iban;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getDataApertura() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        return sdf.format(dataApertura);
	}
	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}
	public String getDataEsito() {
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
	        return sdf.format(dataEsito);
	    } catch (NullPointerException e) {
	        return "N/D";
	    }
	}
	public void setDataEsito(Date dataEsito) {
		this.dataEsito = dataEsito;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public StatoConto getStatoConto() {
		return statoConto;
	}
	public void setStatoConto(StatoConto statoConto) {
		this.statoConto = statoConto;
	}
	public Conto(String iban, int saldo, Date dataApertura, Date dataEsito, Utente utente, StatoConto statoConto) {
		super();
		this.iban = iban;
		this.saldo = saldo;
		this.dataApertura = dataApertura;
		this.dataEsito = dataEsito;
		this.utente = utente;
		this.statoConto = statoConto;
	}
	public Conto() {
		super();
	}
	@Override
	public String toString() {
		return "Conto [id=" + id + ", iban=" + iban + ", saldo=" + saldo + ", dataApertura=" + dataApertura
				+ ", dataEsito=" + dataEsito + ", utente=" + utente + ", statoConto=" + statoConto + "]";
	}
}
