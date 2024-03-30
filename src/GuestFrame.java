import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuestFrame implements ActionListener, MouseListener {
    static JFrame frame;
    static String currentLanguage = "English";
    Color ownGray = new Color(242, 242, 242);
    JLabel logoPlaceholder, homePageLabel, movieSearchLabel, favouriteListLabel, watchHistoryLabel,
            changeLanguageLabel, changeLanguagePlaceholder, logoutLabel, logoutPlaceholder;
    JPanel sideBarPanel, homePagePanel, movieSearchPanel, favouriteListPanel, watchHistoryPanel, overallLayer;
    JLayeredPane overallHomePagePanel, overallMovieSearchPanel, overallFavouriteListPanel, overallWatchHistoryPanel;
    Color sideBarColour = new Color(225, 205, 187), brighterSideBarColour = new Color(249, 244, 240);
    CardLayout cardLayout = new CardLayout();
    GuestFrame(double xPosition, double yPosition) {
        frame = new JFrame("Movie Recommendation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
        frame.setLayout(null);
        frame.setLocation((int) xPosition, (int) yPosition);
        frame.setResizable(false);

        sideBarPanel = new JPanel();
        sideBarPanel.setBounds(0, 0, 400, 768);
        sideBarPanel.setBackground(Color.WHITE);

        ImageIcon logo = new ImageIcon("asset/Logo.jpg");
        Image resizingLogo = logo.getImage();
        resizingLogo = resizingLogo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(resizingLogo);
        logoPlaceholder = new JLabel();
        logoPlaceholder.setBounds(20, 20, 100, 100);
        logoPlaceholder.setIcon(resizedLogo);

        homePageLabel = new JLabel("Home Page");
        homePageLabel.setBounds(40, 0, 390, 60);
        homePageLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        homePageLabel.setHorizontalAlignment(JLabel.LEFT);
        homePageLabel.setVerticalAlignment(JLabel.CENTER);
        homePageLabel.addMouseListener(this);

        homePagePanel = new JPanel();
        homePagePanel.setBounds(0, 0, 390, 60);
        homePagePanel.setBackground(sideBarColour);

        overallHomePagePanel = new JLayeredPane();
        overallHomePagePanel.setBounds(0, 150, 390, 60);
        overallHomePagePanel.add(homePageLabel, JLayeredPane.PALETTE_LAYER);
        overallHomePagePanel.add(homePagePanel, JLayeredPane.DEFAULT_LAYER);

        movieSearchLabel = new JLabel("Movie Search");
        movieSearchLabel.setBounds(40, 0, 390, 60);
        movieSearchLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        movieSearchLabel.setHorizontalAlignment(JLabel.LEFT);
        movieSearchLabel.setVerticalAlignment(JLabel.CENTER);
        movieSearchLabel.addMouseListener(this);

        movieSearchPanel = new JPanel();
        movieSearchPanel.setBounds(0, 0, 390, 60);
        movieSearchPanel.setBackground(Color.WHITE);

        overallMovieSearchPanel = new JLayeredPane();
        overallMovieSearchPanel.setBounds(0, 210, 390, 60);
        overallMovieSearchPanel.add(movieSearchLabel, JLayeredPane.PALETTE_LAYER);
        overallMovieSearchPanel.add(movieSearchPanel, JLayeredPane.DEFAULT_LAYER);

        favouriteListLabel = new JLabel("Favourite List");
        favouriteListLabel.setBounds(40, 0, 390, 60);
        favouriteListLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        favouriteListLabel.setHorizontalAlignment(JLabel.LEFT);
        favouriteListLabel.setVerticalAlignment(JLabel.CENTER);
        favouriteListLabel.setForeground(ownGray);

        favouriteListPanel = new JPanel();
        favouriteListPanel.setBounds(0, 0, 390, 60);
        favouriteListPanel.setBackground(Color.WHITE);

        overallFavouriteListPanel = new JLayeredPane();
        overallFavouriteListPanel.setBounds(0, 270, 390, 60);
        overallFavouriteListPanel.add(favouriteListLabel, JLayeredPane.PALETTE_LAYER);
        overallFavouriteListPanel.add(favouriteListPanel, JLayeredPane.DEFAULT_LAYER);

        watchHistoryLabel = new JLabel("Watch History");
        watchHistoryLabel.setBounds(40, 0, 390, 60);
        watchHistoryLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        watchHistoryLabel.setHorizontalAlignment(JLabel.LEFT);
        watchHistoryLabel.setVerticalAlignment(JLabel.CENTER);
        watchHistoryLabel.setForeground(ownGray);

        watchHistoryPanel = new JPanel();
        watchHistoryPanel.setBounds(0, 0, 390, 60);
        watchHistoryPanel.setBackground(Color.WHITE);

        overallWatchHistoryPanel = new JLayeredPane();
        overallWatchHistoryPanel.setBounds(0, 330, 390, 60);
        overallWatchHistoryPanel.add(watchHistoryLabel, JLayeredPane.PALETTE_LAYER);
        overallWatchHistoryPanel.add(watchHistoryPanel, JLayeredPane.DEFAULT_LAYER);

        ImageIcon languageIcon = new ImageIcon("asset/Language Icon.png");
        Image resizingLanguageIcon = languageIcon.getImage();
        resizingLanguageIcon = resizingLanguageIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        languageIcon = new ImageIcon(resizingLanguageIcon);
        changeLanguagePlaceholder = new JLabel();
        changeLanguagePlaceholder.setIcon(languageIcon);
        changeLanguagePlaceholder.setBounds(30, 575, 100, 50);
        changeLanguagePlaceholder.setHorizontalAlignment(JLabel.CENTER);
        changeLanguagePlaceholder.setVerticalAlignment(JLabel.CENTER);
        changeLanguagePlaceholder.addMouseListener(this);

        changeLanguageLabel = new JLabel("Change Language");
        changeLanguageLabel.setBounds(130, 575, 250, 50);
        changeLanguageLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        changeLanguageLabel.setHorizontalAlignment(JLabel.LEFT);
        changeLanguageLabel.setVerticalAlignment(JLabel.CENTER);
        changeLanguageLabel.addMouseListener(this);

        ImageIcon logoutIcon = new ImageIcon("asset/Logout Logo.png");
        Image resizingLogoutIcon = logoutIcon.getImage();
        resizingLogoutIcon = resizingLogoutIcon.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        logoutIcon = new ImageIcon(resizingLogoutIcon);
        logoutPlaceholder = new JLabel();
        logoutPlaceholder.setIcon(logoutIcon);
        logoutPlaceholder.setBounds(35, 650, 100, 50);
        logoutPlaceholder.setHorizontalAlignment(JLabel.CENTER);
        logoutPlaceholder.setVerticalAlignment(JLabel.CENTER);
        logoutPlaceholder.addMouseListener(this);

        logoutLabel = new JLabel("Exit");
        logoutLabel.setBounds(130, 650, 250, 50);
        logoutLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        logoutLabel.setHorizontalAlignment(JLabel.LEFT);
        logoutLabel.setVerticalAlignment(JLabel.CENTER);
        logoutLabel.addMouseListener(this);

        JLayeredPane homeLayer = new GuestMainPage();

        JLayeredPane searchLayer = new MovieSearch(null);

        overallLayer = new JPanel();
        overallLayer.setLayout(cardLayout);
        overallLayer.setBounds(400, 0, 970, 768);
        overallLayer.add(homeLayer, "Home");
        overallLayer.add(searchLayer, "Search");

        cardLayout.show(overallLayer, "Home");

        frame.add(logoPlaceholder);
        frame.add(overallHomePagePanel);
        frame.add(overallMovieSearchPanel);
        frame.add(overallFavouriteListPanel);
        frame.add(overallWatchHistoryPanel);

        frame.add(changeLanguagePlaceholder);
        frame.add(changeLanguageLabel);
        frame.add(logoutPlaceholder);
        frame.add(logoutLabel);
        frame.add(overallLayer);
        frame.add(sideBarPanel);

        frame.setVisible(true);
    }

    public void changeLanguage(String language) {
        if (language.equals("English")) {
            frame.setTitle("Movie Recommendation System");
            homePageLabel.setText("Home Page");
            movieSearchLabel.setText("Movie Search");
            favouriteListLabel.setText("Favourite List");
            watchHistoryLabel.setText("Watch History");
            changeLanguageLabel.setText("Change Language");
            logoutLabel.setText("Exit");
        } else if (language.equals("Malay")) {
            frame.setTitle("Sistem Cadangan Filem");
            homePageLabel.setText("Menu Utama");
            movieSearchLabel.setText("Carian Filem");
            favouriteListLabel.setText("Senarai Kegemaran");
            watchHistoryLabel.setText("Sejarah Menonton");
            changeLanguageLabel.setText("Tukar Bahasa");
            logoutLabel.setText("Keluar");
        }
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
        if (e.getSource() == homePageLabel && homePagePanel.getBackground() != sideBarColour) {
            homePagePanel.setBackground(sideBarColour);
            movieSearchPanel.setBackground(Color.WHITE);
            cardLayout.show(overallLayer, "Home");

        } else if (e.getSource() == movieSearchLabel && movieSearchPanel.getBackground() != sideBarColour) {
            movieSearchPanel.setBackground(sideBarColour);
            homePagePanel.setBackground(Color.WHITE);
            cardLayout.show(overallLayer, "Search");

        } else if (e.getSource() == logoutLabel || e.getSource() == logoutPlaceholder) {
            ImageIcon logoutLogo = new ImageIcon("asset/Logout Logo.png");
            Image resizingLogoutLogo = logoutLogo.getImage();
            resizingLogoutLogo = resizingLogoutLogo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            logoutLogo = new ImageIcon(resizingLogoutLogo);
            int userExit = -1;
            if (currentLanguage.equals("English")) {
                userExit = JOptionPane.showConfirmDialog(frame, "Are you sure you wish to exit the system?", "Confirm exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logoutLogo);
            } else if (currentLanguage.equals("Malay")) {
                userExit = JOptionPane.showConfirmDialog(frame, "Adakah anda ingin keluar dari sistem?", "Pengesahan keluar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logoutLogo);
            }
            if (userExit == JOptionPane.YES_OPTION) {
                try {
                    new Login(frame.getX(), frame.getY());
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "System error. Please inspect the system.");
                }
            }
        } else if (e.getSource() == changeLanguageLabel || e.getSource() == changeLanguagePlaceholder) {
            String[] language = {"English", "Malay"};
            String languageChoice = currentLanguage;
            if (currentLanguage.equals("English")) {
                languageChoice = (String) JOptionPane.showInputDialog(
                        null,
                        "Choose the preferred language:",
                        "Select Language",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        language,
                        currentLanguage);
            } else if (currentLanguage.equals("Malay")) {
                languageChoice = (String) JOptionPane.showInputDialog(
                        null,
                        "Pilih bahasa pilihan anda:",
                        "Pilih Bahasa",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        language,
                        currentLanguage);
            }

            if ((languageChoice != null) && (!languageChoice.isEmpty())) {
                changeLanguage(languageChoice);
                GuestMainPage.changeLanguage(languageChoice);
                MovieSearch.changeLanguage(languageChoice);

                if (languageChoice.equals("English")) {
                    JOptionPane.showMessageDialog(frame, "Language changed successfully.", "Language Change", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                } else if (languageChoice.equals("Malay")) {
                    JOptionPane.showMessageDialog(frame, "Bahasa berjaya ditukar.", "Pilihan Bahasa", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                }

                currentLanguage = languageChoice;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == homePageLabel && homePagePanel.getBackground() != sideBarColour) {
            homePageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            homePagePanel.setBackground(brighterSideBarColour);

        } else if (e.getSource() == movieSearchLabel && movieSearchPanel.getBackground() != sideBarColour) {
            movieSearchLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            movieSearchPanel.setBackground(brighterSideBarColour);

        } else if (e.getSource() == logoutPlaceholder || e.getSource() == logoutLabel) {
            logoutPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        } else if (e.getSource() == changeLanguageLabel || e.getSource() == changeLanguagePlaceholder) {
            changeLanguageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            changeLanguagePlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (homePagePanel.getBackground() == brighterSideBarColour) {
            homePageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            homePagePanel.setBackground(Color.WHITE);
        }

        if (movieSearchPanel.getBackground() == brighterSideBarColour) {
            movieSearchLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            movieSearchPanel.setBackground(Color.WHITE);
        }

        if (e.getSource() == changeLanguageLabel || e.getSource() == changeLanguagePlaceholder) {
            changeLanguageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            changeLanguagePlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        logoutPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
