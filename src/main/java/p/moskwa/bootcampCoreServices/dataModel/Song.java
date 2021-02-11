package p.moskwa.bootcampCoreServices.dataModel;

/**
 * Represent valid song
 *
 * @since 1.0
 */
public class Song {
    /**
     * Constant String used to generate song unique id
     */
    public final static String UID_SPLITTER = "<!splitter!>";

    private String title;
    private String author;
    private String album;
    private Categories category;
    private Integer votes;
    private String uid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return author + " - " + title;
    }
}