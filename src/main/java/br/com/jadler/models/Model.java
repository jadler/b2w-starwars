package br.com.jadler.models;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

/**
 *
 * @since 1.0
 * @version 1.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public abstract class Model {

    @Id
    @ApiModelProperty(notes = "Generated database element id.")
    protected String id;

    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

}
