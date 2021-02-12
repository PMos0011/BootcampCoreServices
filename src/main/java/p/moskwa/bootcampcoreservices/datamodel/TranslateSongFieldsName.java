package p.moskwa.bootcampcoreservices.datamodel;

/**
 * Translates {@link Song} fields name
 *
 * @see Song
 * @since 1.0
 */
public class TranslateSongFieldsName {

    /**
     * Translates Song.class fields name to polish
     *
     * @param fieldName field name from {@link Song}
     * @return polish name of Song.class fields as {@link String}
     * @see Song
     */
    public static String translateFieldNameToPl(String fieldName) {
        switch (fieldName) {
            case "title":
                return "Tytuł";
            case "author":
                return "Wykonawca";
            case "album":
                return "Album";
            case "category":
                return "Gatunek";
            case "votes":
                return "Głosy";
            default:
                return null;
        }
    }
}
