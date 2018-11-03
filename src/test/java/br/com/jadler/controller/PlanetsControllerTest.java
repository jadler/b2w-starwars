package br.com.jadler.controller;

import br.com.jadler.models.Climate;
import static br.com.jadler.models.Climate.*;
import br.com.jadler.models.Planets;
import static br.com.jadler.models.Terrain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import org.slf4j.Logger;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

        Planets naboo = new Planets("Naboo",
                EnumSet.of(TEMPERATE),
                EnumSet.of(GRASSY_HILLS, SWAMP, FORESTS, MOUNTAINS));
        naboo.setId(ID);

        planets.add(naboo);

        planets.add(new Planets("Dagobah",
                EnumSet.of(MURKY),
                EnumSet.of(SWAMP, JUNGLES)));

        planets.add(new Planets("Alderaan",
                EnumSet.of(TEMPERATE),
                EnumSet.of(GRASSLANDS, MOUNTAINS)));

    }

    @Before
    public void method() {
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
                        .andExpect(status().isOk());
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

        Planets hoth = new Planets("Hoth",
                EnumSet.of(FROZEN),
                EnumSet.of(TUNDRA, ICE_CAVES, MOUNTAIN_RANGES));

        mock.perform(put("/planets/id/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jackson.write(hoth).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.name", is("Hoth")));
    }

    @Test
    public void test_5_Delete() throws Exception {
        mock.perform(delete("/planets/id/" + ID))
                .andDo(print())
                .andExpect(status().isOk());

        mock.perform(get("/planets/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void test_6_Movies() throws Exception {

        Planets corellia = new Planets("Corellia",
                EnumSet.of(TEMPERATE),
                EnumSet.of(PLAINS, URBAN, HILLS, FORESTS)
        );

        mock.perform(post("/planets/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jackson.write(corellia).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies", is(0)));

        Planets tatooine = new Planets("Tatooine",
                EnumSet.of(Climate.ARID),
                EnumSet.of(DESERTS)
        );

        mock.perform(post("/planets/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jackson.write(tatooine).getJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies", is(5)));
    }
}
