import javax.swing.*;
import java.io.*;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;

public class SystemRating {

    Instant timestamp;
    int rating;
    String userFeedback;

    static ArrayList<SystemRating> overallRatingList = new ArrayList<>();

    public SystemRating(Instant timestamp, String rating, String userFeedback) {
        this.timestamp = timestamp;
        this.rating = Integer.parseInt(rating);
        this.userFeedback = userFeedback;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public static void readSystemRatingFromFile() {
        overallRatingList.clear();
        try {
            BufferedReader read = new BufferedReader(new FileReader("textfile/systemRating.txt"));
            String line;
            while ((line = read.readLine()) != null) {
                String[] array = line.split(";");
                SystemRating rating = new SystemRating(Instant.ofEpochSecond(Long.parseLong(array[0])), array[1], array[2]);
                overallRatingList.add(rating);
            }
            read.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in reading system rating file. Please inspect code.");
        }
    }

    public static void writeSystemRatingToFile() {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("textfile/systemRating.txt"));
            for (SystemRating rating: overallRatingList) {
                write.write(rating.timestamp.getEpochSecond() + ";" + rating.rating + ";" + rating.userFeedback);
                write.newLine();
            }
            write.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing to system rating file. Please inspect code.");
        }
    }

    public static void addSystemRatings(SystemRating rating) {
        readSystemRatingFromFile();
        overallRatingList.add(rating);
        writeSystemRatingToFile();
    }

}
