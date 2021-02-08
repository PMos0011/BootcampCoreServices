package p.moskwa.bootcampCoreServices.dataModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"title", "author", "album", "category", "votes"})
public class SongDAO {
    public String title;
    public String author;
    public String album;
    public String category;
    public String votes;

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