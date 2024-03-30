import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuestMainPage extends JLayeredPane implements MouseListener {
    static JLabel title, promptSignUp, guestMessage;
    GuestMainPage() {

        this.setSize(970, 768);

        ImageIcon background = new ImageIcon("asset/User Background.jpg");
        JLabel backgroundPlaceholder = new JLabel();
        backgroundPlaceholder.setBounds(0, 0, 970, 768);
        backgroundPlaceholder.setIcon(background);
        this.add(backgroundPlaceholder, JLayeredPane.DEFAULT_LAYER);

        title = new JLabel("Sign up now to unlock all features of our system!");
        title.setFont(new Font("Advent Pro", Font.BOLD, 40));
        title.setBounds(50, 30, 800, 100);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        this.add(title, JLayeredPane.PALETTE_LAYER);

        promptSignUp = new JLabel("<html>Click <u>here</u> to sign up now.</html>");
        promptSignUp.setFont(new Font("Avenir", Font.PLAIN, 25));
        promptSignUp.setBounds(50, 120, 300, 50);
        promptSignUp.setHorizontalAlignment(JLabel.LEFT);
        promptSignUp.setVerticalAlignment(JLabel.CENTER);
        this.add(promptSignUp, JLayeredPane.PALETTE_LAYER);
        promptSignUp.addMouseListener(this);

        JLayeredPane textBox = new JLayeredPane();

        JPanel bottomLayer = new JPanel();
        bottomLayer.setLayout(new BorderLayout());
        bottomLayer.setBounds(0, 0, 300, 100);
        bottomLayer.add(new GuestTextBox());
        textBox.add(bottomLayer, DEFAULT_LAYER);

        guestMessage = new JLabel("<html>You can still try out our<br>movie search feature here.</html>");
        guestMessage.setFont(new Font("Avenir", Font.PLAIN, 20));
        guestMessage.setBounds(30, 0, 300, 100);
        textBox.add(guestMessage, PALETTE_LAYER);

        textBox.setBounds(95, 200, 300, 100);
        this.add(textBox, JLayeredPane.MODAL_LAYER);

        ImageIcon arrowImage = new ImageIcon("asset/Arrow.png");
        Image resizingArrowImage = arrowImage.getImage();
        resizingArrowImage = resizingArrowImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        arrowImage = new ImageIcon(resizingArrowImage);
        JLabel arrowPlaceholder = new JLabel();
        arrowPlaceholder.setBounds(0, 190, 100, 100);
        arrowPlaceholder.setIcon(arrowImage);
        this.add(arrowPlaceholder, JLayeredPane.PALETTE_LAYER);

        ImageIcon decorationImage = new ImageIcon("asset/Decorate.png");
        Image resizingDecorationImage = decorationImage.getImage();
        resizingDecorationImage = resizingDecorationImage.getScaledInstance(900, 430, Image.SCALE_SMOOTH);
        decorationImage = new ImageIcon(resizingDecorationImage);
        JLabel decoratePlaceholder = new JLabel();
        decoratePlaceholder.setIcon(decorationImage);
        decoratePlaceholder.setBounds(40, 270, 930, 500);
        this.add(decoratePlaceholder, JLayeredPane.PALETTE_LAYER);

    }

    public static void changeLanguage(String language) {
        if (language.equals("English")) {
            title.setText("Sign up now to unlock all features of our system!");
            promptSignUp.setText("<html>Click <u>here</u> to sign up now.</html>");
            guestMessage.setText("<html>You can still try out our<br>movie search feature here.</html>");
        } else if (language.equals("Malay")) {
            title.setText("Daftar sekarang untuk membuka kunci semua ciri sistem kami!");
            promptSignUp.setText("<html>Click <u>here</u> to sign up now.</html>");
            guestMessage.setText("<html>You can still try out our<br>movie search feature here.</html>");
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
        try {
            new SignUp(GuestFrame.frame.getX(), GuestFrame.frame.getY(), "Guest");
            GuestFrame.frame.setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "System error. Please inspect for errors.");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        promptSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        promptSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}

class GuestTextBox extends JPanel {
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(Color.WHITE);
        g2D.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);
        g2D.setPaint(Color.BLACK);
        Stroke dotted = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2D.setStroke(dotted);
        g2D.drawRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
    }
}