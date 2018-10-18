package br.com.jadler.controller;

import br.com.jadler.models.Model;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @param <T>
 * @since 1.0
 * @version 1.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public abstract class Controller<T extends Model> {

    @Autowired
    protected MongoRepository<T, String> repository;

    @ApiOperation("Retrieve all elements")
    @GetMapping({"", "/"})
    public Collection<T> list() {
        return repository.findAll();
    }

    @ApiOperation("Insert a new element")
    @PostMapping({"/"})
    public T create(@Valid @RequestBody T t) {
        return repository.save(t);
    }

    @ApiOperation("Retrieve an element given its id")
    @GetMapping({"/id/{id}"})
    public T getById(@PathVariable("id") String id) {
        return repository.findById(id).get();
    }

    @ApiOperation("Update an element given its id")
    @PutMapping({"/id/{id}"})
    public T modifyById(@PathVariable("id") String id, @Valid @RequestBody T t) {
        t.setId(id);
        return repository.save(t);
    }

    @ApiOperation("Delete an element given its id")
    @DeleteMapping({"/id/{id}"})
    public void delete(@PathVariable String id) {
        repository.delete(repository.findById(id).get());
    }
}
