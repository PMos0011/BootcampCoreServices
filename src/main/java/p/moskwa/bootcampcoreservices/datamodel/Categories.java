package p.moskwa.bootcampcoreservices.datamodel;

/**
 * Supported music genres
 */
public enum Categories {
    ALTERNATIVE("Alternative"),
    AMBIENT("Ambient"),
    BLUES("Blues"),
    CLASSICAL("Classical"),
    COUNTRY("Country"),
    DANCE("Dance"),
    DOWNTEMPO("Downtempo"),
    ELECTRONIC("Electronic"),
    FUNK("Funk"),
    INDUSTRIAL("Industrial"),
    JAZZ("Jazz"),
    METAL("Metal"),
    NEW_AGE("New age"),
    POP("Pop"),
    PROGRESSIVE("Progressive"),
    RAP("Rap"),
    REGGAE("Reggae"),
    ROCK("Rock"),
    TRANCE("Trance"),
    VOCAL("Vocal");

    private final String category;

    Categories(final String category) {
        this.category = category;
    }

    /**
     * @return the enum value
     */
    public String getCategory() {
        return category;
    }
}
