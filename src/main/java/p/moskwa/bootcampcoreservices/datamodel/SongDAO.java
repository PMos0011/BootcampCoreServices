package p.moskwa.bootcampcoreservices.datamodel;

/**
 * Represent unverified song
 *
 * @since 1.0
 */
public class SongDAO {
    public String title;
    public String author;
    public String album;
    public String category;
    public String votes;

    /**
     * Null values constructor
     */
    public SongDAO() {
    }

    /**
     * All arguments constructor
     */
    public SongDAO(String title, String author, String album, String category, String votes) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", album='" + album + '\'' +
                ", category='" + category + '\'' +
                ", votes='" + votes + '\'' +
                '}';
    }

    /**
     * Translates class filed name to polish
     *
     * @param fieldName field name as {@link String}
     * @return translated field name
     */
    public static String translateFieldNameToPolish(String fieldName) {
        try {
            return PolishFieldNames.valueOf(fieldName.toUpperCase()).getFieldName();
        } catch (IllegalArgumentException ignored) {
        }
        return null;
    }

    /**
     * Polish field names
     */
    private enum PolishFieldNames {
        TITLE("Tytuł"),
        AUTHOR("Wykonawca"),
        ALBUM("Album"),
        CATEGORY("Gatunek"),
        VOTES("Głosy");

        private final String polishFieldName;

        PolishFieldNames(final String polishFieldName) {
            this.polishFieldName = polishFieldName;
        }

        /**
         * @return the enum value
         */
        public String getFieldName() {
            return polishFieldName;
        }
    }
}
