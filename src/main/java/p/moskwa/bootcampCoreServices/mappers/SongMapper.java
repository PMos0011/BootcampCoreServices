package p.moskwa.bootcampCoreServices.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

/**
 * Mapper SongDAO.class to Song.class
 *
 * @see SongDAO
 * @see Song
 * @since 1.0
 */
@Mapper(imports = Categories.class)
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    /**
     * Converts SongDAO object to Song object
     * @param songDAO SongDAO object
     * @return Song object
     */
    @Mapping(target = "category", expression = "java(Enum.valueOf( Categories.class, songDAO.category.toUpperCase()))")
    @Mapping(target = "uid", expression = "java(song.getCategory().name() + Song.UID_SPLITTER + songDAO.author + Song.UID_SPLITTER + songDAO.title)")
    Song songDAOToSong(SongDAO songDAO);
}