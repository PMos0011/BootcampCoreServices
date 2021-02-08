package p.moskwa.bootcampCoreServices.dataModel;

public enum Categories {

    ALTERNATIVE("Alternative"),
    AMBIENT("Ambient"),
    BLUES("Blues"),
    CLASSICAL("Classical"),
    COUNTRY("Country"),
    DANCE("Dance"),
    DOWNTEMPO("Downtempo"),
    ELECTRONIC("Electronic"),
    INDUSTRIAL("Industrial"),
    JAZZ("Jazz"),
    METAL("Metal"),
    NEW_AGE("New age"),
    POP("Pop"),
    PROGRESSIVE("Progressive"),
    RAP("Rap"),
    REGGAE("Reggae"),
    RNB("RnB"),
    ROCK("Rock"),
    TRANCE("Trance"),
    VOCAL("Vocal");

    private final String category;

    Categories(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}