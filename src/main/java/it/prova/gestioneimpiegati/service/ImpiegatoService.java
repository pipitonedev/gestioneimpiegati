package it.prova.gestioneimpiegati.service;

import java.util.List;

import it.prova.gestioneimpiegati.model.Impiegato;

public interface ImpiegatoService {
	
	public List<Impiegato> listAllImpiegati();
	
	public Impiegato caricaSingoloImpiegato(Long id);
	
	public void aggiorna(Impiegato impiegatoInstance);
	
	public void inserisciNuovo(Impiegato impiegatoInstance);
	
	public void rimuovi(Impiegato impiegatoInstance);
	
	public Impiegato impiegatoAPiuProgetti(Long id);
	
	public List<Impiegato> trovaImpiegatoPiuVecchiDiSocietaFondataPrimaDel();

}
