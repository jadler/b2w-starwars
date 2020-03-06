package br.com.jadler.controller;

import static br.com.jadler.climate.Climate.ARID;
import static br.com.jadler.climate.Climate.FROZEN;
import static br.com.jadler.climate.Climate.MURKY;
import static br.com.jadler.climate.Climate.TEMPERATE;
import br.com.jadler.models.Planets;
import br.com.jadler.models.PlanetsBuilder;
import static br.com.jadler.terrain.Terrain.DESERTS;
import static br.com.jadler.terrain.Terrain.FORESTS;
import static br.com.jadler.terrain.Terrain.GRASSLANDS;
import static br.com.jadler.terrain.Terrain.GRASSY_HILLS;
import static br.com.jadler.terrain.Terrain.HILLS;
import static br.com.jadler.terrain.Terrain.ICE_CAVES;
import static br.com.jadler.terrain.Terrain.JUNGLES;
import static br.com.jadler.terrain.Terrain.MOUNTAINS;
import static br.com.jadler.terrain.Terrain.MOUNTAIN_RANGES;
import static br.com.jadler.terrain.Terrain.PLAINS;
import static br.com.jadler.terrain.Terrain.SWAMP;
import static br.com.jadler.terrain.Terrain.TUNDRA;
import static br.com.jadler.terrain.Terrain.URBAN;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanetsControllerTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mock;

    private JacksonTester<Planets> jackson;

    private static Collection<Planets> planets;

    private static final String ID = "5bc6e991cfb2da450627f8b7";

    @BeforeClass
    public static void setUpClass() {

        planets = new ArrayList();

        planets.add(new PlanetsBuilder("Naboo")
                .id(ID)
                .climates(TEMPERATE)
                .terrains(GRASSY_HILLS, SWAMP, FORESTS, MOUNTAINS)
                .build());

        planets.add(new PlanetsBuilder("Dagobah")
                .climates(MURKY)
                .terrains(SWAMP, JUNGLES)
                .build());

        planets.add(new PlanetsBuilder("Alderaan")
                .climates(TEMPERATE)
                .terrains(GRASSLANDS, MOUNTAINS)
                .build());
    }

    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void test_1_CreatePlanets() {

        planets.stream().forEach(p -> {
            try {
                mock.perform(post("/planets/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jackson.write(p).getJson()))
                        .andDo(print())
                        .andExpect(status().isCreated());
            } catch (Exception ex) {
                log.error(ex.getLocalizedMessage());
            }
        });
    }

    @Test
    public void test_2_ListPlanets() throws Exception {

        mock.perform(get("/planets/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void test_3_GetByName() throws Exception {

        mock.perform(get("/planets/name/Naboo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.name", is("Naboo")));
    }

    @Test
    public void test_4_ModifyById() throws Exception {

        Planets hoth = new PlanetsBuilder("Hoth")
                .climates(FROZEN)
                .terrains(TUNDRA, ICE_CAVES, MOUNTAIN_RANGES)
                .build();

        mock.perform(put("/planets/id/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jackson.write(hoth).getJson()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_5_Delete() throws Exception {
        mock.perform(delete("/planets/id/" + ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        mock.perform(get("/planets/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void test_6_Movies() throws Exception {

        Planets corellia = new PlanetsBuilder("Corellia")
                .climates(TEMPERATE)
                .terrains(PLAINS, URBAN, HILLS, FORESTS)
                .build();

        mock.perform(post("/planets/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jackson.write(corellia).getJson()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.movies", is(0)));

        Planets tatooine = new PlanetsBuilder("Tatooine")
                .climates(ARID)
                .terrains(DESERTS)
                .build();

        mock.perform(post("/planets/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jackson.write(tatooine).getJson()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.movies", is(5)));
    }
}
