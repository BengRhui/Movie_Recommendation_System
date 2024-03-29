import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WatchHistory extends JLayeredPane {

    JPanel container;
    CardLayout cardLayout = new CardLayout();
    JLabel previous, next;
    int currentDisplay = 0, numberOfPages;
    WatchHistory(String userID) {

        this.setSize(970, 768);

        ImageIcon background = new ImageIcon("asset/User Background.jpg");
        JLabel backgroundPlaceholder = new JLabel();
        backgroundPlaceholder.setIcon(background);
        backgroundPlaceholder.setBounds(0, 0, 970, 768);
        this.add(backgroundPlaceholder, JLayeredPane.DEFAULT_LAYER);

        JLabel title = new JLabel("Movies watched previously:");
        title.setFont(new Font("Advent Pro", Font.BOLD, 40));
        title.setBounds(50, 30, 800, 100);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        this.add(title, JLayeredPane.PALETTE_LAYER);

        ArrayList<History> currentHistoryList = History.filterHistoryFromID(userID);
        for (History history: currentHistoryList) {
            System.out.println(history.userID + "\t" + history.movieID + "\t" + history.time);
        }
    }
}
