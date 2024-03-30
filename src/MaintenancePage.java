import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class MaintenancePage implements KeyListener, MouseListener {
    static JFrame frame;
    static JLabel feedbackSummaryButton, systemMaintenance ,mainTitle, logo, logoutLogo, logOut, datasetManagement, backgroundImage,
            movieDataset, recommendDataset, ratingDataset, movieLabel, recommendLabel, ratingLabel, rightTitle, emailLabel, passwordLabel, rePasswordLabel;
    static JPanel backgroundPanel, linePanel;
    static JTextField emailText, passwordText, rePasswordText;
    static JLayeredPane layer;
    static JButton movieDownload, movieUpdate, recommendDownload, recommendUpdate, ratingDownload, ratingUpdate, resetButton;
    public static void main(String[] args){
        frame = new JFrame("System Maintenance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
        frame.setLayout(null);
        frame.setResizable(false);

        mainTitle = new JLabel("<html>ABC Movie<br>Recommender<html>");
        mainTitle.setFont(new Font("Arial",Font.BOLD,20));
        mainTitle.setBounds(80,20,150,100);

        logo = new JLabel();
        logo.setIcon(new ImageIcon("src/LogoAdmin.jpg"));
        logo.setBounds(15,35,65,65);

        logoutLogo = new JLabel();
        logoutLogo.setIcon(new ImageIcon("src/Logoutadmin Logo.png"));
        logoutLogo.setBounds(10,640,65,65);

        feedbackSummaryButton = new JLabel("Feedback Summary");
        feedbackSummaryButton.setFont(new Font("Arial",Font.PLAIN,20));
        feedbackSummaryButton.setBounds(30,50,200,150);
        feedbackSummaryButton.addMouseListener(new FeedbackSummaryPage());

        systemMaintenance = new JLabel("System Maintenance");
        systemMaintenance.setFont(new Font("Arial",Font.PLAIN,20));
        systemMaintenance.setBounds(30,110,200,150);
        systemMaintenance.addMouseListener(new MaintenancePage());

        logOut = new JLabel("Logout");
        logOut.setFont(new Font("Arial",Font.BOLD,20));
        logOut.setBounds(70,600,150,150);

        datasetManagement = new JLabel("Dataset Management");
        datasetManagement.setFont(new Font("Advent Pro",Font.BOLD,30));
        datasetManagement.setBounds(400,0,350,100);

        movieDataset = new JLabel("<html><u>Movies Dataset<u><html>");
        movieDataset.setFont(new Font("Advent Pro",Font.PLAIN,25));
        movieDataset.setBounds(400,100,350,100);

        movieLabel = new JLabel("Dataset containing all information of movies");
        movieLabel.setFont(new Font("Avenir", Font.PLAIN,17));
        movieLabel.setBounds(400,130,350,100);

        movieDownload = new JButton("Download");
        movieDownload.setFont(new Font("Avenir", Font.PLAIN,27));
        movieDownload.setBounds(400,200,220,80);
        movieDownload.setBackground(Color.WHITE);
        movieDownload.setBorder(new LineBorder(Color.BLACK,2));

        movieUpdate = new JButton("Update");
        movieUpdate.setFont(new Font("Avenir", Font.PLAIN,27));
        movieUpdate.setBounds(650,200,220,80);
        movieUpdate.setBackground(Color.WHITE);
        movieUpdate.setBorder(new LineBorder(Color.BLACK,2));

        recommendDataset = new JLabel("<html><u>Recommendation Dataset<u><html>");
        recommendDataset.setFont(new Font("Advent Pro",Font.PLAIN,25));
        recommendDataset.setBounds(400,280,350,100);

        recommendLabel = new JLabel("Dataset containing RapidMiner's Recommendation for users");
        recommendLabel.setFont(new Font("Avenir", Font.PLAIN,17));
        recommendLabel.setBounds(400,310,500,100);

        recommendDownload = new JButton("Download");
        recommendDownload.setFont(new Font("Avenir", Font.PLAIN,27));
        recommendDownload.setBounds(400,390,220,80);
        recommendDownload.setBackground(Color.WHITE);
        recommendDownload.setBorder(new LineBorder(Color.BLACK,2));

        recommendUpdate = new JButton("Update");
        recommendUpdate.setFont(new Font("Avenir", Font.PLAIN,27));
        recommendUpdate.setBounds(650,390,220,80);
        recommendUpdate.setBackground(Color.WHITE);
        recommendUpdate.setBorder(new LineBorder(Color.BLACK,2));

        ratingDataset = new JLabel("<html><u>Rating Dataset<u><html>");
        ratingDataset.setFont(new Font("Advent Pro",Font.PLAIN,25));
        ratingDataset.setBounds(400,450,350,100);

        ratingLabel = new JLabel("Dataset containing all previous user movie ratings");
        ratingLabel.setFont(new Font("Avenir", Font.PLAIN,17));
        ratingLabel.setBounds(400,480,500,100);

        ratingDownload = new JButton("Download");
        ratingDownload.setFont(new Font("Avenir", Font.PLAIN,27));
        ratingDownload.setBounds(400,560,220,80);
        ratingDownload.setBackground(Color.WHITE);
        ratingDownload.setBorder(new LineBorder(Color.BLACK,2));

        ratingUpdate = new JButton("Update");
        ratingUpdate.setFont(new Font("Avenir", Font.PLAIN,27));
        ratingUpdate.setBounds(650,560,220,80);
        ratingUpdate.setBackground(Color.WHITE);
        ratingUpdate.setBorder(new LineBorder(Color.BLACK,2));

        linePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawLine(950, 50, 950, 700);
            }
        };
        linePanel.setOpaque(false);
        linePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        rightTitle = new JLabel ("Reset Login Details");
        rightTitle.setFont(new Font("Advent Pro", Font.BOLD, 30));
        rightTitle.setBounds(1000,0,300,100);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("src/adminBackground.png"));
        backgroundImage.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Advent Pro", Font.PLAIN,20));
        emailLabel.setBounds(1000,100,300,100);

        emailText = new JTextField();
        emailText.setBorder(new LineBorder(Color.BLACK,2));
        emailText.setBounds(1000,180,300,50);

        passwordLabel = new JLabel("New Password");
        passwordLabel.setFont(new Font("Advent Pro", Font.PLAIN,20));
        passwordLabel.setBounds(1000,250,300,100);

        passwordText = new JTextField();
        passwordText.setBorder(new LineBorder(Color.BLACK,2));
        passwordText.setBounds(1000,330,300,50);

        rePasswordLabel = new JLabel("Retype New Password");
        rePasswordLabel.setFont(new Font("Advent Pro", Font.PLAIN, 20));
        rePasswordLabel.setBounds(1000,400,300,100);

        rePasswordText = new JTextField();
        rePasswordText.setBorder(new LineBorder(Color.BLACK,2));
        rePasswordText.setBounds(1000,480,300,50);

        resetButton = new JButton("Reset Password");
        resetButton.setBorder(new LineBorder(Color.BLACK,2));
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBounds(1000,580,300,70);
        resetButton.setFont(new Font("Avenir",Font.PLAIN,20));

        layer = new JLayeredPane();
        layer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        layer.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
        layer.add(datasetManagement, JLayeredPane.PALETTE_LAYER);
        layer.add(movieDataset, JLayeredPane.PALETTE_LAYER);
        layer.add(movieLabel, JLayeredPane.PALETTE_LAYER);
        layer.add(movieDownload, JLayeredPane.PALETTE_LAYER);
        layer.add(movieUpdate, JLayeredPane.PALETTE_LAYER);
        layer.add(recommendDataset, JLayeredPane.PALETTE_LAYER);
        layer.add(recommendLabel, JLayeredPane.PALETTE_LAYER);
        layer.add(recommendDownload, JLayeredPane.PALETTE_LAYER);
        layer.add(recommendUpdate, JLayeredPane.PALETTE_LAYER);
        layer.add(ratingDataset, JLayeredPane.PALETTE_LAYER);
        layer.add(ratingLabel, JLayeredPane.PALETTE_LAYER);
        layer.add(ratingDownload, JLayeredPane.PALETTE_LAYER);
        layer.add(ratingUpdate, JLayeredPane.PALETTE_LAYER);
        layer.add(linePanel, JLayeredPane.PALETTE_LAYER);
        layer.add(rightTitle, JLayeredPane.PALETTE_LAYER);
        layer.add(emailLabel, JLayeredPane.PALETTE_LAYER);
        layer.add(emailText, JLayeredPane.PALETTE_LAYER);
        layer.add(passwordLabel, JLayeredPane.PALETTE_LAYER);
        layer.add(passwordText, JLayeredPane.PALETTE_LAYER);
        layer.add(rePasswordLabel, JLayeredPane.PALETTE_LAYER);
        layer.add(rePasswordText, JLayeredPane.PALETTE_LAYER);
        layer.add(resetButton, JLayeredPane.PALETTE_LAYER);

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

        feedbackSummaryButton.addMouseListener(new MaintenancePage());
        systemMaintenance.addMouseListener(new MaintenancePage());

        frame.add(backgroundPanel);
        frame.add(layer);
        frame.setVisible(true);
    }

    private void setVisible(boolean b) {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == feedbackSummaryButton) {
            new FeedbackSummaryPage();
            frame.dispose();
        } else if (e.getSource() == systemMaintenance) {
            new MaintenancePage();
            frame.dispose();
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
