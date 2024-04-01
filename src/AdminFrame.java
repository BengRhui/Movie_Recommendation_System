import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdminFrame implements ActionListener, MouseListener {
    static JFrame frame;
    JLabel logoPlaceholder, feedbackLabel, maintenanceLabel, logoutLabel, logoutPlaceholder, systemName;
    JPanel sideBarPanel, feedbackPanel, maintenancePanel;
    static JPanel overallLayer;
    JLayeredPane overallFeedbackPanel, overallMaintenancePanel;
    static JLayeredPane feedbackLayer, maintenanceLayer;
    Color sideBarColour = new Color(170, 230, 255), brighterSideBarColour = new Color(210, 240, 255);
    static CardLayout cardLayout = new CardLayout();

    AdminFrame(double xPosition, double yPosition) {

        frame = new JFrame("Movie Recommendation System - Admin");
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

        systemName = new JLabel("<html>ABC<br>Movie Recommender</html>");
        systemName.setFont(new Font("Advent Pro", Font.BOLD, 20));
        systemName.setBounds(130, 20, 200, 100);

        feedbackLabel = new JLabel("Feedback Summary");
        feedbackLabel.setBounds(40, 0, 390, 60);
        feedbackLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        feedbackLabel.setHorizontalAlignment(JLabel.LEFT);
        feedbackLabel.setVerticalAlignment(JLabel.CENTER);
        feedbackLabel.addMouseListener(this);

        feedbackPanel = new JPanel();
        feedbackPanel.setBounds(0, 0, 390, 60);
        feedbackPanel.setBackground(sideBarColour);

        overallFeedbackPanel = new JLayeredPane();
        overallFeedbackPanel.setBounds(0, 150, 390, 60);
        overallFeedbackPanel.add(feedbackLabel, JLayeredPane.PALETTE_LAYER);
        overallFeedbackPanel.add(feedbackPanel, JLayeredPane.DEFAULT_LAYER);

        maintenanceLabel = new JLabel("System Maintenance");
        maintenanceLabel.setBounds(40, 0, 390, 60);
        maintenanceLabel.setFont(new Font("Avenir", Font.PLAIN, 25));
        maintenanceLabel.setHorizontalAlignment(JLabel.LEFT);
        maintenanceLabel.setVerticalAlignment(JLabel.CENTER);
        maintenanceLabel.addMouseListener(this);

        maintenancePanel = new JPanel();
        maintenancePanel.setBounds(0, 0, 390, 60);
        maintenancePanel.setBackground(Color.WHITE);

        overallMaintenancePanel = new JLayeredPane();
        overallMaintenancePanel.setBounds(0, 210, 390, 60);
        overallMaintenancePanel.add(maintenanceLabel, JLayeredPane.PALETTE_LAYER);
        overallMaintenancePanel.add(maintenancePanel, JLayeredPane.DEFAULT_LAYER);

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

        feedbackLayer = new FeedbackSummaryPage(null);

        maintenanceLayer = new MaintenancePage();

        overallLayer = new JPanel();
        overallLayer.setLayout(cardLayout);
        overallLayer.setBounds(400, 0, 970, 768);
        overallLayer.add(feedbackLayer, "Feedback");
        overallLayer.add(maintenanceLayer, "Maintenance");

        cardLayout.show(overallLayer, "Feedback");

        frame.add(logoPlaceholder);
        frame.add(systemName);
        frame.add(overallMaintenancePanel);
        frame.add(overallFeedbackPanel);

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
        if (e.getSource() == feedbackLabel && feedbackPanel.getBackground() != sideBarColour) {
            feedbackPanel.setBackground(sideBarColour);
            maintenancePanel.setBackground(Color.WHITE);
            cardLayout.show(overallLayer, "Feedback");

        } else if (e.getSource() == maintenanceLabel && maintenancePanel.getBackground() != sideBarColour) {
            maintenancePanel.setBackground(sideBarColour);
            feedbackPanel.setBackground(Color.WHITE);
            cardLayout.show(overallLayer, "Maintenance");

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
                    frame = null;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "System error. Please inspect the system.");
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == feedbackLabel && feedbackPanel.getBackground() != sideBarColour) {
            feedbackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            feedbackPanel.setBackground(brighterSideBarColour);

        } else if (e.getSource() == maintenanceLabel && maintenancePanel.getBackground() != sideBarColour) {
            maintenanceLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            maintenancePanel.setBackground(brighterSideBarColour);


        } else if (e.getSource() == logoutPlaceholder || e.getSource() == logoutLabel) {
            logoutPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (feedbackPanel.getBackground() == brighterSideBarColour) {
            feedbackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            feedbackPanel.setBackground(Color.WHITE);
        }

        if (maintenancePanel.getBackground() == brighterSideBarColour) {
            maintenanceLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            maintenancePanel.setBackground(Color.WHITE);
        }


        logoutPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
