package p.moskwa.bootcampCoreServices.dataModel;

/**
 * Represent unverified song
 */
public class SongDAO {
    public String title;
    public String author;
    public String album;
    public String category;
    public String votes;

    public SongDAO() {
    }

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
}