package br.com.jadler.models;

import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @since 1.0
 * @version 1.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public enum Climate {

    TEMPERATE,
    TROPICAL,
    FROZEN,
    MURKY,
    ARID;

    public static Climate fromValue(String value) {
        Optional<Climate> climate = Arrays.asList(values()).stream()
                .filter(v -> v.toString().equals(value.toLowerCase()))
                .findFirst();

        return climate.orElseThrow(() -> {
            return new IllegalArgumentException();
        });
    }

    @Override
    public String toString() {
        return this.name().toLowerCase().replaceAll("_", " ");
    }

}
