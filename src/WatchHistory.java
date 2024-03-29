import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WatchHistory extends JLayeredPane {

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
        currentHistoryList.sort(Comparator.comparing(History::getTime));

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 255, 255, 0));
        panel.setLocation(title.getX(), title.getY() + title.getHeight() + 10);
        panel.setSize(850, 570);

        int recordCount = 0;

        for (History history: currentHistoryList.reversed()) {
            JPanel subPanel = new JPanel(null);
            subPanel.setPreferredSize(new Dimension(850, 30));

            if (recordCount % 2 == 0) {
                subPanel.setBackground(Color.WHITE);
            } else {
                subPanel.setBackground(new Color(255, 255, 255, 0));
            }

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String formattedTime = format.format(history.time.atZone(ZoneId.of("Asia/Kuala_Lumpur")));

            JLabel timePlaceholder = new JLabel("●  " + formattedTime);
            timePlaceholder.setFont(new Font("Avenir", Font.PLAIN, 16));
            timePlaceholder.setBounds(20, 0, 180, 30);

            JLabel titleName = new JLabel(Movies.getNameFromMovieId(String.valueOf(history.movieID)));
            titleName.setFont(new Font("Avenir", Font.PLAIN, 16));
            titleName.setBounds(200, 0, 600, 30);

            titleName.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    try {
                        new MovieVideoPage(UserFrame.frame.getX(), UserFrame.frame.getY(), history.movieID, userID);
                        UserFrame.frame.setVisible(false);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error with system. Please inspect.");
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    titleName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    titleName.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            });

            subPanel.add(timePlaceholder);
            subPanel.add(titleName);

            panel.add(subPanel);
            if (recordCount == 18) {
                break;
            } else {
                recordCount++;
            }
        }

        this.add(panel, JLayeredPane.PALETTE_LAYER);
    }
}
