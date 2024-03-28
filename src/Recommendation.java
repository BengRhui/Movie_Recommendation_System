import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Recommendation {

    int userID, movieID, rankings;

    public Recommendation(String userID, String movieID, String rankings) {
        this.userID = (int) Double.parseDouble(userID);
        this.movieID = (int) Double.parseDouble(movieID);
        this.rankings = (int) Double.parseDouble(rankings);
    }

    public static String getNameFromRecommendation(String movieID) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader("textfile/Movies.txt"));
            String line;
            while ((line = rd.readLine()) != null) {
                String[] details = line.split("§");
                if (Integer.parseInt(details[0]) == Integer.parseInt(movieID)) {
                    return details[1];
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error from retrieving name from Movies.txt.");
        }
        return null;
    }

    public static String getURLFromRecommendation(String movieID) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader("textfile/Movies.txt"));
            String line;
            while ((line = rd.readLine()) != null) {
                String[] details = line.split("§");
                if (Integer.parseInt(details[0]) == Integer.parseInt(movieID)) {
                    return details[5];
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error from retrieving name from Movies.txt.");
        }
        return null;
    }

}
