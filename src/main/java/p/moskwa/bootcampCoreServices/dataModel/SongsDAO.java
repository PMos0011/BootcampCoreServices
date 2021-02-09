package p.moskwa.bootcampCoreServices.dataModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "songs")
public class SongsDAO {
    @JacksonXmlProperty(localName = "song")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<SongDAO> songDAOS;

    public List<SongDAO> getSongs() {
        return songDAOS;
    }
}