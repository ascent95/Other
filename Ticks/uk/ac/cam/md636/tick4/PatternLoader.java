package uk.ac.cam.md636.tick4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

public class PatternLoader {

    public static List<Pattern> load(Reader r) throws IOException, PatternFormatException {
        BufferedReader buff = new BufferedReader(r);
        List<Pattern> resultList = new LinkedList<Pattern>();
        String line = null;
        while ((line = buff.readLine()) != null) {
            resultList.add(new Pattern(line));
        }
        return resultList;
    }

    public static List<Pattern> loadFromURL(String url) throws IOException, PatternFormatException {
        URL destination = new URL(url);
        URLConnection conn = destination.openConnection();
        return load(new InputStreamReader(conn.getInputStream()));
    }

    public static List<Pattern> loadFromDisk(String filename) throws IOException, PatternFormatException {
        return load(new FileReader(filename));
    }
}