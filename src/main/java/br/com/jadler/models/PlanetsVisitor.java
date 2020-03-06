/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jadler.models;

import br.com.jadler.Visitor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @since 2.0
 * @version 2.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public class PlanetsVisitor implements Visitor<Planets> {

    @Override
    public void visit(Planets planet) {
        setMovies(planet);
    }

    private void setMovies(Planets planet) throws JSONException {
        try {

            URL url = new URL("https://swapi.co/api/planets/?format=json&search=" + planet.getName());
            URLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String result = br.readLine();
            int movies = new JSONObject(result)
                    .getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONArray("films")
                    .length();

            planet.setMovies(movies);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
