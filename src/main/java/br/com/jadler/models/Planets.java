package br.com.jadler.models;

import br.com.jadler.annotation.MappedProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import org.springframework.data.annotation.Id;
import br.com.jadler.annotation.GenerateRepository;
import br.com.jadler.annotation.GenerateController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

/**
 *
 * @since 1.0
 * @version 1.2
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
@GenerateRepository
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

    public Planets() {
    }

    public Planets(String name, Collection<Climate> climates, Collection<Terrain> terrains) {
        this.name = name;
        this.terrains = terrains;
        this.climates = climates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (movies == null && name != null && !name.isEmpty()) {
            try {

                URL url = new URL("https://swapi.co/api/planets/?format=json&search=" + name);
                URLConnection conn = (HttpURLConnection) url.openConnection();
                conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                conn.setUseCaches(false);
                conn.setAllowUserInteraction(false);

                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String result = br.readLine();
                movies = new JSONObject(result)
                        .getJSONArray("results")
                        .getJSONObject(0)
                        .getJSONArray("films")
                        .length();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                movies = null;
            }
        }

        return (movies == null) ? 0 : movies;
    }

    /**
     * This method does nothing and is used just to avoid compilation errors
     * since the amount of movies is returned from the https://swapi.co/api.
     *
     * @param movies
     */
    public void setMovies(Integer movies) {
        // does nothing
    }
}
