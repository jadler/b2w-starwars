package br.com.jadler.models;

import br.com.jadler.annotation.GenerateController;
import br.com.jadler.annotation.MappedProperty;
import br.com.jadler.climate.Climate;
import br.com.jadler.terrain.Terrain;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import org.springframework.data.annotation.Id;

/**
 *
 * @since 1.0
 * @version 2.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
@GenerateController
public class Planets {

    @Id
    @MappedProperty
    @ApiModelProperty(notes = "Generated database planet id.")
    protected String id;

    @MappedProperty
    @ApiModelProperty(notes = "The name of the planet.", required = true)
    protected String name;

    @ApiModelProperty(notes = "The planet's terrains type.", required = false)
    protected Collection<Terrain> terrains;

    @ApiModelProperty(notes = "The planet's climates type.", required = false)
    protected Collection<Climate> climates;

    @ApiModelProperty(notes = "Amount of movies this planet appears on.", required = false)
    protected Integer movies;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Terrain> getTerrains() {
        return terrains;
    }

    public Collection<Climate> getClimates() {
        return climates;
    }

    public Integer getMovies() {
        return movies;
    }

    /**
     * For use with {@link PlanetsVisitor} and {@link PlanetsBuilder} only
     *
     * @param id
     */
    protected void setId(String id) {
        this.id = id;
    }

    /**
     * For use with {@link PlanetsVisitor} and {@link PlanetsBuilder} only
     *
     * @param name
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * For use with {@link PlanetsVisitor} and {@link PlanetsBuilder} only
     *
     * @param collection
     */
    protected void setTerrains(Collection<Terrain> collection) {
        terrains = collection;
    }

    /**
     * For use with {@link PlanetsVisitor} and {@link PlanetsBuilder} only
     *
     * @param collection
     */
    protected void setClimates(Collection<Climate> collection) {
        climates = collection;
    }

    /**
     * For use with {@link PlanetsVisitor} and {@link PlanetsBuilder} only
     *
     * @param movies
     */
    protected void setMovies(Integer movies) {
        this.movies = movies;
    }
}
