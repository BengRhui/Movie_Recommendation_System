import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SignUp implements ActionListener, MouseListener, KeyListener  {
    JFrame frame;
    JPanel holder, shadow, emailPanel, passwordPanel;
    JLabel signUp, emailPlaceholder, passwordPlaceholder, arrowPlaceholder, picturePlaceholder, emailLabel, passwordLabel;
    JTextField emailInput;
    JPasswordField passwordInput;
    JButton signUpButton;
    Action moveCursorToPassword;
    ArrayList<String> account = new ArrayList<>();
    ArrayList<String> password = new ArrayList<>();
    ArrayList<Integer> userID = new ArrayList<>();
    static String lastFrame, currentLanguage = "English";
    SignUp(double frameHorizontal, double frameVertical, String previousFrame) throws IOException {

        if (GuestFrame.frame != null) {
            currentLanguage = GuestFrame.currentLanguage;
        }

        BufferedReader rd = new BufferedReader(new FileReader("textfile/account.txt"));

        String line;
        while ((line = rd.readLine()) != null) {
            String[] splitLines = line.split(";");
            userID.add(Integer.parseInt(splitLines[0].strip()));
            account.add(splitLines[1].strip());
            password.add(splitLines[2].strip());
        }

        rd.close();

        lastFrame = previousFrame;
        frame = new JFrame("Movie Recommendation System");
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

        signUp = new JLabel("Sign Up");
        signUp.setBounds(200, 75, 300, 100);
        signUp.setFont(new Font("Advent Pro", Font.BOLD, 60));

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(200, 190, 450, 50);
        emailLabel.setFont(new Font("Avenir", Font.PLAIN, 30));
        emailLabel.setHorizontalAlignment(JLabel.LEFT);
        emailLabel.setVerticalAlignment(JLabel.CENTER);

        emailPanel = new NewEmailPanel();
        emailPanel.setBounds(200, 250, 500, 80);
        emailPanel.setBackground(new Color(255, 255, 255));

        emailInput = new JTextField();
        emailInput.setBounds(225, 260, 450, 60);
        emailInput.setBorder(null);
        emailInput.setFont(new Font("Avenir", Font.PLAIN, 24));
        emailInput.setBackground(new Color(255,255,255,0));
        moveCursorToPassword = new MoveCursorToPassword();
        emailInput.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "nextField");
        emailInput.getActionMap().put("nextField", moveCursorToPassword);
        emailInput.addKeyListener(this);

        emailPlaceholder = new JLabel("Example: abc123@mail.com");
        emailPlaceholder.setFont(new Font("Avenir", Font.ITALIC, 20));
        emailPlaceholder.setForeground(Color.GRAY);
        emailPlaceholder.setBounds(225, 260, 450, 60);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(200, 340, 450, 80);
        passwordLabel.setFont(new Font("Avenir", Font.PLAIN, 30));
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        passwordLabel.setVerticalAlignment(JLabel.CENTER);

        passwordPanel = new NewPasswordPanel();
        passwordPanel.setBounds(200, 410, 500, 80);
        passwordPanel.setBackground(new Color(255, 255, 255));

        passwordInput = new JPasswordField();
        passwordInput.setBounds(225, 420, 450, 60);
        passwordInput.setFont(new Font("Avenir", Font.PLAIN, 25));
        passwordInput.setBackground(new Color(255,255,255,0));
        passwordInput.setBorder(null);
        passwordInput.setEchoChar('●');
        passwordInput.addActionListener(this);
        passwordInput.addKeyListener(this);

        passwordPlaceholder = new JLabel("Password must be between 4 to 20 characters.");
        passwordPlaceholder.setFont(new Font("Avenir", Font.ITALIC, 20));
        passwordPlaceholder.setForeground(Color.GRAY);
        passwordPlaceholder.setBounds(225, 420, 450, 60);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(200, 540, 500, 80);
        signUpButton.setFont(new Font("Avenir", Font.PLAIN, 30));
        signUpButton.setFocusable(false);
        signUpButton.setBackground(Color.DARK_GRAY);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setOpaque(true);
        signUpButton.setBorderPainted(false);
        signUpButton.addMouseListener(this);
        signUpButton.addActionListener(this);

        ImageIcon arrowImage = new ImageIcon("asset/Back Arrow.png");
        arrowPlaceholder = new JLabel();
        arrowPlaceholder.setBackground(Color.WHITE);
        arrowPlaceholder.setIcon(arrowImage);
        arrowPlaceholder.setBounds(100, 80, 80, 80);
        arrowPlaceholder.setOpaque(true);
        arrowPlaceholder.addMouseListener(this);

        ImageIcon signUpPicture = new ImageIcon("asset/Sign Up Picture.png");
        picturePlaceholder = new JLabel();
        picturePlaceholder.setBackground(Color.WHITE);
        picturePlaceholder.setIcon(signUpPicture);
        picturePlaceholder.setBounds(710, 80, 600, 600);
        picturePlaceholder.setOpaque(true);

        if (currentLanguage.equals("English")) {
            frame.setTitle("Movie Recommendation System");
            signUp.setText("Sign Up");
            emailLabel.setText("Email");
            emailPlaceholder.setText("Example: abc123@mail.com");
            passwordLabel.setText("Password");
            passwordPlaceholder.setText("Password must be between 4 to 20 characters.");
            signUpButton.setText("Sign Up");
        } else if (currentLanguage.equals("Malay")) {
            frame.setTitle("Sistem Cadangan Filem");
            signUp.setText("Daftar Akaun");
            emailLabel.setText("Emel");
            emailPlaceholder.setText("Contoh: abc123@mail.com");
            passwordLabel.setText("Kata Laluan");
            passwordPlaceholder.setText("Kata laluan harus mempunyai 4 hingga 20 aksara.");
            signUpButton.setText("Daftar");
        }

        frame.add(arrowPlaceholder);
        frame.add(picturePlaceholder);
        frame.add(emailLabel);
        frame.add(emailInput);
        frame.add(emailPlaceholder);
        frame.add(emailPanel);
        frame.add(passwordLabel);
        frame.add(passwordInput);
        frame.add(passwordPlaceholder);
        frame.add(passwordPanel);
        frame.add(signUpButton);
        frame.add(signUp);
        frame.add(holder);
        frame.add(shadow);
        frame.add(background);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == passwordInput) {
            signUpButton.doClick();
        } else if (e.getSource() == signUpButton) {
            boolean validation = true;
            char[] passwordChar = passwordInput.getPassword();
            StringBuilder passwordText = new StringBuilder();
            for (char characters: passwordChar) {
                passwordText.append(characters);
            }
            if (emailInput.getText().isEmpty() || passwordText.isEmpty()) {
                validation = false;
                if (currentLanguage.equals("English")) {
                    JOptionPane.showMessageDialog(frame, "Please fill in your email and password to proceed.", "Note", JOptionPane.ERROR_MESSAGE);
                } else if (currentLanguage.equals("Malay")) {
                    JOptionPane.showMessageDialog(frame, "Sila isikan emel dan kata laluan untuk meneruskan proses.", "Nota", JOptionPane.ERROR_MESSAGE);
                }
            } else if (!emailInput.getText().contains("@") || !emailInput.getText().contains(".")) {
                validation = false;
                if (currentLanguage.equals("English")) {
                    JOptionPane.showMessageDialog(frame, "Please provide a valid email address.", "Note", JOptionPane.ERROR_MESSAGE);
                } else if (currentLanguage.equals("Malay")) {
                    JOptionPane.showMessageDialog(frame, "Sila isikan emel yang sah.", "Nota", JOptionPane.ERROR_MESSAGE);
                }
            } else if ((passwordText.toString().length() < 4) || (passwordText.toString().length() > 20)) {
                validation = false;
                if (currentLanguage.equals("English")) {
                    JOptionPane.showMessageDialog(frame, "Invalid password. Please try another password.", "Note", JOptionPane.ERROR_MESSAGE);
                } else if (currentLanguage.equals("Malay")) {
                    JOptionPane.showMessageDialog(frame, "Kata laluan tidak sah. Sila cuba lagi.", "Nota", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                for (String x : account) {
                    if (x.equals(emailInput.getText())) {
                        validation = false;
                        if (currentLanguage.equals("English")) {
                            JOptionPane.showMessageDialog(frame, "The email has already been used. Please use another email.", "Note", JOptionPane.ERROR_MESSAGE);
                        } else if (currentLanguage.equals("Malay")) {
                            JOptionPane.showMessageDialog(frame, "Emel telah diguna. Sila gunakan emel yang lain.", "Nota", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

            if (validation) {
                account.add(emailInput.getText());
                char[] passwordCharacter = passwordInput.getPassword();
                StringBuilder passwordText2 = new StringBuilder();
                for (char letter: passwordCharacter) {
                    passwordText2.append(letter);
                }
                password.add(passwordText2.toString());
                userID.add(Collections.max(userID) + 1);

                try (BufferedWriter wr = new BufferedWriter(new FileWriter("textfile/account.txt"))) {
                    for (int i = 0; i < userID.size(); i ++) {
                        String lineToInsert = String.format("%-10s%-40s%s", (userID.get(i).toString() + ";"), (account.get(i) + ";"), password.get(i));
                        wr.write(lineToInsert);
                        wr.newLine();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "The file cannot be retrieved. Please inspect for any mistakes.");
                }
                if (currentLanguage.equals("English")) {
                    JOptionPane.showMessageDialog(frame, "You have successfully registered to our system. You will be redirected to the login page.", "Note", JOptionPane.INFORMATION_MESSAGE);
                } else if (currentLanguage.equals("Malay")) {
                    JOptionPane.showMessageDialog(frame, "Anda telah berjaya didaftarkan ke sistem. Anda akan dipautkan ke halaman log masuk.", "Nota", JOptionPane.INFORMATION_MESSAGE);
                }
                try {
                    new Login(frame.getX(), frame.getY());
                    frame.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "The file cannot be retrieved. Please inspect for any mistakes.");
                }
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
        if (e.getSource() == arrowPlaceholder) {
            if (lastFrame.equals("Login")) {
                Login.frame.setVisible(true);
                frame.dispose();
            } else if (lastFrame.equals("Guest")) {
                GuestFrame.frame.setVisible(true);
                frame.dispose();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        arrowPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        arrowPlaceholder.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        emailPlaceholder.setVisible(emailInput.getText().isEmpty());
        passwordPlaceholder.setVisible(passwordInput.getPassword().length == 0);
    }

    public class MoveCursorToPassword extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            passwordInput.requestFocusInWindow();
            passwordInput.setCaretPosition(0);
        }
    }
}


class NewEmailPanel extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, 485, 70, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(5, 5, 485, 70, 10, 10);
    }
}

class NewPasswordPanel extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, 485, 70, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(5, 5, 485, 70, 10, 10);
    }
}
