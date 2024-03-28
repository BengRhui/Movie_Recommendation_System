import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Favourite {

    String userID, movieID;


    static ArrayList<Favourite> overallFavouriteList = new ArrayList<>();

    public Favourite(String userID, String movieID) {
        this.userID = userID;
        this.movieID = movieID;
    }

    public static void readToList() {
        overallFavouriteList.clear();
        try {
            BufferedReader read = new BufferedReader(new FileReader("textfile/favourite.txt"));
            String line;
            while ((line = read.readLine()) != null) {
                String[] dataArray = line.split(";");
                Favourite fav = new Favourite(dataArray[0], dataArray[1]);
                overallFavouriteList.add(fav);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in reading from favourite.txt. Please try again.");
        }
    }

    public static ArrayList<Favourite> filterBasedOnID(String userID) {
        readToList();
        ArrayList<Favourite> filteredList = new ArrayList<>();
        for (Favourite fav: overallFavouriteList) {
            if (fav.userID.equals(userID)) {
                filteredList.add(fav);
            }
        }
        return filteredList;
    }

}
