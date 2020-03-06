package br.com.jadler.terrain;

/**
 *
 * @since 1.0
 * @version 2.0
 * @author <a href="mailto:jaguar.adler@gmail.com">Jaguaraquem A. Reinaldo</a>
 */
public enum Terrain {

    ARID("Arid"),
    BARREN("Barren"),
    CITYSCAPES("Cityscapes"),
    DESERTS("Deserts"),
    FORESTS("Forest"),
    GAS_GIANT("Gas Giant"),
    GRASSLANDS("Grasslands"),
    GRASSY_HILLS("Grassy Hills"),
    HILLS("Hills"),
    ICE_CAVES("Ice Caves"),
    JUNGLES("Jungles"),
    LAKE("Lake"),
    MOUNTAIN_RANGES("Mountain Ranges"),
    MOUNTAINS("Mountains"),
    OCEAN("Ocean"),
    PLAINS("Plains"),
    RAINFORESTS("Rainforest"),
    SWAMP("Swamp"),
    TUNDRA("Tundra"),
    URBAN("Urban"),
    ROCK("Rock");

    private final String name;

    private Terrain(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
