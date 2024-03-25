import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserMainPage extends JLayeredPane implements MouseListener {

    JPanel container;
    CardLayout cardLayout = new CardLayout();
    JLabel previous, next;
    int currentDisplay = 0, numberOfPages;
    UserMainPage(String userID) {
        this.setSize(970, 768);

        ImageIcon background = new ImageIcon("src/User Background.jpg");
        JLabel backgroundPlaceholder = new JLabel();
        backgroundPlaceholder.setIcon(background);
        backgroundPlaceholder.setBounds(0, 0, 970, 768);
        this.add(backgroundPlaceholder, JLayeredPane.DEFAULT_LAYER);

        JLabel title = new JLabel("Top picks we recommend:");
        title.setFont(new Font("Advent Pro", Font.BOLD, 40));
        title.setBounds(50, 30, 800, 100);
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.CENTER);
        this.add(title, JLayeredPane.PALETTE_LAYER);

        // Search results - testing
        // Final filtered array
        ArrayList<Recommendation> listOfRecommendation = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/recommendation.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayFromFile = line.split(";");
                if (Double.parseDouble(arrayFromFile[0]) == Double.parseDouble(userID)) {
                    Recommendation newRec = new Recommendation(arrayFromFile[0], arrayFromFile[1], arrayFromFile[2]);
                    listOfRecommendation.add(newRec);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error ");
        }

        /*

        int initialSize = listOfRecommendation.size();
        numberOfPages = (int) Math.ceil(initialSize / 10.0);

        container = new JPanel(cardLayout);
        //Map<String, JPanel> record = new HashMap<>();

        Iterator<Recommendation> iteratorList = listOfRecommendation.iterator();
        for (int i = 0; i < numberOfPages; i ++) {
            JPanel panel = new JPanel(new GridLayout(2, 5, 10, 10));
            int count = 0;
            while (iteratorList.hasNext() && count < 10) {
                Recommendation item = iteratorList.next();
                JLabel label = new JLabel(Recommendation.getNameFromRecommendation(String.valueOf(item.movieID)));
                JPanel holder = new JPanel();
                holder.setBackground(Color.BLUE);
                holder.add(label);
                panel.add(holder);
                iteratorList.remove();
                count++;
            }
            if (i == numberOfPages - 1) {
                int remaining = 10 - (initialSize % 10);
                for (int j = 0; j < remaining; j ++) {
                    JPanel blank = new JPanel();
                    blank.setBackground(Color.WHITE);
                    panel.add(blank);
                }
            }
            container.add(panel, "card" + i);
            //record.put("card" + i, panel);
        }


        cardLayout.show(container, "card0");
        container.setBounds(0, 100, 970, 600);
        this.add(container, JLayeredPane.PALETTE_LAYER);

        previous = new JLabel("Previous");
        previous.setBounds(20, 700, 100, 50);
        previous.addMouseListener(this);
        this.add(previous, JLayeredPane.PALETTE_LAYER);
        previous.setVisible(false);

        next = new JLabel("Next");
        next.setBounds(400, 700, 100, 50);
        next.addMouseListener(this);
        this.add(next, JLayeredPane.PALETTE_LAYER);


         */

        // Temporary field
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBounds(200, 300, 600, 500);
        panel.setBackground(new Color(255, 255, 255, 0));

        System.out.println(listOfRecommendation.size());

        if (listOfRecommendation.isEmpty()) {
            JLabel name = new JLabel("No recommendation yet. Please rate at least 10 movies to obtain recommendations.");
            panel.add(name);
        }

        for (Recommendation recommendation : listOfRecommendation) {
            JLabel name = new JLabel(Recommendation.getNameFromRecommendation(String.valueOf(recommendation.movieID)));
            name.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.setPreferredSize(new Dimension(600, 100));
            panel.add(name);
        }

        add(panel, PALETTE_LAYER);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == previous){
            next.setVisible(true);
            currentDisplay -= 1;
            cardLayout.show(container, "card" + currentDisplay);
            if (currentDisplay == 0) {
                previous.setVisible(false);
            }
        } else if (e.getSource() == next){
            previous.setVisible(true);
            currentDisplay += 1;
            cardLayout.show(container, "card" + currentDisplay);
            if (currentDisplay == numberOfPages - 1) {
                next.setVisible(false);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        previous.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        previous.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        next.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
