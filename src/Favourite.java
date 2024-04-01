import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Favourite {

    String userID, movieID;


    static ArrayList<Favourite> overallFavouriteList = new ArrayList<>();

    public Favourite(String userID, String movieID) {
        this.userID = userID;
        this.movieID = movieID;
    }

    public String getUserID() {
        return userID;
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

    public static void writeFavouriteToFile() {
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("textfile/favourite.txt"));
            overallFavouriteList.sort(Comparator.comparing(Favourite::getUserID));
            for (Favourite fav: overallFavouriteList) {
                wr.write(fav.userID + ";" + fav.movieID);
                wr.newLine();
            }
            wr.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to write to favourite.txt. Please inspect.");
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

    public static void addFavouriteToList(String userID, String movieID) {
        readToList();
        Favourite fav = new Favourite(userID, movieID);
        overallFavouriteList.add(fav);
        writeFavouriteToFile();
    }

    public static void removeFavouriteFromList(String userID, String movieID) {
        readToList();
        int index = -1;
        for (int i = 0; i < overallFavouriteList.size(); i ++) {
            if (overallFavouriteList.get(i).userID.equals(userID) && overallFavouriteList.get(i).movieID.equals(movieID)) {
                index = i;
            }
        }
        overallFavouriteList.remove(index);
        writeFavouriteToFile();
    }

}
