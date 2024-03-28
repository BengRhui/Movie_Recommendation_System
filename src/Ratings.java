import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Ratings {

    int userId, movieId, timestamp;
    double rating;
    static ArrayList<Ratings> ratingsEntireList = new ArrayList<>();

    public Ratings(int userId, int movieId, double rating, int timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public static void readRatingToList() {
        ratingsEntireList.clear();
        try {
            FileReader file = new FileReader("textfile/ratings.csv");
            BufferedReader rd = new BufferedReader(file);
            String line;
            int count = 0;
            while ((line = rd.readLine()) != null) {
                if (count != 0) {
                    String[] wholeLine = line.split(",");
                    Ratings rating = new Ratings(Integer.parseInt(wholeLine[0]), Integer.parseInt(wholeLine[1]), Double.parseDouble(wholeLine[2]), Integer.parseInt(wholeLine[3]));
                    ratingsEntireList.add(rating);
                }
                count ++;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in retrieving ratings.csv file.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Fail to capture data. Inspect file and look for any invalid numbers.");
        }
    }

    public int getID() {
        return userId;
    }

    public static void writeRatingToFile() {
        ratingsEntireList.sort(Comparator.comparing(Ratings :: getID));
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("textfile/ratings.csv"));
            wr.write("userId,movieId,rating,timestamp");
            wr.newLine();
            for (Ratings rating: ratingsEntireList) {
                String line = rating.userId + "," + rating.movieId + "," + rating.rating + "," + rating.timestamp;
                wr.write(line);
                wr.newLine();
            }
            wr.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error writing to ratings.csv file.");
        }
    }

}
