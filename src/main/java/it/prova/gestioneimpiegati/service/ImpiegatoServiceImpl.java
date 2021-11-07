package it.prova.gestioneimpiegati.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneimpiegati.model.Impiegato;
import it.prova.gestioneimpiegati.repository.ImpiegatoRepository;

@Service
public class ImpiegatoServiceImpl implements ImpiegatoService {
	
	@Autowired
	private ImpiegatoRepository impiegatoRepository;

	@Transactional(readOnly = true)
	public List<Impiegato> listAllImpiegati() {
		return (List<Impiegato>) impiegatoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Impiegato caricaSingoloImpiegato(Long id) {
		return impiegatoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Impiegato impiegatoInstance) {
		impiegatoRepository.save(impiegatoInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Impiegato impiegatoInstance) {
		impiegatoRepository.save(impiegatoInstance);
		
	}

	@Transactional
	public void rimuovi(Impiegato impiegatoInstance) {
		impiegatoRepository.delete(impiegatoInstance);
		
	}

	@Transactional(readOnly = true)
	public List<Impiegato> trovaImpiegatoPiuVecchiDiSocietaFondataPrimaDel() {
		return impiegatoRepository.findAllImpiegatoPiuAnziano();
	}

	@Transactional(readOnly = true)
	public Impiegato impiegatoAPiuProgetti(Long id) {
		return impiegatoRepository.findById(id).get();
	}

}
