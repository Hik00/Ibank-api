package it.exolab.bank.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transazione {
	private Integer id;
	private int importo;
	private Date data;
	private Date dataEsito;
	Utente utente;
	Conto conto;
	InfBonifico infBonifico;
	StatoTransazione statoTransazione;
	TipoTransazione tipoTransazione;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getImporto() {
		return importo;
	}
	public void setImporto(int importo) {
		this.importo = importo;
	}
	public String getData() {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			return sdf.format(data);
		}
		return null; // o un valore di fallback, a seconda delle tue esigenze
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDataEsito() {
		if (dataEsito != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			return sdf.format(dataEsito);
		}
		return null; // o un valore di fallback, a seconda delle tue esigenze
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
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		this.conto = conto;
	}
	public InfBonifico getInfBonifico() {
		return infBonifico;
	}
	public void setInfBonifico(InfBonifico infBonifico) {
		this.infBonifico = infBonifico;
	}
	public StatoTransazione getStatoTransazione() {
		return statoTransazione;
	}
	public void setStatoTransazione(StatoTransazione statoTransazione) {
		this.statoTransazione = statoTransazione;
	}
	public TipoTransazione getTipoTransazione() {
		return tipoTransazione;
	}

	public void setTipoTransazione(TipoTransazione tipoTransazione) {
		this.tipoTransazione = tipoTransazione;
	}

	public Transazione(int importo, Date data, Date dataEsito, Utente utente, Conto conto, InfBonifico infBonifico,
			StatoTransazione statoTransazione, TipoTransazione tipoTransazione) {
		super();
		this.importo = importo;
		this.data = data;
		this.dataEsito = dataEsito;
		this.utente = utente;
		this.conto = conto;
		this.infBonifico = infBonifico;
		this.statoTransazione = statoTransazione;
		this.tipoTransazione = tipoTransazione;
	}
	public Transazione() {
		super();
	}
	@Override
	public String toString() {
		return "Transazione [id=" + id + ", importo=" + importo + ", data=" + data + ", dataEsito=" + dataEsito
				+ ", utente=" + utente + ", conto=" + conto + ", infBonifico=" + infBonifico + ", statoTransazione="
				+ statoTransazione + ", tipoTransazione=" + tipoTransazione + "]";
	}






}
