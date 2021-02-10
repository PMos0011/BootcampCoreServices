package p.moskwa.bootcampCoreServices.gui.services;

import p.moskwa.bootcampCoreServices.dataModel.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindowInstance;

public class ReportMenuServices {

    public void createReport(ActionEvent action, ButtonGroup reportButtonsGroup) {
        JMenuItem jMenuItem = (JMenuItem) action.getSource();
        ButtonModel radioButton = reportButtonsGroup.getSelection();

        int iterationCount = 0;
        if (radioButton.getActionCommand() != null)
            iterationCount = Integer.parseInt(radioButton.getActionCommand());

        List<Song> sortedSongList = getMainWindowInstance().getSongService().getSortedSongList(jMenuItem.getName());
        HashMap<Integer, List<Song>> rankingList = createRankingList(sortedSongList, iterationCount);

        getMainWindowInstance().getMainContentPanel().displayReport(rankingList);
    }

    public HashMap<Integer, List<Song>> createRankingList(List<Song> sortedSongList, int iterationCount) {
        HashMap<Integer, List<Song>> rankingList = new HashMap<>();
        int songCounter = 1;
        int rankingPlace = 1;
        Song previewSong = new Song();

        ListIterator<Song> songIterator = sortedSongList.listIterator();

        while (songIterator.hasNext()) {
            Song currentSong = songIterator.next();
            if (songIterator.previousIndex() > 0 && previewSong.getVotes().equals(currentSong.getVotes())) {
                rankingList.get(rankingPlace).add(currentSong);
            } else {
                rankingPlace = songCounter;
                if (iterationCount != 0 && rankingPlace > iterationCount)
                    break;

                rankingList.put(rankingPlace, new ArrayList<>() {{
                    add(currentSong);
                }});
            }
            previewSong = currentSong;
            songCounter++;
        }
        return rankingList;
    }
}