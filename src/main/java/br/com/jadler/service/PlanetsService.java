/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jadler.service;

import br.com.jadler.Visitor;
import br.com.jadler.models.Planets;
import br.com.jadler.models.PlanetsVisitor;
import br.com.jadler.repository.PlanetsRepository;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @since 2.0
 * @version 2.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
@Service
public class PlanetsService {

    private static final String CACHE_NAME = "planets";

    @Autowired
    protected PlanetsRepository repository;

    protected final Visitor visitor = new PlanetsVisitor();

    public Iterable<Planets> findAll() {
        Iterable<Planets> planets = repository.findAll();
        planets.forEach(visitor::visit);
        return planets;
    }

    /*
     * FIXME - Se o repositório estiver com anotação 'transactional' e der erro
     * durante a transação e ocorrer rollback o cache já terá sido atualizado e
     * isso causará inconsistência.
     */
    @CachePut(cacheNames = CACHE_NAME, key = "#planet.id")
    public Planets save(Planets planet) {
        visitor.visit(planet);
        return repository.save(planet);
    }

    @CacheEvict(cacheNames = CACHE_NAME, key = "#id")
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "#id", sync = true, condition = "#id != null")
    public Optional<Planets> findById(String id) {
        Optional<Planets> planet = repository.findById(id);
        planet.ifPresent(visitor::visit);
        return planet;
    }

    @Cacheable(cacheNames = CACHE_NAME,
            key = "{#planet.probe.id, #planet.probe.name}",
            sync = true,
            condition = "#planet != null")
    public Optional<Planets> findOne(Example<Planets> planet) {
        Optional<Planets> optional = repository.findOne(planet);
        optional.ifPresent(visitor::visit);
        return optional;
    }

    @Scheduled(fixedRate = 600000)
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    public void clearCache() {
        LoggerFactory.getLogger(this.getClass()).info("Cleaning '{}' cache", CACHE_NAME);
    }
}
