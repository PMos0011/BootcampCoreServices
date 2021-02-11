package p.moskwa.bootcampCoreServices.gui.services;

import p.moskwa.bootcampCoreServices.dataModel.RankedSongList;
import p.moskwa.bootcampCoreServices.dataModel.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindowInstance;

/**
 * Report services
 *
 * @since 1.0
 */
public class ReportMenuServices {

    /**
     * Determines type of songs ranking, invoke sort method, create ranking method
     * and display method
     *
     * @param action             calling component data
     * @param reportButtonsGroup radio buttons selection data
     */
    public void createReport(ActionEvent action, ButtonGroup reportButtonsGroup) {
        JMenuItem jMenuItem = (JMenuItem) action.getSource();
        ButtonModel radioButton = reportButtonsGroup.getSelection();

        int iterationCount = 0;
        if (radioButton.getActionCommand() != null)
            iterationCount = Integer.parseInt(radioButton.getActionCommand());

        List<Song> sortedSongList = getMainWindowInstance().getSongService().getSortedSongList(jMenuItem.getName());
        List<RankedSongList> rankingList = createRankingList(sortedSongList, iterationCount);
        getMainWindowInstance().getSideBar().clearSidebar();
        getMainWindowInstance().getMainContentPanel().displayReport(rankingList);
    }

    /**
     * Creates chosen by user songs ranking report
     *
     * @param sortedSongList sorted songs collection
     * @param iterationCount maximum ranking positions
     * @return ranking report as RankedSongLis collection
     * @see RankedSongList
     */
    public List<RankedSongList> createRankingList(List<Song> sortedSongList, int iterationCount) {
        List<RankedSongList> rankingList = new ArrayList<>();
        int songCounter = 1;
        int rankingPlace;
        Song previewSong = new Song();

        ListIterator<Song> songIterator = sortedSongList.listIterator();

        while (songIterator.hasNext()) {
            Song currentSong = songIterator.next();
            if (songIterator.previousIndex() > 0 && previewSong.getVotes().equals(currentSong.getVotes())) {
                rankingList.get(rankingList.size() - 1).getSongList().add(currentSong);
            } else {
                rankingPlace = songCounter;
                if (iterationCount != 0 && rankingPlace > iterationCount)
                    break;

                RankedSongList newRankedList = new RankedSongList();
                newRankedList.setPlace(rankingPlace);
                newRankedList.getSongList().add(currentSong);
                rankingList.add(newRankedList);
            }
            previewSong = currentSong;
            songCounter++;
        }
        return rankingList;
    }
}