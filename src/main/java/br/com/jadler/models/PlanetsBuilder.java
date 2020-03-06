/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jadler.models;

import br.com.jadler.Visitor;
import br.com.jadler.climate.Climate;
import br.com.jadler.terrain.Terrain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @since 2.0
 * @version 2.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public class PlanetsBuilder {

    protected String id;

    protected final String name;

    protected Collection<Terrain> terrains;

    protected Collection<Climate> climates;

    private boolean request;

    private final Visitor visitor;

    public PlanetsBuilder(final String name) {
        this.visitor = new PlanetsVisitor();
        this.name = name;
    }

    public PlanetsBuilder id(final String id) {
        this.id = id;
        return this;
    }

    public PlanetsBuilder terrains(Collection<Terrain> terrains) {
        this.terrains = terrains;
        return this;
    }

    public PlanetsBuilder terrains(Terrain... terrain) {
        return this.terrains(Arrays.asList(terrain));
    }

    public PlanetsBuilder climates(Collection<Climate> climates) {
        this.climates = climates;
        return this;
    }

    public PlanetsBuilder climates(Climate... climates) {
        return this.climates(Arrays.asList(climates));
    }

    public PlanetsBuilder requestMovies() {
        this.requestMovies(true);
        return this;
    }

    public PlanetsBuilder requestMovies(boolean request) {
        this.request = request;
        return this;
    }

    public Planets build() {

        Planets planet = new Planets();

        planet.setId(id);
        planet.setName(name);

        if (climates == null) {
            climates = new ArrayList<>();
        }
        planet.setClimates(climates);

        if (terrains == null) {
            terrains = new ArrayList<>();
        }
        planet.setTerrains(terrains);

        if (request) {
            visitor.visit(planet);
        }

        return planet;
    }

}
