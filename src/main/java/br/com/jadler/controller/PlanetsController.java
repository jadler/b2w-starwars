package br.com.jadler.controller;

import br.com.jadler.models.Planets;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @since 1.0
 * @version 1.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
@RestController
@RequestMapping("/planets")
@ApiOperation("Operation belonging to the planets")
public class PlanetsController extends Controller<Planets> {

    @ApiOperation("Retrieve a planet given its name")
    @GetMapping(value = {"/name/{name}"}, produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public Planets getByName(@PathVariable("name") String name) {
        Planets p = new Planets();
        p.setName(name);

        GenericPropertyMatcher property;
        property = GenericPropertyMatcher.of(StringMatcher.EXACT);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("name", property);
        
        return repository.findOne(Example.of(p, matcher)).get();

    }

}
