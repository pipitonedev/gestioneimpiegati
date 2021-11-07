package it.prova.gestioneimpiegati.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestioneimpiegati.model.Progetto;

public interface ProgettoRepository extends CrudRepository<Progetto, Long>, QueryByExampleExecutor<Progetto> {

	List<Progetto> findByImpiegati_Ral(int input);

	@EntityGraph(attributePaths = { "impiegati" })
	Optional<Progetto> findById(Long id);
	
	@Query("select distinct p from Societa s join s.impiegati i join i.progetti p where s.id = ?1")
	List<Progetto> listaClientiSocieta(Long id);

}
