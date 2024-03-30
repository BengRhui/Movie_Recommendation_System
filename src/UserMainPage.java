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

public class UserMainPage extends JLayeredPane implements MouseListener {

    JPanel container;
    CardLayout cardLayout = new CardLayout();
    JLabel previous, next;
    static JLabel title;
    int currentDisplay = 0, numberOfPages;
    UserMainPage(String userID) {

        this.setSize(970, 768);

        ImageIcon background = new ImageIcon("asset/User Background.jpg");
        JLabel backgroundPlaceholder = new JLabel();
        backgroundPlaceholder.setIcon(background);
        backgroundPlaceholder.setBounds(0, 0, 970, 768);
        this.add(backgroundPlaceholder, JLayeredPane.DEFAULT_LAYER);

        title = new JLabel("Top picks we recommend:");
        title.setFont(new Font("Advent Pro", Font.BOLD, 40));
        title.setBounds(50, 30, 800, 100);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        this.add(title, JLayeredPane.PALETTE_LAYER);

        ArrayList<Recommendation> listOfRecommendation = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("textfile/recommendation.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayFromFile = line.split(";");
                if (Double.parseDouble(arrayFromFile[0]) == Double.parseDouble(userID)) {
                    Recommendation newRec = new Recommendation(arrayFromFile[0], arrayFromFile[1], arrayFromFile[2]);
                    listOfRecommendation.add(newRec);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error ");
        }

        if (listOfRecommendation.isEmpty()) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel.setBounds(title.getX(), title.getY() + title.getHeight(), 800, 200);
            panel.setBackground(new Color(255, 255, 255, 0));
            JLabel name = new JLabel("No recommendation yet. Please rate at least 10 movies to obtain recommendations.");
            name.setFont(new Font("Avenir", Font.PLAIN, 20));
            panel.add(name);
            add(panel, PALETTE_LAYER);

        } else {
            int initialSize = listOfRecommendation.size();
            numberOfPages = (int) Math.ceil(initialSize / 10.0);

            container = new JPanel(cardLayout);

            Iterator<Recommendation> iteratorList = listOfRecommendation.iterator();

            JFrame popUpLoading = new JFrame("Loading");
            popUpLoading.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            popUpLoading.setLayout(new FlowLayout());
            popUpLoading.setSize(300, 50);
            popUpLoading.setLocationRelativeTo(null);
            popUpLoading.setVisible(true);

            int pageCount = 0;
            for (int i = 0; i < numberOfPages; i++) {
                JPanel panel = new JPanel(new GridLayout(2, 5, 10, 10));
                int count = 0;

                while (iteratorList.hasNext() && count < 10) {
                    JPanel holder = new JPanel(null);
                    holder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                    Recommendation item = iteratorList.next();

                    String movieName = Recommendation.getNameFromRecommendation(String.valueOf(item.movieID));
                    JLabel label = new JLabel("<html>" + movieName + "</html>");
                    label.setBounds(10, 200, 110, 75);
                    label.setFont(new Font("Avenir", Font.PLAIN, 16));
                    label.setVerticalAlignment(JLabel.TOP);

                    JLabel posterLabel = new JLabel();
                    posterLabel.setBounds(2, 2, 160, 190);

                    String urlText = Recommendation.getURLFromRecommendation(String.valueOf(item.movieID));

                    assert urlText != null;
                    if (urlText.equals("null")) {
                        ImageIcon image = new ImageIcon("asset/No image.png");
                        Image imageResized = image.getImage();
                        imageResized = imageResized.getScaledInstance(160, 190, Image.SCALE_SMOOTH);
                        ImageIcon posterImage = new ImageIcon(imageResized);
                        posterLabel.setIcon(posterImage);
                        posterLabel.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {

                            }

                            @Override
                            public void mousePressed(MouseEvent e) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                try {
                                    new MovieVideoPage(UserFrame.frame.getX(), UserFrame.frame.getY(), item.movieID, userID);
                                    UserFrame.frame.setVisible(false);
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(null, "Error in opening page. Please check User Main Page.");
                                }
                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                posterLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                posterLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                            }
                        });

                    } else {
                        try {
                            URL imageURL = new URI(urlText).toURL();
                            BufferedImage image = ImageIO.read(imageURL);
                            Image imageResize = image.getScaledInstance(160, 190, Image.SCALE_SMOOTH);
                            ImageIcon posterImage = new ImageIcon(imageResize);
                            posterLabel.setIcon(posterImage);
                            posterLabel.addMouseListener(new MouseListener() {
                                @Override
                                public void mouseClicked(MouseEvent e) {

                                }

                                @Override
                                public void mousePressed(MouseEvent e) {

                                }

                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    try {
                                        new MovieVideoPage(UserFrame.frame.getX(), UserFrame.frame.getY(), item.movieID, userID);
                                        UserFrame.frame.setVisible(false);
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null, "Error in opening page. Please check User Main Page.");
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    posterLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    posterLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                                }
                            });
                        } catch (URISyntaxException | IOException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid URL for movie " + item.movieID);
                        }
                    }

                    ArrayList<Favourite> currentFav = Favourite.filterBasedOnID(userID);

                    boolean available = false;

                    for (Favourite fav: currentFav) {
                        if (fav.movieID.equals(String.valueOf(item.movieID))) {
                            available = true;
                            break;
                        }
                    }

                    JLabel bookmarkHolder = new JLabel();
                    bookmarkHolder.setBounds(120, 200, 43, 45);

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
                                Favourite.removeFavouriteFromList(userID, String.valueOf(item.movieID));
                                bookmarkHolder.setIcon(finalNotBookmarkedIcon);
                                JOptionPane.showMessageDialog(null, "The movie has been removed from the favourite list.", "Success Remove", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                            } else {
                                Favourite.addFavouriteToList(userID, String.valueOf(item.movieID));
                                bookmarkHolder.setIcon(finalBookmarkedIcon);
                                JOptionPane.showMessageDialog(null, "The movie has been added to the favourite list.", "Success Added", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                            }

                            UserFrame.overallLayer.remove(UserFrame.searchLayer);
                            UserFrame.overallLayer.add(new MovieSearch(userID), "Search");
                            UserFrame.overallLayer.remove(UserFrame.favouriteListLayer);
                            UserFrame.overallLayer.add(new FavouriteList(userID), "Favourite");
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



                    holder.setBackground(Color.WHITE);
                    holder.add(label);
                    holder.add(posterLabel);
                    holder.add(bookmarkHolder);

                    panel.add(holder);
                    iteratorList.remove();
                    count++;
                }

                if (i == numberOfPages - 1) {
                    int remaining = 0;
                    if (initialSize % 10 != 0) {
                        remaining = 10 - (initialSize % 10);
                    }
                    for (int j = 0; j < remaining; j++) {
                        JPanel blank = new JPanel();
                        blank.setBackground(Color.WHITE);
                        panel.add(blank);
                    }
                }
                container.add(panel, "card" + i);
                pageCount = i;
            }

            popUpLoading.dispose();

            cardLayout.show(container, "card0");
            container.setBounds(title.getX(), title.getY() + title.getHeight() + 10, 860, 550);
            this.add(container, JLayeredPane.PALETTE_LAYER);

            if (pageCount > 0) {
                previous = new JLabel("Previous");
                previous.setBounds(20, 700, 100, 50);
                previous.addMouseListener(this);
                this.add(previous, JLayeredPane.PALETTE_LAYER);
                previous.setVisible(false);

                next = new JLabel("Next");
                next.setBounds(400, 700, 100, 50);
                next.addMouseListener(this);
                this.add(next, JLayeredPane.PALETTE_LAYER);
            }
        }


    }

    public static void changeLanguage(String language) {
        if (language.equals("English")) {
            title.setText("Top picks we recommend:");
        } else if (language.equals("Malay")) {
            title.setText("Filem yang kami cadangkan:");
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == previous){
            next.setVisible(true);
            currentDisplay -= 1;
            cardLayout.show(container, "card" + currentDisplay);
            if (currentDisplay == 0) {
                previous.setVisible(false);
            }
        } else if (e.getSource() == next){
            previous.setVisible(true);
            currentDisplay += 1;
            cardLayout.show(container, "card" + currentDisplay);
            if (currentDisplay == numberOfPages - 1) {
                next.setVisible(false);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        previous.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        previous.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        next.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
