package br.com.jadler.models;

import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @since 1.0
 * @version 1.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public enum Terrain {

    ARID,
    GRASSLANDS,
    MOUNTAINS,
    JUNGLES,
    RAINFORESTS,
    TUNDRA,
    ICE_CAVES,
    MOUNTAIN_RANGES,
    SWAMP,
    GAS_GIANT,
    FORESTS,
    LAKE,
    GRASSY_HILLS,
    CITYSCAPES,
    OCEAN,
    ROCK,
    DESERTS,
    BARREN;

    public static Terrain fromValue(String value) {
        Optional<Terrain> terrain = Arrays.asList(values()).stream()
                .filter(v -> v.toString().equals(value.toLowerCase()))
                .findFirst();

        return terrain.orElseThrow(() -> {
            return new IllegalArgumentException();
        });
    }

    @Override
    public String toString() {
        return this.name().toLowerCase().replaceAll("_", " ");
    }

}
