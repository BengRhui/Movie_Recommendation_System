import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class MovieSearch extends JLayeredPane implements KeyListener, MouseListener {

    CardLayout cardLayout = new CardLayout();
    JLabel backgroundPlaceholder, searchLogoPlaceholder, label, filterPlaceholder;
    static JLabel title, promptText, previous, next;
    JPanel searchBarPlaceholder, container = new JPanel(cardLayout), panel;
    JTextField textField;
    String newArrangeChoice;
    static String userID, currentLanguage = "English";
    int currentDisplay = 0, numberOfPages;

    MovieSearch(String userID) {

        if (UserFrame.frame != null) {
            currentLanguage = UserFrame.currentLanguage;
        } else if (GuestFrame.frame != null) {
            currentLanguage = GuestFrame.currentLanguage;
        }

        MovieSearch.userID = userID;

        this.setSize(970, 768);

        ImageIcon background = new ImageIcon("asset/User Background.jpg");
        backgroundPlaceholder = new JLabel();
        backgroundPlaceholder.setIcon(background);
        backgroundPlaceholder.setBounds(0, 0, 970, 768);
        this.add(backgroundPlaceholder, JLayeredPane.DEFAULT_LAYER);

        title = new JLabel("Search for movies here:");
        title.setFont(new Font("Advent Pro", Font.BOLD, 40));
        title.setBounds(50, 30, 800, 100);
        this.add(title, JLayeredPane.PALETTE_LAYER);

        searchBarPlaceholder = new JPanel();
        searchBarPlaceholder.setLayout(new BorderLayout());
        searchBarPlaceholder.add(new SearchBar());
        searchBarPlaceholder.setBounds(50, 130, 850, 75);
        this.add(searchBarPlaceholder, JLayeredPane.PALETTE_LAYER);

        ImageIcon searchIcon = new ImageIcon("asset/Search Icon.png");
        Image resizingSearchIcon = searchIcon.getImage();
        resizingSearchIcon = resizingSearchIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(resizingSearchIcon);
        searchLogoPlaceholder = new JLabel();
        searchLogoPlaceholder.setIcon(searchIcon);
        searchLogoPlaceholder.setBounds(820, 143, 50, 50);
        searchLogoPlaceholder.setHorizontalAlignment(JLabel.CENTER);
        searchLogoPlaceholder.setVerticalAlignment(JLabel.CENTER);
        this.add(searchLogoPlaceholder, JLayeredPane.MODAL_LAYER);
        searchLogoPlaceholder.addMouseListener(this);

        promptText = new JLabel("Type the movie name here.");
        promptText.setFont(new Font("Avenir", Font.PLAIN, 18));
        promptText.setForeground(Color.LIGHT_GRAY);
        promptText.setBounds(90, 130, 800, 70);
        this.add(promptText, JLayeredPane.MODAL_LAYER);

        textField = new JTextField();
        textField.setFont(new Font("Avenir", Font.PLAIN, 20));
        textField.setBounds(promptText.getX(), promptText.getY(), promptText.getWidth() - 150, promptText.getHeight());
        textField.setBackground(new Color(255, 255, 255, 0));
        textField.setBorder(null);
        this.add(textField, JLayeredPane.POPUP_LAYER);
        textField.addKeyListener(this);

        filterPlaceholder = new JLabel();
        ImageIcon filterImage = new ImageIcon("src/filter.png");
        filterPlaceholder.setIcon(filterImage);
        filterPlaceholder.setBounds(searchLogoPlaceholder.getX() - 60, searchLogoPlaceholder.getY(), 50, 50);
        this.add(filterPlaceholder, JLayeredPane.POPUP_LAYER);
        filterPlaceholder.addMouseListener(this);

        if (currentLanguage.equals("English")) {
            title.setText("Search for movies here:");
            promptText.setText("Type the movie name here.");
        } else if (currentLanguage.equals("Malay")) {
            title.setText("Cari filem di sini:");
            promptText.setText("Masukkan nama filem di sini.");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            MouseEvent clickEvent = new MouseEvent(searchLogoPlaceholder, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, searchLogoPlaceholder.getWidth() / 2, searchLogoPlaceholder.getHeight() / 2, 1, false);
            searchLogoPlaceholder.dispatchEvent(clickEvent);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == textField && !textField.getText().isEmpty()) {
            promptText.setForeground(Color.WHITE);
        } else if (e.getSource() == textField && textField.getText().isEmpty()) {
            promptText.setForeground(Color.LIGHT_GRAY);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == searchLogoPlaceholder) {

            container.setBounds(searchBarPlaceholder.getX() + 20, searchBarPlaceholder.getY() + searchBarPlaceholder.getHeight() + 30, 810, 450);

            String search = textField.getText().strip();
            Movies.appendMovieObject();
            ArrayList<Movies> filteredList = new ArrayList<>();

            for (Movies movie: Movies.movieList) {
                if (movie.title.toLowerCase().contains(search.toLowerCase())) {
                    filteredList.add(movie);
                }
            }

            if (newArrangeChoice != null) {
                if (newArrangeChoice.equals("A - Z")) {
                    filteredList.sort(Comparator.comparing(Movies::getTitle));
                } else if (newArrangeChoice.equals("Z - A")) {
                    filteredList.sort(Comparator.comparing(Movies::getTitle));
                    filteredList.reversed();
                }
            }

            int initialSize = filteredList.size();

            if (initialSize == 0) {

                if (panel != null) {
                    container.removeAll();
                    this.remove(container);
                    this.remove(next);
                    this.remove(previous);
                    this.repaint();
                    this.revalidate();
                }

                label = new JLabel("No results from searches. Please enter another keyword.");
                label.setBounds(searchBarPlaceholder.getX() + 20, searchBarPlaceholder.getY() + searchBarPlaceholder.getHeight() + 20, 700, 50);
                label.setFont(new Font("Avenir", Font.PLAIN, 20));
                this.add(label, JLayeredPane.MODAL_LAYER);

            } else {

                if (label != null) {
                    this.remove(label);
                }

                container.removeAll();
                this.remove(container);

                numberOfPages = (int) Math.ceil(initialSize / 10.0);

                Iterator<Movies> iteratorList;

                if (newArrangeChoice != null && newArrangeChoice.equals("Z - A")) {
                    iteratorList = filteredList.reversed().iterator();
                } else {
                    iteratorList = filteredList.iterator();
                }

                JFrame popUpLoading = new JFrame();

                if (currentLanguage.equals("English")) {
                    popUpLoading.setTitle("Loading");
                } else if (currentLanguage.equals("Malay")) {
                    popUpLoading.setTitle("Sedang diproses");
                }

                popUpLoading.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                popUpLoading.setLayout(new FlowLayout());
                popUpLoading.setSize(300, 50);
                popUpLoading.setLocationRelativeTo(null);
                popUpLoading.setVisible(true);

                int pageCount = 0;
                for (int i = 0; i < numberOfPages; i++) {

                    panel = new JPanel(new GridLayout(2, 5, 10, 10));
                    panel.setBackground(new Color(255, 255, 255, 0));
                    int count = 0;

                    while (iteratorList.hasNext() && count < 10) {
                        JPanel holder = new JPanel(null);
                        holder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                        Movies item = iteratorList.next();

                        String movieName = item.title;
                        JLabel label = new JLabel("<html>" + movieName + "</html>");
                        label.setBounds(10, 155, 110, 75);
                        label.setFont(new Font("Avenir", Font.PLAIN, 14));
                        label.setVerticalAlignment(JLabel.TOP);

                        JLabel posterLabel = new JLabel();
                        posterLabel.setBounds(2, 2, 150, 145);

                        String urlText = Movies.getUrlFromMovieId(String.valueOf(item.movieIdInDataset));

                        assert urlText != null;
                        if (urlText.equals("null")) {
                            ImageIcon image = new ImageIcon("asset/No image.png");
                            Image imageResized = image.getImage();
                            imageResized = imageResized.getScaledInstance(150, 145, Image.SCALE_SMOOTH);
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
                                        new MovieVideoPage(UserFrame.frame.getX(), UserFrame.frame.getY(), item.movieIdInDataset, userID);
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
                                Image imageResize = image.getScaledInstance(150, 145, Image.SCALE_SMOOTH);
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
                                            if (userID != null) {
                                                new MovieVideoPage(UserFrame.frame.getX(), UserFrame.frame.getY(), item.movieIdInDataset, userID);
                                                UserFrame.frame.setVisible(false);
                                            } else {
                                                new MovieVideoPage(GuestFrame.frame.getX(), GuestFrame.frame.getY(), item.movieIdInDataset, null);
                                                GuestFrame.frame.setVisible(false);
                                            }
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
                                JOptionPane.showMessageDialog(null, "Invalid URL for movie " + item.movieIdInDataset);
                            }
                        }

                        ArrayList<Favourite> currentFav = Favourite.filterBasedOnID(userID);

                        boolean available = false;

                        for (Favourite fav : currentFav) {
                            if (fav.movieID.equals(String.valueOf(item.movieIdInDataset))) {
                                available = true;
                                break;
                            }
                        }

                        if (userID != null) {
                            JLabel bookmarkHolder = new JLabel();
                            bookmarkHolder.setBounds(120, 155, 25, 35);

                            ImageIcon bookmarkedIcon = new ImageIcon("asset/Bookmark_Yes.png");
                            Image resizeBookmarkedImage = bookmarkedIcon.getImage();
                            resizeBookmarkedImage = resizeBookmarkedImage.getScaledInstance(25, 35, Image.SCALE_SMOOTH);
                            bookmarkedIcon = new ImageIcon(resizeBookmarkedImage);

                            ImageIcon notBookmarkedIcon = new ImageIcon("asset/Bookmark_No.png");
                            Image resizeNotBookmarkedImage = notBookmarkedIcon.getImage();
                            resizeNotBookmarkedImage = resizeNotBookmarkedImage.getScaledInstance(25, 35, Image.SCALE_SMOOTH);
                            notBookmarkedIcon = new ImageIcon(resizeNotBookmarkedImage);

                            bookmarkHolder.setIcon(available ? bookmarkedIcon : notBookmarkedIcon);

                            ImageIcon finalBookmarkedIcon = bookmarkedIcon;
                            ImageIcon finalNotBookmarkedIcon = notBookmarkedIcon;
                            bookmarkHolder.addMouseListener(new MouseListener() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    boolean isCurrentlyBookmarked = bookmarkHolder.getIcon() == finalBookmarkedIcon;

                                    if (isCurrentlyBookmarked) {
                                        Favourite.removeFavouriteFromList(userID, String.valueOf(item.movieIdInDataset));
                                        bookmarkHolder.setIcon(finalNotBookmarkedIcon);
                                        if (currentLanguage.equals("English")) {
                                            JOptionPane.showMessageDialog(null, "The movie has been removed from the favourite list.", "Success Remove", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                                        } else if (currentLanguage.equals("Malay")) {
                                            JOptionPane.showMessageDialog(null, "Filem ini telah dialih keluar dari senarai kegemaran anda.", "Berjaya Dialih Keluar", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));

                                        }

                                    } else {
                                        Favourite.addFavouriteToList(userID, String.valueOf(item.movieIdInDataset));
                                        bookmarkHolder.setIcon(finalBookmarkedIcon);
                                        if (currentLanguage.equals("English")) {
                                            JOptionPane.showMessageDialog(null, "The movie has been added to the favourite list.", "Success Added", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                                        } else if (currentLanguage.equals("Malay")) {
                                            JOptionPane.showMessageDialog(null, "Filem telah ditambah ke senarai kegemaran anda.", "Berjaya Ditambah", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));

                                        }
                                    }

                                    UserFrame.overallLayer.remove(UserFrame.favouriteListLayer);
                                    UserFrame.overallLayer.add(new FavouriteList(userID), "Favourite");
                                    UserFrame.overallLayer.remove(UserFrame.homeLayer);
                                    UserFrame.overallLayer.add(new UserMainPage(userID), "Home");
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
                            holder.add(bookmarkHolder);
                        }

                        holder.setBackground(Color.WHITE);
                        holder.add(label);
                        holder.add(posterLabel);
                        panel.add(holder);
                        iteratorList.remove();
                        count++;
                    }

                    panel.setVisible(true);
                    panel.setOpaque(true);

                    if (i == numberOfPages - 1) {
                        int remaining = 0;
                        if (initialSize % 10 != 0) {
                            remaining = 10 - (initialSize % 10);
                        }
                        for (int j = 0; j < remaining; j++) {
                            JPanel blank = new JPanel();
                            blank.setBackground(new Color(255, 255, 255, 0));
                            panel.add(blank);
                        }
                    }
                    container.add(panel, "card" + i);
                    pageCount = i;
                }

                popUpLoading.dispose();

                cardLayout.show(container, "card0");
                this.add(container, JLayeredPane.MODAL_LAYER);

                if (pageCount > 0) {
                    previous = new JLabel("Previous");
                    previous.setBounds(70, 690, 100, 30);
                    previous.setFont(new Font("Avenir", Font.PLAIN, 18));
                    previous.addMouseListener(this);
                    this.add(previous, JLayeredPane.POPUP_LAYER);
                    previous.setVisible(false);

                    next = new JLabel("Next");
                    next.setBounds(780, 690, 100, 30);
                    next.setHorizontalAlignment(JLabel.RIGHT);
                    next.setFont(new Font("Avenir", Font.PLAIN, 18));
                    next.addMouseListener(this);
                    this.add(next, JLayeredPane.POPUP_LAYER);

                    if (currentLanguage.equals("English")) {
                        previous.setText("Previous");
                        next.setText("Next");
                    } else if (currentLanguage.equals("Malay")) {
                        previous.setText("Sebelumnya");
                        next.setText("Selepasnya");
                    }

                } else {
                    if (previous != null) {
                        this.remove(previous);
                    }
                    if (next != null) {
                        this.remove(next);
                    }
                    this.repaint();
                    this.revalidate();
                }
            }
        }

    }

    public static void changeLanguage(String language) {
        if (language.equals("English")) {
            title.setText("Search for movies here:");
            promptText.setText("Type the movie name here.");
            if (previous != null) {
                previous.setText("Previous");
            }
            if (next != null) {
                next.setText("Next");
            }
            currentLanguage = "English";

        } else if (language.equals("Malay")) {
            title.setText("Cari filem di sini:");
            promptText.setText("Masukkan nama filem di sini.");
            if (previous != null) {
                previous.setText("Sebelumnya");
            }
            if (next != null) {
                next.setText("Selepasnya");
            }
            currentLanguage = "Malay";
        }
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
        } else if (e.getSource() == filterPlaceholder) {

            String[] arrange = {"A - Z", "Z - A"};
            String arrangeChoice;

            if (newArrangeChoice == null) {
                newArrangeChoice = "A - Z";
            }

            if (currentLanguage.equals("English")) {
                arrangeChoice = (String) JOptionPane.showInputDialog(
                        null,
                        "How would you like to sort your movies:",
                        "Select Arrangement",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        arrange,
                        newArrangeChoice);
                newArrangeChoice = arrangeChoice;
            } else if (currentLanguage.equals("Malay")) {
                arrangeChoice = (String) JOptionPane.showInputDialog(
                        null,
                        "Bagaimanakah anda ingin menyusun filem:",
                        "Pilih Susunan",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        arrange,
                        newArrangeChoice);
                newArrangeChoice = arrangeChoice;
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        searchLogoPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        filterPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (previous != null) {
            previous.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        if (next != null) {
            next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        searchLogoPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        filterPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        if (previous != null) {
            previous.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if (next != null) {
            next.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}



class SearchBar extends JPanel {
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(Color.BLACK);
        g2D.setStroke(new BasicStroke(3));
        g2D.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 75, 75);
        g2D.setPaint(Color.WHITE);
        g2D.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 75, 75);
    }
}
