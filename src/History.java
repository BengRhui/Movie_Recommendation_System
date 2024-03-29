import javax.swing.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public class History {

    int userID, movieID;
    Instant time;

    static ArrayList<History> overallHistoryList = new ArrayList<>();

    public Instant getTime() {
        return time;
    }

    public History(int userID, int movieID, Instant time) {
        this.userID = userID;
        this.movieID = movieID;
        this.time = time;
    }

    public static void readHistoryFromFile() {
        overallHistoryList.clear();
        try {
            BufferedReader rd = new BufferedReader(new FileReader("textfile/history.txt"));
            String line;
            while ((line = rd.readLine()) != null) {
                String[] lineList = line.split(";");
                long epochTime = Long.parseLong(lineList[2]);
                Instant instant = Instant.ofEpochSecond(epochTime);
                History history = new History(Integer.parseInt(lineList[0]), Integer.parseInt(lineList[1]), instant);
                overallHistoryList.add(history);
            }
            rd.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in retrieving history.txt. Please check text file.");
        }
    }

    public static void writeHistoryToFile() {
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("textfile/history.txt"));
            for (History history: overallHistoryList) {
                wr.write(history.userID + ";" + history.movieID + ";" + history.time.getEpochSecond());
                wr.newLine();
            }
            wr.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing to history.txt. Please examine content");
        }
    }

    public static ArrayList<History> filterHistoryFromID(String userID) {
        readHistoryFromFile();
        ArrayList<History> list = new ArrayList<>();
        for (History history: overallHistoryList) {
            if (history.userID == Integer.parseInt(userID)) {
                list.add(history);
            }
        }
        return list;
    }
}
