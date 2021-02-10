package p.moskwa.bootcampCoreServices.validator;

import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    public List<SongDAO> validateSongDAOList(List<SongDAO> songDAOList) {
        List<SongDAO> removedSongs = new ArrayList<>();

        songDAOList.removeIf(songDAO -> {
            if (isSongDAOInvalid(songDAO) || isSongVotesInvalid(songDAO) || isSongCategoryInvalid(songDAO)) {
                removedSongs.add(songDAO);
                return true;
            }
            return false;
        });

        return removedSongs;
    }

    public boolean isSongDAOInvalid(SongDAO songDAO) {
        return Arrays.stream(songDAO.getClass().getFields())
                .anyMatch(field -> {
                    try {
                        String value = (String) field.get(songDAO);
                        if (value != null) {
                            value = value.trim();
                            field.set(songDAO, value);
                            return value.equals("");
                        }
                    } catch (IllegalAccessException ignored) {
                    }
                    return true;
                });
    }

    public boolean isSongVotesInvalid(SongDAO songDAO) {
        String regex = "[0-9]+";
        return !Pattern.compile(regex)
                .matcher(songDAO.votes)
                .matches();
    }

    private boolean isSongCategoryInvalid(SongDAO songDAO) {
        try {
            Categories.valueOf(songDAO.category.toUpperCase());
            return false;
        } catch (Exception ignore) {
        }
        return true;
    }
}