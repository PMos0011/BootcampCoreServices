package p.moskwa.bootcampCoreServices.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

@Mapper( imports = Categories.class)
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "category",expression = "java(Enum.valueOf( Categories.class, songDAO.category.toUpperCase()))")
    Song songDAOToSong(SongDAO songDAO);
}