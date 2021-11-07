package it.prova.gestioneimpiegati.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestioneimpiegati.model.Impiegato;
import it.prova.gestioneimpiegati.model.Progetto;
import it.prova.gestioneimpiegati.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;

	@Autowired
	private ImpiegatoService impiegatoService;

	@Autowired
	private ProgettoService progettoService;

	// TEST INSERISCI SOCIETA

	public void testInserimentoSocieta() {

		System.out.println("testInserimentoSocieta........Inizio");
		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = new Societa("Facebook inc." + nowInMillisecondi, new Date("2004/02/04"));

		if (nuovaSocieta.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("testInserisciNuovaSocieta........OK");
	}

	// TEST FIND BY EXAMPLE SOCIETA

	public void testFindByExampleSocieta() {

		System.out.println("testFindByExampleSocieta........Inizio");

		Societa societa = new Societa("mcDonalds", new Date("1960/04/28"));
		Societa societa2 = new Societa("Oracle", new Date("1990/08/17"));

		societaService.inserisciNuovo(societa);
		societaService.inserisciNuovo(societa2);

		if (societa.getId() == null || societa2.getId() == null)
			throw new RuntimeException("testFindByExampleSocieta...failed: inserimento fallito");

		Societa example = new Societa("mcDonalds", null);

		if (societaService.findByExample(example).size() != 1)
			throw new RuntimeException("testFindByExampleSocieta failed: risultato inatteso");

		System.out.println("testFindByExampleSocieta........OK");
	}
	// TEST RIMUOVI SOCIETA INSERITA

	public void testRimuoviSocieta() {

		System.out.println("testRimuoviSocieta........Inizio");

		Societa societa = new Societa("Google", new Date("1998/10/11"));

		if (societa.getId() != null)
			throw new RuntimeException("testRimuoviSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);

		if (societa.getId() == null || societa.getId() < 1)
			throw new RuntimeException("testRimuoviSocieta...failed: inserimento fallito");

		societaService.rimuovi(societa);

		if (societaService.listAllSocieta().isEmpty()) {
			System.out.println("testRimuoviSocieta........OK");
		}

	}

	// TEST INSERIMENTO IMPIEGATO CON SOCIETA

	public void testInserimentoImpiegato() {

		System.out.println("testInserimentoImpiegato........Inizio");

		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta1 = new Societa("Blizzard" + nowInMillisecondi, new Date("2000/20/03"));
		societaService.inserisciNuovo(nuovaSocieta1);

		Impiegato nuovoImpiegato = new Impiegato("Vincenzo", "Pipitone", new Date("2015/13/10"), 20000);
		nuovoImpiegato.setSocieta(nuovaSocieta1);
		impiegatoService.inserisciNuovo(nuovoImpiegato);

		if (nuovaSocieta1.getId() == null || nuovoImpiegato.getId() == null)
			throw new RuntimeException("testInserimentoImpiegato...failed: inserimento fallito");

		System.out.println("testInserimentoImpiegato........OK");

	}

	// TEST INSERIMENTO PROGETTO

	public void testInserimentoProgetto() {

		System.out.println("testInserimentoProgetto........Inizio");

		Long nowInMillisecondi = new Date().getTime();

		Progetto nuovoProgetto = new Progetto("Sistemazione Server" + nowInMillisecondi,
				"Microsoft" + nowInMillisecondi, 3);

		if (nuovoProgetto.getId() != null)
			throw new RuntimeException("testInserimentoProgetto...failed: transient object con id valorizzato");

		progettoService.inserisciNuovo(nuovoProgetto);
		if (nuovoProgetto.getId() == null || nuovoProgetto.getId() < 1)
			throw new RuntimeException("testInserimentoProgetto...failed: inserimento fallito");

		System.out.println("testInserimentoProgetto........OK");
	}

	// TEST COLLEGA IMPIEGATO A PIU' PROGETTI

	public void testCollegamentoImpiegatoAProgetti() {

		System.out.println("testCollegamentoImpiegatoAProgetti........Inizio");

		Societa societa = new Societa("Amazon", new Date("1998/10/13"));
		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testCollegamentoImpiegatoAProgetti...failed: inserimento fallito");

		Progetto progetto = new Progetto("AWS Cloud", "Amazon", 12);
		progettoService.inserisciNuovo(progetto);

		Progetto progetto2 = new Progetto("Kindle", "Amazon", 6);
		progettoService.inserisciNuovo(progetto2);

		Impiegato nuovoImpiegato = new Impiegato("Vincenzo", "Pipitone", new Date("2016/09/26"), 23000, societa);

		nuovoImpiegato.addToProgetti(progetto);
		nuovoImpiegato.addToProgetti(progetto2);
		impiegatoService.inserisciNuovo(nuovoImpiegato);

		Impiegato risultato = impiegatoService.impiegatoAPiuProgetti(nuovoImpiegato.getId());
		if (risultato.getProgetti().size() != 2)
			throw new RuntimeException("testCollegamentoImpiegatoAProgetti failed: risultato inatteso");

		System.out.println("testCollegamentoImpiegatoAProgetti........OK");
	}

	// TEST COLLEGA PROGETTO A PIU' IMPIEGATI

	public void testCollegaProgettoAImpiegati() {

		System.out.println("testCollegaProgettoAImpiegati........Inizio");

		Societa societa = new Societa("Twitter", new Date("2006/03/21"));
		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testCollegaProgettoAImpiegati...failed: inserimento fallito");

		Progetto nuovoProgetto = new Progetto("Fact Checking", "Twitter", 10);
		progettoService.inserisciNuovo(nuovoProgetto);

		Impiegato impiegato1 = new Impiegato("Jeff", "Bezos", new Date("2010/05/15"), 100000, societa);
		Impiegato impiegato2 = new Impiegato("Roman", "Zuckberg", new Date("2012/12/06"), 55000, societa);
		impiegato1.addToProgetti(nuovoProgetto);
		impiegato2.addToProgetti(nuovoProgetto);
		impiegatoService.inserisciNuovo(impiegato1);
		impiegatoService.inserisciNuovo(impiegato2);

		Progetto risultato = progettoService.progettoAPiuImpiegati(nuovoProgetto.getId());
		if (risultato.getImpiegati().size() != 2)
			throw new RuntimeException("testCollegaProgettoAImpiegati failed: risultato inatteso");

		System.out.println("testCollegaProgettoAImpiegati........OK");

	}

	// LISTA CLIENTI DEI PROGETTI DI UNA DETERMINATA SOCIETA'

	public void testListaClientiProgettiDiUnaDeterminataSocieta() {

		System.out.println("testListaClientiProgettiDiUnaDeterminataSocieta........Inizio");

		Societa societa = new Societa("Accenture spa", new Date("2001/11/30"));
		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testListaClientiProgettiDiUnaDeterminataSocieta...failed: inserimento fallito");

		Progetto progetto = new Progetto("Gestione Database", "Avenade", 8);
		Progetto progetto2 = new Progetto("Consulenza Informatica", "Coritel", 3);

		progettoService.inserisciNuovo(progetto);
		progettoService.inserisciNuovo(progetto2);

		Impiegato nuovoImpiegato = new Impiegato("Giovanni", "Allegri", new Date("2003/10/04"), 20000, societa);

		nuovoImpiegato.addToProgetti(progetto);
		nuovoImpiegato.addToProgetti(progetto2);
		impiegatoService.inserisciNuovo(nuovoImpiegato);

		List<Progetto> progetti = progettoService.caricaClientiDataSocieta(societa.getId());

		if (progetti.size() != 2)
			throw new RuntimeException("testListaClientiProgettiDiUnaDeterminataSocieta failed: risultato inatteso");

		System.out.println("testListaClientiProgettiDiUnaDeterminataSocieta........OK");
	}

	// TEST CERCA PROGETTO IL CUI IMPIEGATO ABBIA UN RAL DI 30.000

	public void testCercaProgettoConImpiegatoConRalUgualeA() {

		System.out.println("testCercaProgettoConImpiegatoConRalUgualeA........Inizio");

		Societa nuovaSocieta = new Societa("Netflix", new Date("2002/10/12"));
		societaService.inserisciNuovo(nuovaSocieta);

		Progetto nuovoProgetto = new Progetto("Sistemazione Server", "Netflix", 3);
		Progetto nuovoProgetto2 = new Progetto("Gestione Database", "Netflix", 7);
		Progetto nuovoProgetto3 = new Progetto("Marketing Social", "Netflix", 12);

		progettoService.inserisciNuovo(nuovoProgetto);
		progettoService.inserisciNuovo(nuovoProgetto2);
		progettoService.inserisciNuovo(nuovoProgetto3);

		Impiegato nuovoImpiegato = new Impiegato("Vincenzo", "Pipitone", new Date("2015/13/10"), 30000, nuovaSocieta);
		Impiegato nuovoImpiegato2 = new Impiegato("Giovanni", "Verdi", new Date("2015/13/10"), 20000, nuovaSocieta);
		Impiegato nuovoImpiegato3 = new Impiegato("Mario", "Rossi", new Date("2015/13/10"), 30000, nuovaSocieta);

		nuovoImpiegato.addToProgetti(nuovoProgetto);
		nuovoImpiegato2.addToProgetti(nuovoProgetto2);
		nuovoImpiegato3.addToProgetti(nuovoProgetto3);

		impiegatoService.inserisciNuovo(nuovoImpiegato);
		impiegatoService.inserisciNuovo(nuovoImpiegato2);
		impiegatoService.inserisciNuovo(nuovoImpiegato3);

		List<Progetto> progetti = progettoService.cercaImpiegatoConRal(30000);

		if (progetti.size() != 2)
			throw new RuntimeException("testCercaProgettoConImpiegatoConRalUgualeA failed: risultato inatteso");

		System.out.println("testCercaProgettoConImpiegatoConRalUgualeA........OK");

	}

	// LISTA DELLE SOCIETA' CHE ABBIANO DEI PROGETTI CON UNA DURATA MAGGIORE DI 12
	// MESI

	public void testCercaProgettiConPiuDiUnAnno() {

		System.out.println("testCercaProgettiConPiuDiUnAnno........Inizio");

		Societa societa = new Societa("Tesla", new Date("2003/07/01"));
		societaService.inserisciNuovo(societa);

		Societa societa2 = new Societa("Zara", new Date("1974/05/24"));
		societaService.inserisciNuovo(societa2);

		Progetto progetto = new Progetto("Social Media Management", "Zara", 5);
		Progetto progetto2 = new Progetto("Produzione Nuovi Modelli", "Tesla", 13);
		Progetto progetto3 = new Progetto("Distribuzione cappotti", "Zara", 14);

		progettoService.inserisciNuovo(progetto);
		progettoService.inserisciNuovo(progetto2);
		progettoService.inserisciNuovo(progetto3);

		Impiegato impiegato = new Impiegato("Vincenzo", "Pipitone", new Date("2005/07/10"), 40000, societa);
		Impiegato impiegato2 = new Impiegato("Valeria", "Barraco", new Date("2001/10/09"), 50000, societa2);
		Impiegato impiegato3 = new Impiegato("Giovanni", "Verdi", new Date("1999/02/10"), 25000, societa2);

		impiegato.addToProgetti(progetto2);
		impiegato2.addToProgetti(progetto);
		impiegato3.addToProgetti(progetto3);

		impiegatoService.inserisciNuovo(impiegato);
		impiegatoService.inserisciNuovo(impiegato2);
		impiegatoService.inserisciNuovo(impiegato3);

		List<Societa> risultato = societaService.trovaNomeSocialeConProgettiConDurataMaggiorediUnAnno();

		if (risultato.size() != 2)
			throw new RuntimeException("testCercaProgettiConPiuDiUnAnno failed: risultato inatteso");

		System.out.println("testCercaProgettiConPiuDiUnAnno........OK");
	}

	// L'IMPIEGATO PIU' ANZIANO DELLE SOCIETA' FONDATE PRIMA DEL 1990 E CHE LAVORA
	// SU UN PROGETTO ALMENO DA 6 MESI

	public void testCercaImpiegatoAnzianoDiUnaSocietaCheLavoraAProgettoDa() {

		System.out.println("testCercaImpiegatoAnzianoDiUnaSocietaCheLavoraAProgettoDa........Inizio");

		Societa societa = new Societa("Feltrinelli", new Date("1954/06/18"));
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("Ampliamento Catalogo", "Feltrinelli Scuola", 6);
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato = new Impiegato("Roberto", "Incontrada", new Date("1985/10/03"), 220000, societa);
		Impiegato impiegato2 = new Impiegato("Taylor", "Mega", new Date("2013/04/12"), 150000, societa);

		impiegato.addToProgetti(progetto);
		impiegato2.addToProgetti(progetto);

		impiegatoService.inserisciNuovo(impiegato);
		impiegatoService.inserisciNuovo(impiegato2);

		List<Impiegato> impiegatoAnziano = impiegatoService.trovaImpiegatoPiuVecchiDiSocietaFondataPrimaDel();

		if (impiegatoAnziano.size() != 3)
			throw new RuntimeException("testCercaProgettiConPiuDiUnAnno failed: risultato inatteso");

		System.out.println("testCercaImpiegatoAnzianoDiUnaSocietaCheLavoraAProgettoDa........OK");
	}
}
