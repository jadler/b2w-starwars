package br.com.jadler.climate;

/**
 *
 * @since 1.0
 * @version 1.2
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public enum Climate {

    ARID("Arid"),
    FROZEN("Frozen"),
    MURKY("Murky"),
    TEMPERATE("Temperate"),
    TROPICAL("Tropical");

    private final String name;

    private Climate(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
