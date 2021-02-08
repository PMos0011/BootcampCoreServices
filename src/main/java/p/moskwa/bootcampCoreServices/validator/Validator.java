package p.moskwa.bootcampCoreServices.validator;

import p.moskwa.bootcampCoreServices.dataModel.RemovedElements;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    public RemovedElements validateSongDAOList(List<SongDAO> songDAOList, String fileName) {

        RemovedElements removedElements = new RemovedElements();

        songDAOList.removeIf(songDAO -> {
            if (isSongDAOInvalid(songDAO) || isSongVotesInvalid(songDAO)) {
                System.out.println(" nieprawidłowa piosenka: " + songDAO.toString() + " w pliku " + fileName);

                if (!removedElements.getRemovedSongs().containsKey(fileName)) {
                    List<SongDAO> removedSongs = new ArrayList<>();
                    removedSongs.add(songDAO);
                    removedElements.getRemovedSongs().put(fileName, removedSongs);
                } else
                    removedElements.getRemovedSongs().get(fileName).add(songDAO);

                return true;
            }
            return false;
        });
        return removedElements;
    }

    private boolean isSongDAOInvalid(SongDAO songDAO) {
        return Arrays.stream(songDAO.getClass().getFields())
                .anyMatch(field -> {
                    try {
                        String value = (String) field.get(songDAO);
                        if (value != null) {
                            value = value.trim();
                            return value.equals("");
                        }
                    } catch (IllegalAccessException ignored) {
                    }
                    return true;
                });
    }

    private boolean isSongVotesInvalid(SongDAO songDAO) {
        String regex = "[0-9]+";
        return !Pattern.compile(regex)
                .matcher(songDAO.votes)
                .matches();
    }
}