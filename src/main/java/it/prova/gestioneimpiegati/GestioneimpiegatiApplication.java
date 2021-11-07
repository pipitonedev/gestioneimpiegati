package it.prova.gestioneimpiegati;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestioneimpiegati.service.BatteriaDiTestService;

@SpringBootApplication
public class GestioneimpiegatiApplication implements CommandLineRunner {

	@Autowired
	private BatteriaDiTestService batteriaDiTestService;

	public static void main(String[] args) {
		SpringApplication.run(GestioneimpiegatiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("################ START   #################");
		System.out.println("################ eseguo i test  #################");

		batteriaDiTestService.testInserimentoSocieta();
		batteriaDiTestService.testFindByExampleSocieta();
		batteriaDiTestService.testRimuoviSocieta();
		batteriaDiTestService.testInserimentoImpiegato();
		batteriaDiTestService.testInserimentoProgetto();
		batteriaDiTestService.testCollegamentoImpiegatoAProgetti();
		batteriaDiTestService.testCollegaProgettoAImpiegati();
		batteriaDiTestService.testListaClientiProgettiDiUnaDeterminataSocieta();
		batteriaDiTestService.testCercaProgettiConPiuDiUnAnno();
		batteriaDiTestService.testCercaProgettoConImpiegatoConRalUgualeA();
		batteriaDiTestService.testCercaImpiegatoAnzianoDiUnaSocietaCheLavoraAProgettoDa();

		System.out.println("################ FINE   #################");

	}

}
