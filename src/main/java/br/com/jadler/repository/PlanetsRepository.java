package br.com.jadler.repository;

import br.com.jadler.models.Planets;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * @since 2.0
 * @version 2.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
@Repository
public interface PlanetsRepository extends PagingAndSortingRepository<Planets, String>, QueryByExampleExecutor<Planets> {
}
