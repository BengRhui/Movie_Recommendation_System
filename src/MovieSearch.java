import javax.swing.*;
import java.awt.*;

public class MovieSearch extends JLayeredPane {

    JLabel title, backgroundPlaceholder, searchLogoPlaceholder;
    JPanel searchBarPlaceholder;

    MovieSearch() {

        this.setSize(970, 768);

        ImageIcon background = new ImageIcon("src/User Background.jpg");
        backgroundPlaceholder = new JLabel();
        backgroundPlaceholder.setIcon(background);
        backgroundPlaceholder.setBounds(0, 0, 970, 768);
        this.add(backgroundPlaceholder, JLayeredPane.DEFAULT_LAYER);

        title = new JLabel("Search for movies here:");
        title.setFont(new Font("Advent Pro", Font.BOLD, 40));
        title.setBounds(50, 30, 800, 100);
        this.add(title, JLayeredPane.PALETTE_LAYER);

        searchBarPlaceholder = new JPanel();
        searchBarPlaceholder.setLayout(new BorderLayout());
        searchBarPlaceholder.add(new SearchBar());
        searchBarPlaceholder.setBounds(50, 130, 850, 75);
        this.add(searchBarPlaceholder, JLayeredPane.PALETTE_LAYER);

        ImageIcon searchIcon = new ImageIcon("src/Search Icon.png");
        Image resizingSearchIcon = searchIcon.getImage();
        resizingSearchIcon = resizingSearchIcon.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(resizingSearchIcon);
        searchLogoPlaceholder = new JLabel();
        searchLogoPlaceholder.setIcon(searchIcon);
        searchLogoPlaceholder.setBounds(820, 143, 50, 50);
        searchLogoPlaceholder.setHorizontalAlignment(JLabel.CENTER);
        searchLogoPlaceholder.setVerticalAlignment(JLabel.CENTER);
        this.add(searchLogoPlaceholder, JLayeredPane.MODAL_LAYER);
    }
}

class SearchBar extends JPanel {
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(Color.BLACK);
        g2D.setStroke(new BasicStroke(3));
        g2D.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 75, 75);
        g2D.setPaint(Color.WHITE);
        g2D.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 75, 75);
    }
}
