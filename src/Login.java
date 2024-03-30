import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Login implements ActionListener, MouseListener {

    static JFrame frame;
    JPanel panelLeft, emailPanel, passwordPanel;
    JButton loginButton;
    JTextField emailInput;
    JPasswordField passwordInput;
    JLabel promptNewUser, promptGuest;
    Action moveCursorToPassword;

    static ArrayList<String> username = new ArrayList<>();
    static ArrayList<String> password = new ArrayList<>();
    static ArrayList<String> userID = new ArrayList<>();
    ArrayList<Admin> adminList;

    Login(int horizontalValue, int verticalValue) throws IOException {

        Admin.readAdminFromFile();
        adminList = Admin.overallAdmin;

        BufferedReader rd = new BufferedReader(new FileReader("textfile/customerAccount.txt"));

        String line;
        while ((line = rd.readLine()) != null) {
            String[] parts = line.split(";");
            userID.add(parts[0].strip());
            username.add(parts[1].strip());
            password.add(parts[2].strip());
        }

        rd.close();

        frame = new JFrame("Movie Recommendation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
        frame.setLayout(null);
        frame.setLocation(horizontalValue, verticalValue);
        frame.setResizable(false);

        panelLeft = new Panel1();
        panelLeft.setBounds(50, 50, 638, 290);

        JLabel titleLabel1 = new JLabel("MOVIE RECOMMENDATION");
        titleLabel1.setBounds(50, 70, 633, 113);
        titleLabel1.setFont(new Font("Advent Pro", Font.BOLD, 50));
        titleLabel1.setHorizontalAlignment(JLabel.CENTER);
        titleLabel1.setVerticalAlignment(JLabel.CENTER);

        JLabel titleLabel2 = new JLabel("SYSTEM");
        titleLabel2.setBounds(50, 130, 633, 113);
        titleLabel2.setFont(new Font("Advent Pro", Font.BOLD, 50));
        titleLabel2.setHorizontalAlignment(JLabel.CENTER);
        titleLabel2.setVerticalAlignment(JLabel.CENTER);

        JLabel titleLabel3 = new JLabel("A simple yet accurate solution for everyone.");
        titleLabel3.setBounds(50, 215, 633, 113);
        titleLabel3.setFont(new Font("Advent Pro", Font.PLAIN, 30));
        titleLabel3.setHorizontalAlignment(JLabel.CENTER);
        titleLabel3.setVerticalAlignment(JLabel.CENTER);

        JLabel picture = new JLabel();
        ImageIcon clappingBoard = new ImageIcon("asset/Login Background.jpg");
        picture.setIcon(clappingBoard);
        picture.setOpaque(true);
        picture.setBounds(-300, 0, 1050, 768);

        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(250, 250, 250, 230));
        panelLogin.setBounds(750, 0, 616, 768);

        JLabel loginTitle = new JLabel("Login");
        loginTitle.setBounds(805, 50, 500, 80);
        loginTitle.setFont(new Font("Advent Pro", Font.BOLD, 70));
        loginTitle.setHorizontalAlignment(JLabel.LEFT);
        loginTitle.setVerticalAlignment(JLabel.CENTER);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(805, 160, 500, 50);
        emailLabel.setFont(new Font("Avenir", Font.PLAIN, 30));
        emailLabel.setHorizontalAlignment(JLabel.LEFT);
        emailLabel.setVerticalAlignment(JLabel.CENTER);

        emailPanel = new Panel3();
        emailPanel.setBounds(800, 210, 500, 80);
        emailPanel.setBackground(new Color(250, 250, 250));

        emailInput = new JTextField();
        emailInput.setBounds(825, 220, 450, 60);
        emailInput.setBorder(null);
        emailInput.setFont(new Font("Avenir", Font.PLAIN, 24));
        moveCursorToPassword = new MoveCursorToPassword();
        emailInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "nextField");
        emailInput.getActionMap().put("nextField", moveCursorToPassword);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(805, 310, 500, 50);
        passwordLabel.setFont(new Font("Avenir", Font.PLAIN, 30));
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        passwordLabel.setVerticalAlignment(JLabel.CENTER);


        passwordPanel = new Panel4();
        passwordPanel.setBounds(800, 370, 500, 80);
        passwordPanel.setBackground(new Color(250, 250, 250));

        passwordInput = new JPasswordField();
        passwordInput.setBounds(825, 380, 450, 60);
        passwordInput.setFont(new Font("Avenir", Font.PLAIN, 25));
        passwordInput.setBorder(null);
        passwordInput.setEchoChar('●');
        passwordInput.addActionListener(this);

        loginButton = new JButton("Login");
        loginButton.setBounds(805, 490, 490, 80);
        loginButton.setFont(new Font("Avenir", Font.PLAIN, 30));
        loginButton.setFocusable(false);
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.addMouseListener(this);
        loginButton.addActionListener(this);

        promptNewUser = new JLabel("First time using our system? Click here to sign up.");
        promptNewUser.setBounds(805, 600, 460, 40);
        promptNewUser.setFont(new Font("Avenir", Font.PLAIN, 20));
        promptNewUser.setHorizontalAlignment(JLabel.LEFT);
        promptNewUser.setVerticalAlignment(JLabel.CENTER);
        promptNewUser.addMouseListener(this);

        promptGuest = new JLabel("Wish to take a look only? Proceed as guest here.");
        promptGuest.setBounds(805, 640, 460, 40);
        promptGuest.setFont(new Font("Avenir", Font.PLAIN, 20));
        promptGuest.setHorizontalAlignment(JLabel.LEFT);
        promptGuest.setVerticalAlignment(JLabel.CENTER);
        promptGuest.addMouseListener(this);

        frame.add(titleLabel1);
        frame.add(titleLabel2);
        frame.add(titleLabel3);
        frame.add(loginTitle);
        frame.add(emailLabel);
        frame.add(emailInput);
        frame.add(passwordLabel);
        frame.add(passwordInput);
        frame.add(loginButton);
        frame.add(promptGuest);
        frame.add(promptNewUser);
        frame.add(panelLeft);
        frame.add(emailPanel);
        frame.add(passwordPanel);
        frame.add(panelLogin);
        frame.add(picture);

        frame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == passwordInput) {
            loginButton.doClick();
        }
        if (e.getSource() == loginButton) {
            String inputtedEmail = emailInput.getText();
            char[] passwordList = passwordInput.getPassword();

            StringBuilder inputtedPassword = new StringBuilder();
            for (char passwordChar: passwordList) {
                inputtedPassword.append(passwordChar);
            }

            boolean nextPage = false;
            for (int i = 0; i < userID.size(); i ++) {
                if ((username.get(i).equals(inputtedEmail))  && (password.get(i).equals(String.valueOf(inputtedPassword)))) {
                    nextPage = true;
                    new UserFrame(userID.get(i), frame.getX(), frame.getY(), "English");
                    frame.dispose();
                }
            }

            for (Admin admin : adminList) {
                if (admin.adminEmail.equals(inputtedEmail) && admin.adminPassword.contentEquals(inputtedPassword)) {
                    nextPage = true;
                    new AdminFrame(frame.getX(), frame.getY());
                    frame.dispose();
                }
            }

            if (!nextPage) {
                JOptionPane.showMessageDialog(frame, "Wrong email or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                passwordInput.setText("");
                emailInput.requestFocusInWindow();
                emailInput.setCaretPosition(0);
            }
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
        if (e.getSource() == promptNewUser) {
            try {
                new SignUp(frame.getX(), frame.getY(), "Login");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "The file cannot be retrieved. Please inspect for any mistakes.");
            }
            frame.setVisible(false);
        } else if (e.getSource() == promptGuest) {
            ImageIcon guestIcon = new ImageIcon("asset/Guest Icon.png");
            Image tempImage = guestIcon.getImage();
            Image newTempImage = tempImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            guestIcon = new ImageIcon(newTempImage);
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to proceed as guest?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, guestIcon);
            if (choice == JOptionPane.OK_OPTION) {
                new GuestFrame(frame.getX(), frame.getY());
                frame.dispose();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        promptNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        promptGuest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        promptNewUser.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        promptGuest.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public class MoveCursorToPassword extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            passwordInput.requestFocusInWindow();
            passwordInput.setCaretPosition(0);
        }
    }
}

class Panel1 extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(300, 185, 333, 185);
        g2d.drawLine(730, 50, 630, 688);
        g2d.drawRect(10, 10, 618, 270);
    }
}

class Panel3 extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, 490, 70, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(5, 5, 490, 70, 10, 10);
    }
}

class Panel4 extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, 490, 70, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(5, 5, 490, 70, 10, 10);
    }
}



