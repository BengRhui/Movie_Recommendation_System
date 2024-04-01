import javax.management.AttributeValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Instant;

public class ProvideRatingPage implements ActionListener, KeyListener, MouseListener {
    JFrame frame;
    JLabel title, subtitle, scalePrompt, feedbackPrompt;
    JTextArea feedbackInput;
    JRadioButton button1, button2, button3, button4, button5;
    ButtonGroup group;
    JPanel buttonPanel, background;
    JButton cancelButton, saveButton;
    static String currentLanguage;

    public ProvideRatingPage() {

        currentLanguage = UserFrame.currentLanguage;

        frame = new JFrame("Provide Ratings");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(750, 620);
        frame.setLocation(UserFrame.frame.getX() + (UserFrame.frame.getWidth() - frame.getWidth()) / 2, UserFrame.frame.getY() + (UserFrame.frame.getHeight() - frame.getHeight()) / 2);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                UserFrame.frame.setEnabled(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        background = new JPanel();
        background.setBackground(Color.WHITE);
        background.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        title = new JLabel("Feedback");
        title.setFont(new Font("Advent Pro", Font.BOLD, 40));
        title.setBounds(60, 40, 400, 50);

        subtitle = new JLabel("<html>We would like to hear from you.<br>Please assist us by filling in the following information.</html>");
        subtitle.setFont(new Font("Avenir", Font.PLAIN, 20));
        subtitle.setBounds(title.getX(), title.getY() + title.getHeight() + 20, 800, 50);

        scalePrompt = new JLabel("Overall rating for our system: ");
        scalePrompt.setFont(new Font("Avenir", Font.PLAIN, 20));
        scalePrompt.setBounds(subtitle.getX(), subtitle.getY() + subtitle.getHeight() + 20, 350, 50);

        button1 = new JRadioButton("1");
        button1.setFont(new Font("Avenir", Font.PLAIN, 20));
        button1.setBackground(Color.WHITE);

        button2 = new JRadioButton("2");
        button2.setFont(new Font("Avenir", Font.PLAIN, 20));
        button2.setBackground(Color.WHITE);

        button3 = new JRadioButton("3");
        button3.setFont(new Font("Avenir", Font.PLAIN, 20));
        button3.setBackground(Color.WHITE);

        button4 = new JRadioButton("4");
        button4.setFont(new Font("Avenir", Font.PLAIN, 20));
        button4.setBackground(Color.WHITE);

        button5 = new JRadioButton("5");
        button5.setFont(new Font("Avenir", Font.PLAIN, 20));
        button5.setBackground(Color.WHITE);

        group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);
        group.add(button5);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(scalePrompt.getX() + scalePrompt.getWidth(), scalePrompt.getY() + 4, 350, 50);

        feedbackPrompt = new JLabel("Feedbacks / Comments of the system: ");
        feedbackPrompt.setFont(new Font("Avenir", Font.PLAIN, 20));
        feedbackPrompt.setBounds(scalePrompt.getX(), scalePrompt.getY() + scalePrompt.getHeight() + 10, 360, 50);

        feedbackInput = new JTextArea(3, 50);
        feedbackInput.setBounds(feedbackPrompt.getX(), feedbackPrompt.getY() + feedbackPrompt.getHeight() + 10, 650, 110);
        feedbackInput.setFont(new Font("Avenir", Font.PLAIN, 20));
        feedbackInput.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        feedbackInput.setLineWrap(true);
        feedbackInput.setWrapStyleWord(true);
        feedbackInput.addKeyListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setSize(200, 50);
        cancelButton.setFont(new Font("Avenir", Font.PLAIN, 20));
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cancelButton.setOpaque(true);
        cancelButton.setFocusable(false);
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setLocation(280, 440);
        cancelButton.addActionListener(this);
        cancelButton.addMouseListener(this);

        saveButton = new JButton("Save");
        saveButton.setSize(200, 50);
        saveButton.setFont(new Font("Avenir", Font.PLAIN, 20));
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(false);
        saveButton.setFocusable(false);
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);
        saveButton.setLocation(510, 440);
        saveButton.addActionListener(this);
        saveButton.addMouseListener(this);

        if (currentLanguage.equals("English")) {
            frame.setTitle("Provide Ratings");
            title.setText("Feedback");
            subtitle.setText("<html>We would like to hear from you.<br>Please assist us by filling in the following information.</html>");
            scalePrompt.setText("Overall rating for our system: ");
            feedbackPrompt.setText("Feedbacks / Comments of the system: ");
            cancelButton.setText("Cancel");
            saveButton.setText("Save");
        } else if (currentLanguage.equals("Malay")) {
            frame.setTitle("Halaman Penilaian");
            title.setText("Penilaian");
            subtitle.setText("<html>Kami ingin mengetahui pandangan anda.<br>Sila mengisikan semua butiran di bawah.</html>");
            scalePrompt.setText("Penilaian keseluruhan untuk sistem: ");
            feedbackPrompt.setText("Pandangan terhadap sistem: ");
            cancelButton.setText("Batal");
            saveButton.setText("Simpan");
        }

        frame.add(cancelButton);
        frame.add(saveButton);
        frame.add(title);
        frame.add(subtitle);
        frame.add(scalePrompt);
        frame.add(buttonPanel);
        frame.add(feedbackPrompt);
        frame.add(feedbackInput);
        frame.add(background);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {

            int systemRating;
            try {
                if (button1.isSelected()) {
                    systemRating = 1;
                } else if (button2.isSelected()) {
                    systemRating = 2;
                } else if (button3.isSelected()) {
                    systemRating = 3;
                } else if (button4.isSelected()) {
                    systemRating = 4;
                } else if (button5.isSelected()) {
                    systemRating = 5;
                } else {
                    throw new NullPointerException();
                }

                if (feedbackInput.getText().isBlank()) {
                    throw new NullPointerException();
                }

                SystemRating rating = new SystemRating(Instant.now(), String.valueOf(systemRating), feedbackInput.getText().strip());
                SystemRating.addSystemRatings(rating);

                if (currentLanguage.equals("English")) {
                    JOptionPane.showMessageDialog(frame, "Successful submission. Hope you have a great experience using our system.", "Submission Success", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                } else if (currentLanguage.equals("Malay")) {
                    JOptionPane.showMessageDialog(frame, "Berjaya dihantar. Semoga anda menikmati sistem kami.", "Berjaya Dihantar", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));

                }
                frame.dispose();
                UserFrame.frame.setEnabled(true);

            } catch (NullPointerException ex) {
                if (currentLanguage.equals("English")) {
                    JOptionPane.showMessageDialog(frame, "Invalid choice / feedback. Please try again.", "Invalid input", JOptionPane.ERROR_MESSAGE);
                } else if (currentLanguage.equals("Malay")) {
                    JOptionPane.showMessageDialog(frame, "Pilihan / Komen yang tidak lengkap. Sila cuba lagi.", "Penilaian Tidak Sah", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == cancelButton) {
            frame.dispose();
            UserFrame.frame.setEnabled(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == feedbackInput && feedbackInput.getText().length() > 120) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == feedbackInput && e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
