import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class MovieVideoPage implements ActionListener, MouseListener, KeyListener  {
    JFrame frame;
    JPanel holder, shadow, playPanel;
    JLabel frameTitle, arrowPlaceholder, playPlaceholder, playPrompt, synopsisTitle, favouritesPrompt;
    JButton rateButton;
    static String URL, userID, currentLanguage, previousFrame;
    static int movieID;
    boolean buttonTriggered = false;


    MovieVideoPage(double frameHorizontal, double frameVertical, int movieID, String userID, String previousFrame) throws IOException {

        MovieVideoPage.previousFrame = previousFrame;

        if (UserFrame.frame != null) {
            currentLanguage = UserFrame.currentLanguage;
        } else if (GuestFrame.frame != null) {
            currentLanguage = GuestFrame.currentLanguage;
        }

        MovieVideoPage.userID = userID;
        MovieVideoPage.movieID = movieID;

        String synopsis = null, title = null;
        ArrayList<Movies> movie = Movies.movieList;

        for (Movies movieItem: movie) {
            if (movieID == movieItem.movieIdInDataset) {
                title = movieItem.title;
                synopsis = movieItem.plot;
                URL = movieItem.movieUrl;
            }
        }

        frame = new JFrame("Movie Video Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
        frame.setLocation((int) frameHorizontal, (int) frameVertical);
        frame.setResizable(false);

        JLabel background = new JLabel();
        ImageIcon image = new ImageIcon("asset/User Background.jpg");
        background.setIcon(image);
        background.setBounds(0, 0, 1366, 768);

        holder = new JPanel();
        holder.setBackground(Color.WHITE);
        holder.setBounds(60, 40, 1246, 648);

        shadow = new JPanel();
        shadow.setBackground(new Color(255, 255, 255, 160));
        shadow.setBounds(50, 30, 1266, 668);

        frameTitle = new JLabel(title);
        frameTitle.setBounds(200, 75, 900, 100);
        frameTitle.setFont(new Font("Advent Pro", Font.BOLD, 60));


        ImageIcon arrowImage = new ImageIcon("asset/Back Arrow.png");
        arrowPlaceholder = new JLabel();
        arrowPlaceholder.setBackground(Color.WHITE);
        arrowPlaceholder.setIcon(arrowImage);
        arrowPlaceholder.setBounds(100, 80, 80, 80);
        arrowPlaceholder.setOpaque(true);
        arrowPlaceholder.addMouseListener(this);

        playPanel = new JPanel(null);
        playPanel.setBounds(frameTitle.getX(), frameTitle.getY() + frameTitle.getHeight() + 20, 430, 430);
        playPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 2, 5));
        playPanel.setBackground(Color.LIGHT_GRAY);

        ImageIcon imageIcon = new ImageIcon("asset/Play Button.png");
        playPlaceholder = new JLabel();
        playPlaceholder.setBounds(180, 180, 75, 75);
        playPlaceholder.setIcon(imageIcon);

        playPrompt = new JLabel("Click me to play video:");
        playPrompt.setFont(new Font("Avenir", Font.PLAIN, 20));
        playPrompt.setBounds(50, 120, 330, 50);

        synopsisTitle = new JLabel("Synopsis");
        synopsisTitle.setFont(new Font("Advent Pro", Font.BOLD, 30));
        synopsisTitle.setBounds(playPanel.getX() + playPanel.getWidth() + 30, playPanel.getY(), 150, 50);

        JLabel synopsisText = new JLabel("<html>" + synopsis + "</html>");
        synopsisText.setFont(new Font("Avenir", Font.PLAIN, 18));
        synopsisText.setBounds(synopsisTitle.getX() + synopsisTitle.getWidth(), synopsisTitle.getY() + 18, 450, 350);
        synopsisText.setVerticalAlignment(JLabel.TOP);

        if (userID != null) {

            favouritesPrompt = new JLabel("Save to Favourites:");
            favouritesPrompt.setFont(new Font("Advent Pro", Font.BOLD, 30));
            favouritesPrompt.setBounds(synopsisTitle.getX(), synopsisText.getY() + synopsisText.getHeight(), 250, 50);
            favouritesPrompt.setBackground(Color.RED);

            ArrayList<Favourite> currentFav = Favourite.filterBasedOnID(userID);

            boolean available = false;

            for (Favourite fav : currentFav) {
                if (fav.movieID.equals(String.valueOf(movieID))) {
                    available = true;
                    break;
                }
            }

            JLabel bookmarkHolder = new JLabel();
            bookmarkHolder.setBounds(favouritesPrompt.getX() + favouritesPrompt.getWidth(), favouritesPrompt.getY() + 5, 43, 45);

            ImageIcon bookmarkedIcon = new ImageIcon("asset/Bookmark_Yes.png");
            Image resizeBookmarkedImage = bookmarkedIcon.getImage();
            resizeBookmarkedImage = resizeBookmarkedImage.getScaledInstance(35, 45, Image.SCALE_SMOOTH);
            bookmarkedIcon = new ImageIcon(resizeBookmarkedImage);

            ImageIcon notBookmarkedIcon = new ImageIcon("asset/Bookmark_No.png");
            Image resizeNotBookmarkedImage = notBookmarkedIcon.getImage();
            resizeNotBookmarkedImage = resizeNotBookmarkedImage.getScaledInstance(35, 45, Image.SCALE_SMOOTH);
            notBookmarkedIcon = new ImageIcon(resizeNotBookmarkedImage);

            bookmarkHolder.setIcon(available ? bookmarkedIcon : notBookmarkedIcon);

            ImageIcon finalBookmarkedIcon = bookmarkedIcon;
            ImageIcon finalNotBookmarkedIcon = notBookmarkedIcon;
            bookmarkHolder.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    boolean isCurrentlyBookmarked = bookmarkHolder.getIcon() == finalBookmarkedIcon;

                    if (isCurrentlyBookmarked) {
                        Favourite.removeFavouriteFromList(userID, String.valueOf(movieID));
                        bookmarkHolder.setIcon(finalNotBookmarkedIcon);
                        if (currentLanguage.equals("English")) {
                            JOptionPane.showMessageDialog(UserFrame.frame, "The movie has been removed from the favourite list.", "Success Remove", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                        } else if (currentLanguage.equals("Malay")) {
                            JOptionPane.showMessageDialog(UserFrame.frame, "Filem ini telah dialih keluar dari senarai kegemaran anda.", "Berjaya Dialih Keluar", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                        }
                        UserFrame.frame.dispose();
                        new UserFrame(userID, frame.getX(), frame.getY(), currentLanguage);
                        buttonTriggered = true;
                        UserFrame.frame.setVisible(false);
                    } else {
                        Favourite.addFavouriteToList(userID, String.valueOf(movieID));
                        bookmarkHolder.setIcon(finalBookmarkedIcon);
                        if (currentLanguage.equals("English")) {
                            JOptionPane.showMessageDialog(UserFrame.frame, "The movie has been added to the favourite list.", "Success Added", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                        } else if (currentLanguage.equals("Malay")) {
                            JOptionPane.showMessageDialog(UserFrame.frame, "Filem telah ditambah ke senarai kegemaran anda.", "Berjaya Ditambah", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                        }
                        UserFrame.frame.dispose();
                        new UserFrame(userID, frame.getX(), frame.getY(), currentLanguage);
                        buttonTriggered = true;
                        UserFrame.frame.setVisible(false);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    bookmarkHolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    bookmarkHolder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            });

            rateButton = new JButton("Rate me!");
            rateButton.setBackground(Color.DARK_GRAY);
            rateButton.setFont(new Font("Avenir", Font.PLAIN, 20));
            rateButton.setForeground(Color.WHITE);
            rateButton.setSize(250, 50);
            rateButton.setLocation(synopsisText.getX() + synopsisText.getWidth() - rateButton.getWidth(), bookmarkHolder.getY());
            rateButton.setOpaque(true);
            rateButton.setFocusable(false);
            rateButton.setBorderPainted(false);
            rateButton.addMouseListener(this);

            if (currentLanguage.equals("English")) {
                favouritesPrompt.setText("Save to Favourites:");
                rateButton.setText("Rate me!");
            } else if (currentLanguage.equals("Malay")) {
                favouritesPrompt.setText("Tambah ke senarai:");
                rateButton.setText("Menilai saya!");
            }

            frame.add(rateButton);
            frame.add(favouritesPrompt);
            frame.add(bookmarkHolder);

        }

        playPanel.add(playPlaceholder);
        playPanel.add(playPrompt);
        playPanel.addMouseListener(this);

        if (currentLanguage.equals("English")) {
            frame.setTitle("Movie Video Page");
            playPrompt.setText("Click me to play video:");
            synopsisTitle.setText("Synopsis");
        } else if (currentLanguage.equals("Malay")) {
            frame.setTitle("Halaman Video Filem");
            playPrompt.setText("Tekan saya untuk memainkan video:");
            synopsisTitle.setText("Sinopsis");
        }

        frame.add(synopsisTitle);
        frame.add(synopsisText);
        frame.add(playPanel);
        frame.add(arrowPlaceholder);
        frame.add(frameTitle);
        frame.add(holder);
        frame.add(shadow);
        frame.add(background);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == arrowPlaceholder) {
            if (userID != null) {
                UserFrame.overallLayer.remove(UserFrame.historyLayer);
                UserFrame.historyLayer = new WatchHistory(userID);
                UserFrame.overallLayer.add(UserFrame.historyLayer, "History");
                UserFrame.historyLayer.revalidate();
                UserFrame.historyLayer.repaint();

                UserFrame.currentLanguage = currentLanguage;
                UserFrame.frame.setLocation(frame.getX(), frame.getY());
                UserFrame.frame.setVisible(true);

                if (previousFrame.equals("History") && !buttonTriggered) {
                    UserFrame.cardLayout.show(UserFrame.overallLayer, "History");
                }

            } else {
                GuestFrame.frame.setLocation(frame.getX(), frame.getY());
                GuestFrame.frame.setVisible(true);
            }
            frame.dispose();
        } else if (e.getSource() == playPanel) {
            try {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    URI url = URI.create(URL);
                    if (url.getScheme() != null) {
                        desktop.browse(url);
                    } else {
                        throw new Exception();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Can't open in browser.");
                }
                if (userID != null) {
                    History.readHistoryFromFile();
                    History history = new History(Integer.parseInt(userID), movieID, Instant.now());
                    History.overallHistoryList.add(history);
                    History.writeHistoryToFile();
                }
            } catch (Exception ex) {
                if (currentLanguage.equals("English")) {
                    JOptionPane.showMessageDialog(null, "Video is not available. Please search the movie manually online.");
                } else if (currentLanguage.equals("Malay")) {
                    JOptionPane.showMessageDialog(null, "Video tidak wujud. Sila cari dalam talian secara manual.");
                }
            }
        } else if (e.getSource() == rateButton) {
            String[] options = {"5", "4", "3", "2", "1"};
            int userChoice = -1;
            if (currentLanguage.equals("English")) {
                userChoice = JOptionPane.showOptionDialog(
                        null,
                        "Please choose a rating.",
                        "New Movie Rating",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("asset/Rating.png"),
                        options,
                        null
                );
            } else if (currentLanguage.equals("Malay")) {
                userChoice = JOptionPane.showOptionDialog(
                        null,
                        "Sila pilih penilaian anda.",
                        "Penilaian Filem Baharu",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("asset/Rating.png"),
                        options,
                        null
                );
            }
            if (userChoice != -1) {
                int rating = 5 - userChoice;

                Instant instant = Instant.now();
                long epochTime = instant.getEpochSecond();

                Ratings.readRatingToList();
                Ratings ratingItem = new Ratings(Integer.parseInt(userID), movieID, rating, (int) epochTime);
                Ratings.ratingsEntireList.add(ratingItem);
                Ratings.writeRatingToFile();

                if (currentLanguage.equals("English")) {
                    JOptionPane.showMessageDialog(null, "Ratings saved successful.", "Success save", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                } else if (currentLanguage.equals("Malay")) {
                    JOptionPane.showMessageDialog(null, "Penilaian berjaya disimpan.", "Berjaya Disimpan", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                }
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        arrowPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        playPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (userID != null) {
            rateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        arrowPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        playPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        if (userID != null) {
            rateButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


