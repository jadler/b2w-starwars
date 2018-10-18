package br.com.jadler.models;

import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;

/**
 *
 * @since 1.0
 * @version 1.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public class Planets extends Model {

    @ApiModelProperty(notes = "The name of the planet.", required = true)
    private String name;

    @ApiModelProperty(notes = "The planet's terrains type.", required = false)
    private Collection<Terrain> terrains;

    @ApiModelProperty(notes = "The planet's climates type.", required = false)
    private Collection<Climate> climates;

    @ApiModelProperty(notes = "Amount of movies this planet appears on.", required = false)
    private Integer movies;

    public Planets() {
    }

    public Planets(String name) {
        this(null, name, null, null, 0);
    }

    public Planets(String name, Integer movies) {
        this(null, name, null, null, movies);
    }

    public Planets(String name, Collection<Climate> climates, Collection<Terrain> terrains) {
        this(null, name, climates, terrains, 0);
    }

    public Planets(String name, Collection<Climate> climates, Collection<Terrain> terrains, Integer movies) {
        this(null, name, climates, terrains, movies);
    }

    public Planets(String id, String name, Collection<Climate> climates, Collection<Terrain> terrains) {
        this(null, name, climates, terrains, 0);
    }

    public Planets(String id, String name, Collection<Climate> climates, Collection<Terrain> terrains, Integer movies) {
        this.id = id;
        this.name = name;
        this.terrains = terrains;
        this.climates = climates;
        this.movies = movies;
    }

    public Collection<Terrain> getTerrains() {
        return terrains;
    }

    public void setTerrains(Collection<Terrain> collection) {
        terrains = collection;
    }

    public Collection<Climate> getClimates() {
        return climates;
    }

    public void setClimates(Collection<Climate> collection) {
        climates = collection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMovies() {
        return movies;
    }

    public void setMovies(Integer movies) {
        this.movies = movies;
    }

}
