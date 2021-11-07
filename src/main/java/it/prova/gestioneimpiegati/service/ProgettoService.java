package it.prova.gestioneimpiegati.service;

import java.util.List;

import it.prova.gestioneimpiegati.model.Progetto;

public interface ProgettoService {
	
	public List<Progetto> listAllProgetti();
	
	public Progetto caricaSingoloProgetto(Long id);
	
	public void aggiorna(Progetto progettoInstance);
	
	public void inserisciNuovo (Progetto progettoInstance);
	
	public void rimuovi (Progetto progettoInstance);
	
	public Progetto progettoAPiuImpiegati(Long id);
	
	public List<Progetto> cercaImpiegatoConRal(int input);
	
	public List<Progetto> caricaClientiDataSocieta(Long id);

}
