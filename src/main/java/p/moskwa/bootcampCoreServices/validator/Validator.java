package p.moskwa.bootcampCoreServices.validator;

import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * SongDAO validator
 *
 * @see SongDAO
 * @since 1.0
 */
public class Validator {

    /**
     * Removes invalid SongsDAOs
     *
     * @param songDAOList {@link SongDAO} as collection
     * @return {@link SongDAO} as collection
     */
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

    /**
     * Validates fields in SongDAO
     *
     * @param songDAO {@link SongDAO}
     * @return true if fields value are empty or null
     */
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

    /**
     * Validates votes field
     *
     * @param songDAO {@link SongDAO}
     * @return true if field value includes none numeric characters
     */
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