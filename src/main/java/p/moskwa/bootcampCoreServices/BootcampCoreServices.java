package p.moskwa.bootcampCoreServices;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.Songs;
import p.moskwa.bootcampCoreServices.gui.MainWindow;
import java.io.File;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BootcampCoreServices {

    public static void main(String[] args){
        System.out.println("Starting");

        MainWindow mainWindow = new MainWindow();

//        Song song1 = new Song("tyt1","a1","al1","cat1",1);
//        Song song2 = new Song("tyt2","a1","al1","cat1",1);
//        Song song3 = new Song("tyt3","a1","al1","cat1",1);
//
//        Songs songs = new Songs();
//        List<Song> songList = Arrays.asList(song1,song2,song3);
//        songs.setSongs(Arrays.asList(song1,song2,song3));
//
//        CsvMapper mapper = new CsvMapper();
//        try {
//            mapper.writeValue(new File("./test.xml"),songs);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}