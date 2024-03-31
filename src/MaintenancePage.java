import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.InputMismatchException;

public class MaintenancePage extends JLayeredPane implements MouseListener, ActionListener {
    static JLabel datasetManagement, backgroundImage, movieDataset, recommendDataset, ratingDataset, movieLabel,
            recommendLabel, ratingLabel, rightTitle, emailLabel, passwordLabel, rePasswordLabel, passwordHint;
    static JPanel linePanel;
    static JTextField emailText;
    static JPasswordField passwordText, rePasswordText;
    static JButton movieDownload, movieUpdate, recommendDownload, recommendUpdate, ratingDownload, ratingUpdate, resetButton;

    public MaintenancePage(){

        this.setSize(970, 768);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("asset/Admin Background.png"));
        backgroundImage.setBounds(0, 0, 970, 768);

        datasetManagement = new JLabel("Dataset Management");
        datasetManagement.setFont(new Font("Advent Pro", Font.BOLD, 40));
        datasetManagement.setBounds(50, 30, 400, 100);

        movieDataset = new JLabel("<html><u>Movies Dataset</u></html>");
        movieDataset.setFont(new Font("Advent Pro",Font.PLAIN,25));
        movieDataset.setBounds(50,150,400,30);

        movieLabel = new JLabel("Dataset containing all information of movies");
        movieLabel.setFont(new Font("Avenir", Font.PLAIN,18));
        movieLabel.setBounds(50,190,400,30);

        movieDownload = new JButton("Download");
        movieDownload.setFont(new Font("Avenir", Font.PLAIN,20));
        movieDownload.setBounds(50,230,200,50);
        movieDownload.setBackground(Color.WHITE);
        movieDownload.setOpaque(true);
        movieDownload.setFocusable(false);
        movieDownload.setBorder(new LineBorder(Color.BLACK,2));
        movieDownload.addMouseListener(this);
        movieDownload.addActionListener(this);

        movieUpdate = new JButton("Update");
        movieUpdate.setFont(new Font("Avenir", Font.PLAIN,20));
        movieUpdate.setBounds(270,230,200,50);
        movieUpdate.setBackground(Color.WHITE);
        movieUpdate.setOpaque(true);
        movieUpdate.setFocusable(false);
        movieUpdate.setBorder(new LineBorder(Color.BLACK,2));
        movieUpdate.addMouseListener(this);
        movieUpdate.addActionListener(this);

        recommendDataset = new JLabel("<html><u>Recommendation Dataset<u><html>");
        recommendDataset.setFont(new Font("Advent Pro",Font.PLAIN,25));
        recommendDataset.setBounds(50,340,400,30);

        recommendLabel = new JLabel("Dataset containing RapidMiner's recommendation");
        recommendLabel.setFont(new Font("Avenir", Font.PLAIN,18));
        recommendLabel.setBounds(50,380,420,30);

        recommendDownload = new JButton("Download");
        recommendDownload.setFont(new Font("Avenir", Font.PLAIN,20));
        recommendDownload.setBounds(50,420,200,50);
        recommendDownload.setBackground(Color.WHITE);
        recommendDownload.setOpaque(true);
        recommendDownload.setFocusable(false);
        recommendDownload.setBorder(new LineBorder(Color.BLACK,2));
        recommendDownload.addMouseListener(this);
        recommendDownload.addActionListener(this);

        recommendUpdate = new JButton("Update");
        recommendUpdate.setFont(new Font("Avenir", Font.PLAIN,20));
        recommendUpdate.setBounds(270,420,200,50);
        recommendUpdate.setBackground(Color.WHITE);
        recommendUpdate.setOpaque(true);
        recommendUpdate.setFocusable(false);
        recommendUpdate.setBorder(new LineBorder(Color.BLACK,2));
        recommendUpdate.addMouseListener(this);
        recommendUpdate.addActionListener(this);

        ratingDataset = new JLabel("<html><u>Rating Dataset<u><html>");
        ratingDataset.setFont(new Font("Advent Pro",Font.PLAIN,25));
        ratingDataset.setBounds(50,530,400,30);

        ratingLabel = new JLabel("Dataset containing all previous user movie ratings");
        ratingLabel.setFont(new Font("Avenir", Font.PLAIN,18));
        ratingLabel.setBounds(50,570,400,30);

        ratingDownload = new JButton("Download");
        ratingDownload.setFont(new Font("Avenir", Font.PLAIN,20));
        ratingDownload.setBounds(50,610,200,50);
        ratingDownload.setBackground(Color.WHITE);
        ratingDownload.setOpaque(true);
        ratingDownload.setFocusable(false);
        ratingDownload.setBorder(new LineBorder(Color.BLACK,2));
        ratingDownload.addMouseListener(this);
        ratingDownload.addActionListener(this);

        ratingUpdate = new JButton("Update");
        ratingUpdate.setFont(new Font("Avenir", Font.PLAIN,20));
        ratingUpdate.setBounds(270,610,200,50);
        ratingUpdate.setBackground(Color.WHITE);
        ratingUpdate.setOpaque(true);
        ratingUpdate.setFocusable(false);
        ratingUpdate.setBorder(new LineBorder(Color.BLACK,2));
        ratingUpdate.addMouseListener(this);
        ratingUpdate.addActionListener(this);

        linePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(0, 0, 0, 800);
            }
        };
        linePanel.setOpaque(false);
        linePanel.setBounds(510, 0, 2, 800);

        rightTitle = new JLabel ("Reset Login Details");
        rightTitle.setFont(new Font("Advent Pro", Font.BOLD, 40));
        rightTitle.setBounds(560, 30, 400, 100);

        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Advent Pro", Font.PLAIN,25));
        emailLabel.setBounds(560,140,400,30);

        emailText = new JTextField();
        emailText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK,2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        emailText.setBounds(560,190,330,55);
        emailText.setFont(new Font("Avenir", Font.PLAIN, 18));

        passwordLabel = new JLabel("New Password");
        passwordLabel.setFont(new Font("Advent Pro", Font.PLAIN,25));
        passwordLabel.setBounds(560,280,400,30);

        passwordText = new JPasswordField();
        passwordText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK,2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        passwordText.setBounds(560,330,330,55);
        passwordText.setFont(new Font("Avenir", Font.PLAIN, 18));

        rePasswordLabel = new JLabel("Retype New Password");
        rePasswordLabel.setFont(new Font("Advent Pro", Font.PLAIN, 25));
        rePasswordLabel.setBounds(560,420,400,30);

        rePasswordText = new JPasswordField();
        rePasswordText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK,2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        rePasswordText.setBounds(560,470,330,55);
        rePasswordText.setFont(new Font("Avenir", Font.PLAIN, 18));

        passwordHint = new JLabel("<html>Note: Password must be between 4 to 20 characters.</html>");
        passwordHint.setFont(new Font("Avenir", Font.PLAIN, 18));
        passwordHint.setBounds(560, 540, 330, 60);

        resetButton = new JButton("Reset Password");
        resetButton.setBorder(new LineBorder(Color.BLACK,2));
        resetButton.setFont(new Font("Avenir", Font.PLAIN, 20));
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBorderPainted(false);
        resetButton.setOpaque(true);
        resetButton.setFocusable(false);
        resetButton.setBounds(560,620,330,55);
        resetButton.addMouseListener(this);
        resetButton.addActionListener(this);


        this.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
        this.add(datasetManagement, JLayeredPane.PALETTE_LAYER);
        this.add(movieDataset, JLayeredPane.PALETTE_LAYER);
        this.add(movieLabel, JLayeredPane.PALETTE_LAYER);
        this.add(movieDownload, JLayeredPane.PALETTE_LAYER);
        this.add(movieUpdate, JLayeredPane.PALETTE_LAYER);
        this.add(recommendDataset, JLayeredPane.PALETTE_LAYER);
        this.add(recommendLabel, JLayeredPane.PALETTE_LAYER);
        this.add(recommendDownload, JLayeredPane.PALETTE_LAYER);
        this.add(recommendUpdate, JLayeredPane.PALETTE_LAYER);
        this.add(ratingDataset, JLayeredPane.PALETTE_LAYER);
        this.add(ratingLabel, JLayeredPane.PALETTE_LAYER);
        this.add(ratingDownload, JLayeredPane.PALETTE_LAYER);
        this.add(ratingUpdate, JLayeredPane.PALETTE_LAYER);
        this.add(linePanel, JLayeredPane.PALETTE_LAYER);
        this.add(rightTitle, JLayeredPane.PALETTE_LAYER);
        this.add(emailLabel, JLayeredPane.PALETTE_LAYER);
        this.add(emailText, JLayeredPane.PALETTE_LAYER);
        this.add(passwordLabel, JLayeredPane.PALETTE_LAYER);
        this.add(passwordText, JLayeredPane.PALETTE_LAYER);
        this.add(rePasswordLabel, JLayeredPane.PALETTE_LAYER);
        this.add(rePasswordText, JLayeredPane.PALETTE_LAYER);
        this.add(passwordHint, JLayeredPane.PALETTE_LAYER);
        this.add(resetButton, JLayeredPane.PALETTE_LAYER);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        movieDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        movieUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        recommendDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        recommendUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ratingDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ratingUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        movieDownload.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        movieUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        recommendDownload.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        recommendUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        ratingDownload.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        ratingUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            try {
                String inputEmail = emailText.getText().strip().toLowerCase();
                Login.userID.clear();
                Login.username.clear();
                Login.password.clear();

                BufferedReader read = new BufferedReader(new FileReader("textfile/customerAccount.txt"));
                String line;
                while ((line = read.readLine()) != null) {
                    String[] array = line.split(";");
                    for (int i = 0; i < array.length; i ++) {
                        array[i] = array[i].strip();
                    }
                    Login.userID.add(array[0]);
                    Login.username.add(array[1]);
                    Login.password.add(array[2]);
                }

                int customerIndex = -1;
                for (int i = 0; i < Login.userID.size(); i ++) {
                    if (inputEmail.equals(Login.username.get(i))) {
                        customerIndex = i;
                        break;
                    }
                }

                boolean adminFound = false;
                if (customerIndex == -1) {
                    Admin.readAdminFromFile();
                    for (Admin admin : Admin.overallAdmin) {
                        if (admin.adminEmail.equals(inputEmail)) {
                            adminFound = true;
                            break;
                        }
                    }
                }

                if (customerIndex == -1 && !adminFound) {
                    throw new IOException();
                }

                StringBuilder password = new StringBuilder();
                StringBuilder retypePassword = new StringBuilder();

                if (passwordText.getPassword().length < 4 || passwordText.getPassword().length > 20) {
                    throw new NumberFormatException();
                }

                for (char character: passwordText.getPassword()) {
                    password.append(character);
                }

                for (char character: rePasswordText.getPassword()) {
                    retypePassword.append(character);
                }

                if (password.compareTo(retypePassword) != 0) {
                    throw new InputMismatchException();
                }

                if (customerIndex != -1) {
                    Login.password.set(customerIndex, password.toString());
                    BufferedWriter write = new BufferedWriter(new FileWriter("textfile/customerAccount.txt"));
                    for (int i = 0; i < Login.userID.size(); i ++) {
                        write.write(Login.userID.get(i) + ";" + Login.username.get(i) + ";" + Login.password.get(i));
                        write.newLine();
                    }
                    write.close();
                    JOptionPane.showMessageDialog(AdminFrame.frame, "Customer password updated successful.", "Reset Successful", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                } else {
                    for (Admin admin: Admin.overallAdmin) {
                        if (admin.adminEmail.equals(inputEmail)) {
                            admin.adminPassword = password.toString();
                        }
                    }
                    Admin.writeToAdminFile();
                    JOptionPane.showMessageDialog(AdminFrame.frame, "Admin password updated successful.", "Reset Successful", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(AdminFrame.frame, "Email is not found in the system", "Error Email", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AdminFrame.frame, "Password does not fulfill requirement. Please try again.", "Password Error", JOptionPane.ERROR_MESSAGE);
            } catch (InputMismatchException ex) {
                JOptionPane.showMessageDialog(AdminFrame.frame, "Password does not match the retyped password. Please try again.", "Password Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == movieDownload) {
            DatasetDownloadAndUpload.downloadFiles("Movies.csv");
            JOptionPane.showMessageDialog(AdminFrame.frame, "<html> Movies.csv downloaded successful.<br>" +
                    "Do note that the file consists of the following attributes:<br>" +
                    " - Movie ID<br>" +
                    " - Movie Name<br>" +
                    " - Released Year<br>" +
                    " - Movie ID in IMDB<br>" +
                    " - Movie ID in TMDB<br>" +
                    " - Movie Genres (up to 5 genres: null is represented with a dash '-')", "Download Successful",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));

        } else if (e.getSource() == movieUpdate) {
            JOptionPane.showMessageDialog(AdminFrame.frame, "<html> Make sure your file is in .CSV format.<br>" +
                            "Make sure that the file consists of the following attributes:<br>" +
                            " - Movie ID<br>" +
                            " - Movie Name<br>" +
                            " - Released Year<br>" +
                            " - Movie ID in IMDB<br>" +
                            " - Movie ID in TMDB<br>" +
                            " - Movie Genres (up to 5 genres: null is represented with a dash '-')", "Upload Reminder",
                    JOptionPane.WARNING_MESSAGE);

            DatasetDownloadAndUpload.uploadFiles("Movies.csv");

        } else if (e.getSource() == recommendDownload) {
            DatasetDownloadAndUpload.downloadFiles("recommendation.txt");
            JOptionPane.showMessageDialog(AdminFrame.frame, "<html> recommendation.txt downloaded successful.<br>" +
                            "Do note that the file consists of the following attributes:<br>" +
                            " - User ID<br>" +
                            " - Movie ID<br>" +
                            " - Rankings", "Download Successful",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));

        } else if (e.getSource() == recommendUpdate) {

            JOptionPane.showMessageDialog(AdminFrame.frame, "<html>Make sure your file is in .TXT format.<br>" +
                            "Make sure that the file consists of the following attributes:<br>" +
                            " - User ID<br>" +
                            " - Movie ID<br>" +
                            " - Rankings", "Upload Reminder",
                    JOptionPane.WARNING_MESSAGE);

            DatasetDownloadAndUpload.uploadFiles("recommendation.txt");

        } else if (e.getSource() == ratingDownload) {
            DatasetDownloadAndUpload.downloadFiles("ratings.csv");
            JOptionPane.showMessageDialog(AdminFrame.frame, "<html> ratings.csv downloaded successful.<br>" +
                            "Do note that the file consists of the following attributes:<br>" +
                            " - User ID<br>" +
                            " - Movie ID<br>" +
                            " - Ratings<br>" +
                            " - Timestamp (in epoch)", "Download Successful",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));

        } else if (e.getSource() == ratingUpdate) {

            JOptionPane.showMessageDialog(AdminFrame.frame, "<html>Make sure your file is in .CSV format.<br>" +
                            "Make sure that the file consists of the following attributes:<br>" +
                            " - User ID<br>" +
                            " - Movie ID<br>" +
                            " - Ratings<br>" +
                            " - Timestamp (in epoch)", "Upload Reminder",
                    JOptionPane.WARNING_MESSAGE);

            DatasetDownloadAndUpload.uploadFiles("ratings.csv");

        }
    }
}
