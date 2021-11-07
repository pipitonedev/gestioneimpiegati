package it.prova.gestioneimpiegati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneimpiegati.model.Progetto;
import it.prova.gestioneimpiegati.repository.ProgettoRepository;

@Service
public class ProgettoServiceImpl implements ProgettoService {
	
	@Autowired
	private ProgettoRepository progettoRepository;

	@Transactional(readOnly = true)
	public List<Progetto> listAllProgetti() {
		return (List<Progetto>) progettoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Progetto caricaSingoloProgetto(Long id) {
		return progettoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Progetto progettoInstance) {
		progettoRepository.save(progettoInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Progetto progettoInstance) {
		progettoRepository.save(progettoInstance);
		
	}

	@Transactional
	public void rimuovi(Progetto progettoInstance) {
		progettoRepository.delete(progettoInstance);
		
	}

	@Transactional(readOnly = true)
	public List<Progetto> cercaImpiegatoConRal(int input) {
		return progettoRepository.findByImpiegati_Ral(input);
	}

	@Transactional(readOnly = true)
	public Progetto progettoAPiuImpiegati(Long id) {
		return progettoRepository.findById(id).get();
	}

	@Override
	public List<Progetto> caricaClientiDataSocieta(Long id) {
		return progettoRepository.listaClientiSocieta(id);
	}

}
