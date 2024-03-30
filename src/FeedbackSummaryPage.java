import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FeedbackSummaryPage implements MouseListener {
    static JFrame frame;
    static JPanel backgroundPanel, rightPanel, rightPanel2, rightPanel3;
    static JLabel mainTitle, feedbackSummaryButton, systemMaintenance, logOut, feedbackSummary, recentFeedback, rightRating, rightRating2, rightRating3, filterImage;
    static JLabel backgroundImage, graphImage, logo,logoutLogo,  rightContent, rightContent2, rightContent3;

    static JLayeredPane layer;

    public FeedbackSummaryPage (){


        frame = new JFrame("Feedback Summary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
        frame.setLayout(null);
        frame.setResizable(false);

        logo = new JLabel();
        logo.setIcon(new ImageIcon("src/LogoAdmin.jpg"));
        logo.setBounds(15,35,65,65);

        logoutLogo = new JLabel();
        logoutLogo.setIcon(new ImageIcon("src/Logoutadmin Logo.png"));
        logoutLogo.setBounds(10,640,65,65);
        logoutLogo.addMouseListener(this);

        mainTitle = new JLabel("<html>ABC Movie<br>Recommender<html>");
        mainTitle.setFont(new Font("Arial",Font.BOLD,20));
        mainTitle.setBounds(80,20,150,100);

        feedbackSummaryButton = new JLabel("Feedback Summary");
        feedbackSummaryButton.setFont(new Font("Arial",Font.PLAIN,20));
        feedbackSummaryButton.setBounds(30,50,200,150);
        feedbackSummaryButton.addMouseListener(this);

        systemMaintenance = new JLabel("System Maintenance");
        systemMaintenance.setFont(new Font("Arial",Font.PLAIN,20));
        systemMaintenance.setBounds(30,110,200,150);
        systemMaintenance.addMouseListener(new MaintenancePage());

        logOut = new JLabel("Logout");
        logOut.setFont(new Font("Arial",Font.BOLD,20));
        logOut.setBounds(70,600,150,150);
        logOut.addMouseListener(this);

        feedbackSummary = new JLabel("Feedback Summary");
        feedbackSummary.setFont(new Font("Arial",Font.BOLD,30));
        feedbackSummary.setBounds(400,0,300,100);

        graphImage = new JLabel();
        graphImage.setIcon(new ImageIcon("src/adminGraph.png"));
        graphImage.setBounds(400,120,400,550);

        filterImage = new JLabel();
        filterImage.setIcon(new ImageIcon("src/filter.png"));
        filterImage.setBounds(1250,20,65,65);
        filterImage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FilterTimePage();
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("src/adminBackground.png"));
        backgroundImage.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        recentFeedback = new JLabel("<html><u style='border-bottom: 2px solid black;'>Recent Feedbacks<u><html>");
        recentFeedback.setFont(new Font("Arial", Font.BOLD, 25));
        recentFeedback.setBounds(900, 90, 300, 100);

        rightRating = new JLabel("Ratings 5 Stars");
        rightRating.setFont(new Font("Arial",Font.BOLD,17));
        rightRating.setBounds(930,160,300,70);

        rightContent = new JLabel("<html>Good system. Everything work as intended<br>without error. Interesting recommendations<br>provided by the system<html>");
        rightContent.setFont(new Font("Arial",Font.PLAIN,17));
        rightContent.setBounds(930,170,500,150);

        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(900,160,400,150);

        rightRating2 = new JLabel("Ratings 2 Stars");
        rightRating2.setFont(new Font("Arial",Font.BOLD,17));
        rightRating2.setBounds(930,330,300,70);

        rightContent2 = new JLabel("<html>Not a very informative system. All the movie<br>recommendations provided are not up-to-date.<html>");
        rightContent2.setFont(new Font("Arial",Font.PLAIN,17));
        rightContent2.setBounds(930,330,500,150);

        rightPanel2 = new JPanel();
        rightPanel2.setBackground(Color.WHITE);
        rightPanel2.setBounds(900,330,400,150);

        rightRating3 = new JLabel("Ratings 4 Stars");
        rightRating3.setFont(new Font("Arial",Font.BOLD,17));
        rightRating3.setBounds(930,500,300,70);

        rightContent3 = new JLabel("<html>Quite a good system, but the system can<br>involve more functionalities so that we can<br>interact more with the system<html>");
        rightContent3.setFont(new Font("Arial",Font.PLAIN,17));
        rightContent3.setBounds(930,510,500,150);

        rightPanel3 = new JPanel();
        rightPanel3.setBackground(Color.WHITE);
        rightPanel3.setBounds(900,500,400,150);


        layer = new JLayeredPane();
        layer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        layer.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
        layer.add(feedbackSummary, JLayeredPane.PALETTE_LAYER);
        layer.add(graphImage, JLayeredPane.PALETTE_LAYER);
        layer.add(rightRating, JLayeredPane.PALETTE_LAYER);
        layer.add(rightContent, JLayeredPane.PALETTE_LAYER);
        layer.add(rightRating2, JLayeredPane.PALETTE_LAYER);
        layer.add(rightContent2, JLayeredPane.PALETTE_LAYER);
        layer.add(rightRating3, JLayeredPane.PALETTE_LAYER);
        layer.add(rightContent3, JLayeredPane.PALETTE_LAYER);
        layer.add(rightPanel, JLayeredPane.PALETTE_LAYER);
        layer.add(rightPanel2, JLayeredPane.PALETTE_LAYER);
        layer.add(rightPanel3, JLayeredPane.PALETTE_LAYER);
        layer.add(recentFeedback, JLayeredPane.PALETTE_LAYER);
        layer.add(filterImage, JLayeredPane.PALETTE_LAYER);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        backgroundPanel.setSize(frame.getWidth()/4,frame.getHeight());

        backgroundPanel.add(mainTitle);
        backgroundPanel.add(feedbackSummaryButton);
        backgroundPanel.add(systemMaintenance);
        backgroundPanel.add(logOut);
        backgroundPanel.add(logo);
        backgroundPanel.add(logoutLogo);


        frame.add(backgroundPanel);
        frame.add(layer);
        frame.setVisible(true);

    }

    public static void main(String[] args){
        new FeedbackSummaryPage();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == feedbackSummaryButton) {
            new FeedbackSummaryPage();
            frame.dispose();
        } else if (e.getSource() == systemMaintenance) {
            new MaintenancePage();
            frame.dispose();
        }

        if (e.getSource() == logOut || e.getSource() == logoutLogo) {
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
        if (e.getSource() == logOut || e.getSource() == logoutLogo) {
            logOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            logoutLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }

    @Override
    public void mouseExited(MouseEvent e)   {
        if (e.getSource() == logOut || e.getSource() == logoutLogo) {
            logOut.setCursor(Cursor.getDefaultCursor());
            logoutLogo.setCursor(Cursor.getDefaultCursor());
        }
    }



    public void setVisible(boolean b) {
    }
}
