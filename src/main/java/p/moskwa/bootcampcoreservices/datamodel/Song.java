package p.moskwa.bootcampcoreservices.datamodel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent valid song
 *
 * @since 1.0
 */
public class Song {
    /**
     * Constant String used to generate song unique id
     */
    public static final String UID_SPLITTER = "<!splitter!>";

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

    /**
     * Creates name: value String
     *
     * @param song song to extract details
     * @return field name in Polish with colon separated value as collection of {@link String}
     */
    public List<String> getSongDetailsInPolish(Song song) {
        List<String> songDetails = new ArrayList<>();

        for (Field field : Song.class.getDeclaredFields()) {
            String polishFieldName = SongDAO.translateFieldNameToPolish(field.getName());
            if (polishFieldName != null) {
                try {
                    polishFieldName += ": ";
                    if (field.getName().equals("category")) {
                        songDetails.add(polishFieldName + song.getCategory().getCategory());
                    } else {
                        songDetails.add(polishFieldName + field.get(song));
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }
        return songDetails;
    }
}
