package it.prova.gestioneimpiegati.service;

import java.util.List;

import it.prova.gestioneimpiegati.model.Societa;

public interface SocietaService {
	
	public List<Societa> listAllSocieta();
	
	public Societa caricaSingolaSocieta(Long id);
	
	public void aggiorna(Societa societaInstance);
	
	public void inserisciNuovo(Societa societaInstance);
	
	public void rimuovi(Societa societaInstance);
	
	public List<Societa> findByExample(Societa example);
	
	public List<Societa> trovaNomeSocialeConProgettiConDurataMaggiorediUnAnno();

}
