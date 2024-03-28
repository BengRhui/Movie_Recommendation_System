import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserFrame implements ActionListener, MouseListener {
    static JFrame frame;
    JLabel logoPlaceholder, homePageLabel, movieSearchLabel, favouriteListLabel, watchHistoryLabel, reportIssueLabel,
            reportIssuePlaceholder, changeLanguageLabel, changeLanguagePlaceholder, logoutLabel, logoutPlaceholder;
    JPanel sideBarPanel, homePagePanel, movieSearchPanel, favouriteListPanel, watchHistoryPanel, historyLayer;
    static JPanel overallLayer;
    JLayeredPane overallHomePagePanel, overallMovieSearchPanel, overallFavouriteListPanel, overallWatchHistoryPanel, searchLayer;
    static JLayeredPane homeLayer, favouriteListLayer;
    Color sideBarColour = new Color(225, 205, 187), brighterSideBarColour = new Color(249, 244, 240);
    CardLayout cardLayout = new CardLayout();

    UserFrame(String userID, double xPosition, double yPosition) {

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
        favouriteListLabel.addMouseListener(this);

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
        watchHistoryLabel.addMouseListener(this);

        watchHistoryPanel = new JPanel();
        watchHistoryPanel.setBounds(0, 0, 390, 60);
        watchHistoryPanel.setBackground(Color.WHITE);

        overallWatchHistoryPanel = new JLayeredPane();
        overallWatchHistoryPanel.setBounds(0, 330, 390, 60);
        overallWatchHistoryPanel.add(watchHistoryLabel, JLayeredPane.PALETTE_LAYER);
        overallWatchHistoryPanel.add(watchHistoryPanel, JLayeredPane.DEFAULT_LAYER);

        ImageIcon reportIcon = new ImageIcon("asset/Report Icon.png");
        Image resizingReportIcon = reportIcon.getImage();
        resizingReportIcon = resizingReportIcon.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        reportIcon = new ImageIcon(resizingReportIcon);
        reportIssuePlaceholder = new JLabel();
        reportIssuePlaceholder.setIcon(reportIcon);
        reportIssuePlaceholder.setBounds(30, 490, 100, 70);
        reportIssuePlaceholder.setVerticalAlignment(JLabel.CENTER);
        reportIssuePlaceholder.setHorizontalAlignment(JLabel.CENTER);

        reportIssueLabel = new JLabel("Report Issue");
        reportIssueLabel.setBounds(130, 490, 250, 70);
        reportIssueLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        reportIssueLabel.setHorizontalAlignment(JLabel.LEFT);
        reportIssueLabel.setVerticalAlignment(JLabel.CENTER);

        ImageIcon languageIcon = new ImageIcon("asset/Language Icon.png");
        Image resizingLanguageIcon = languageIcon.getImage();
        resizingLanguageIcon = resizingLanguageIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        languageIcon = new ImageIcon(resizingLanguageIcon);
        changeLanguagePlaceholder = new JLabel();
        changeLanguagePlaceholder.setIcon(languageIcon);
        changeLanguagePlaceholder.setBounds(30, 575, 100, 50);
        changeLanguagePlaceholder.setHorizontalAlignment(JLabel.CENTER);
        changeLanguagePlaceholder.setVerticalAlignment(JLabel.CENTER);

        changeLanguageLabel = new JLabel("Change Language");
        changeLanguageLabel.setBounds(130, 575, 250, 50);
        changeLanguageLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        changeLanguageLabel.setHorizontalAlignment(JLabel.LEFT);
        changeLanguageLabel.setVerticalAlignment(JLabel.CENTER);

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

        logoutLabel = new JLabel("Logout");
        logoutLabel.setBounds(130, 650, 250, 50);
        logoutLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        logoutLabel.setHorizontalAlignment(JLabel.LEFT);
        logoutLabel.setVerticalAlignment(JLabel.CENTER);
        logoutLabel.addMouseListener(this);

        homeLayer = new UserMainPage(userID);

        searchLayer = new MovieSearch(userID);
        searchLayer.setBackground(Color.RED);

        favouriteListLayer = new FavouriteList(userID);

        historyLayer = new JPanel();
        historyLayer.setBackground(Color.ORANGE);

        overallLayer = new JPanel();
        overallLayer.setLayout(cardLayout);
        overallLayer.setBounds(400, 0, 970, 768);
        overallLayer.add(homeLayer, "Home");
        overallLayer.add(searchLayer, "Search");
        overallLayer.add(favouriteListLayer, "Favourite");
        overallLayer.add(historyLayer, "History");

        cardLayout.show(overallLayer, "Home");

        frame.add(logoPlaceholder);
        frame.add(overallHomePagePanel);
        frame.add(overallMovieSearchPanel);
        frame.add(overallFavouriteListPanel);
        frame.add(overallWatchHistoryPanel);

        frame.add(reportIssuePlaceholder);
        frame.add(reportIssueLabel);
        frame.add(changeLanguagePlaceholder);
        frame.add(changeLanguageLabel);
        frame.add(logoutPlaceholder);
        frame.add(logoutLabel);
        frame.add(overallLayer);
        frame.add(sideBarPanel);

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
        if (e.getSource() == homePageLabel && homePagePanel.getBackground() != sideBarColour) {
            homePagePanel.setBackground(sideBarColour);
            movieSearchPanel.setBackground(Color.WHITE);
            favouriteListPanel.setBackground(Color.WHITE);
            watchHistoryPanel.setBackground(Color.WHITE);
            cardLayout.show(overallLayer, "Home");

        } else if (e.getSource() == movieSearchLabel && movieSearchPanel.getBackground() != sideBarColour) {
            movieSearchPanel.setBackground(sideBarColour);
            homePagePanel.setBackground(Color.WHITE);
            favouriteListPanel.setBackground(Color.WHITE);
            watchHistoryPanel.setBackground(Color.WHITE);
            cardLayout.show(overallLayer, "Search");

        } else if (e.getSource() == favouriteListLabel && favouriteListPanel.getBackground() != sideBarColour) {
            favouriteListPanel.setBackground(sideBarColour);
            homePagePanel.setBackground(Color.WHITE);
            movieSearchPanel.setBackground(Color.WHITE);
            watchHistoryPanel.setBackground(Color.WHITE);
            cardLayout.show(overallLayer, "Favourite");

        } else if (e.getSource() == watchHistoryLabel && watchHistoryPanel.getBackground() != sideBarColour) {
            watchHistoryPanel.setBackground(sideBarColour);
            homePagePanel.setBackground(Color.WHITE);
            movieSearchPanel.setBackground(Color.WHITE);
            favouriteListPanel.setBackground(Color.WHITE);
            cardLayout.show(overallLayer, "History");

        } else if (e.getSource() == logoutLabel || e.getSource() == logoutPlaceholder) {
            ImageIcon logoutLogo = new ImageIcon("asset/Logout Logo.png");
            Image resizingLogoutLogo = logoutLogo.getImage();
            resizingLogoutLogo = resizingLogoutLogo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            logoutLogo = new ImageIcon(resizingLogoutLogo);
            int userExit = JOptionPane.showConfirmDialog(frame, "Are you sure you wish to logout from the system?", "Confirm logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logoutLogo);
            if (userExit == JOptionPane.YES_OPTION) {
                try {
                    new Login(frame.getX(), frame.getY());
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "System error. Please inspect the system.");
                }
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

        } else if (e.getSource() == favouriteListLabel && favouriteListPanel.getBackground() != sideBarColour) {
            favouriteListLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            favouriteListPanel.setBackground(brighterSideBarColour);

        } else if (e.getSource() == watchHistoryLabel && watchHistoryPanel.getBackground() != sideBarColour) {
            watchHistoryLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            watchHistoryPanel.setBackground(brighterSideBarColour);

        } else if (e.getSource() == logoutPlaceholder || e.getSource() == logoutLabel) {
            logoutPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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

        if (favouriteListPanel.getBackground() == brighterSideBarColour) {
            favouriteListLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            favouriteListPanel.setBackground(Color.WHITE);
        }

        if (watchHistoryPanel.getBackground() == brighterSideBarColour) {
            watchHistoryLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            watchHistoryPanel.setBackground(Color.WHITE);
        }

        logoutPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
