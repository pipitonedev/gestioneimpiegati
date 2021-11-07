package it.prova.gestioneimpiegati.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "impiegato")
public class Impiegato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "dataAssunzione")
	private Date dataAssunzione;
	@Column(name = "ral")
	private Integer ral;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_societa", nullable = false)
	private Societa societa;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "impiegato_progetto", joinColumns = @JoinColumn(name = "impiegato_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "progetto_id", referencedColumnName = "ID"))
	private Set<Progetto> progetti = new HashSet<>();

	public Impiegato() {
	}

	public Impiegato(String nome, String cognome, Date dataAssunzione, Integer ral) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataAssunzione = dataAssunzione;
		this.ral = ral;
	}

	public Impiegato(String nome, String cognome, Date dataAssunzione, Integer ral, Societa societa) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataAssunzione = dataAssunzione;
		this.ral = ral;
		this.societa = societa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Integer getRal() {
		return ral;
	}

	public void setRal(Integer ral) {
		this.ral = ral;
	}

	public Societa getSocieta() {
		return societa;
	}

	public void setSocieta(Societa societa) {
		this.societa = societa;
	}

	public Set<Progetto> getProgetti() {
		return progetti;
	}

	public void setProgetti(Set<Progetto> progetti) {
		this.progetti = progetti;
	}

	public void removeFromProgetti(Progetto progetto) {
		this.progetti.remove(progetto);
		progetto.getImpiegati().remove(this);
	}

	public void addToProgetti(Progetto progetto) {
		this.progetti.add(progetto);
		progetto.getImpiegati().add(this);
	}

	@Override
	public String toString() {
		return "Impiegato [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataAssunzione=" + dataAssunzione
				+ ", ral=" + ral + ", societa=" + societa + ", progetti=" + progetti + "]";
	}

}
