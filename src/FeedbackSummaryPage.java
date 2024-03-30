import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;

public class FeedbackSummaryPage extends JLayeredPane {

    static JLabel feedbackSummary, recentFeedback, filterImage, graphTitle, backgroundImage, graphLabel1, graphLabel2,
            graphLabel3, graphLabel4, graphLabel5;
    JPanel scrollBackground, graphImage;
    JScrollPane scrollPane;
    static ArrayList<SystemRating> displayRating = new ArrayList<>();

    public FeedbackSummaryPage (ArrayList<SystemRating> ratingList){

        if (ratingList == null) {
            SystemRating.readSystemRatingFromFile();
            SystemRating.overallRatingList.sort(Comparator.comparing(SystemRating::getTimestamp));
            displayRating.addAll(SystemRating.overallRatingList.reversed());
        } else {
            displayRating = ratingList;
        }

        this.setSize(970, 768);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("asset/Admin Background.png"));
        backgroundImage.setBounds(0, 0, this.getWidth(), this.getHeight());

        feedbackSummary = new JLabel("Feedback Summary");
        feedbackSummary.setFont(new Font("Advent Pro", Font.BOLD, 40));
        feedbackSummary.setBounds(50, 30, 700, 100);

        graphTitle = new JLabel("<html><u>Distribution of User Ratings</u></html>");
        graphTitle.setFont(new Font("Avenir", Font.BOLD, 20));
        graphTitle.setBounds(50, 130, 300, 30);

        int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
        for (SystemRating rating: displayRating) {
            switch (rating.rating) {
                case 1:
                    count1 ++;
                    break;
                case 2:
                    count2 ++;
                    break;
                case 3:
                    count3 ++;
                    break;
                case 4:
                    count4 ++;
                    break;
                case 5:
                    count5 ++;
                    break;
            }
        }

        int[] data = {count1, count2, count3, count4, count5};
        graphImage = new BarChart(data);
        graphImage.setBounds(50, 190, 380, 450);

        graphLabel1 = new JLabel("1");
        graphLabel1.setFont(new Font("Avenir", Font.PLAIN, 20));
        graphLabel1.setBounds(90, 640, 380, 50);

        graphLabel2 = new JLabel("2");
        graphLabel2.setFont(new Font("Avenir", Font.PLAIN, 20));
        graphLabel2.setBounds(163, 640, 380, 50);

        graphLabel3 = new JLabel("3");
        graphLabel3.setFont(new Font("Avenir", Font.PLAIN, 20));
        graphLabel3.setBounds(235, 640, 380, 50);

        graphLabel4 = new JLabel("4");
        graphLabel4.setFont(new Font("Avenir", Font.PLAIN, 20));
        graphLabel4.setBounds(308, 640, 380, 50);

        graphLabel5 = new JLabel("5");
        graphLabel5.setFont(new Font("Avenir", Font.PLAIN, 20));
        graphLabel5.setBounds(379, 640, 380, 50);

        filterImage = new JLabel();
        ImageIcon filter = new ImageIcon("asset/Filter.png");
        Image resizeImage = filter.getImage();
        resizeImage = resizeImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        filter = new ImageIcon(resizeImage);
        filterImage.setIcon(filter);
        filterImage.setBounds(800,30,100, 100);
        filterImage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                new FilterTimePage();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                filterImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                filterImage.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        recentFeedback = new JLabel("<html><u>Recent Feedbacks</u><html>");
        recentFeedback.setFont(new Font("Avenir", Font.BOLD, 20));
        recentFeedback.setBounds(500, 130, 300, 30);

        scrollBackground = new JPanel();
        scrollBackground.setLayout(new BoxLayout(scrollBackground, BoxLayout.Y_AXIS));
        scrollBackground.setBackground(new Color(163, 193, 239));

        int height = displayRating.size() * (150 + 10);

        scrollBackground.setPreferredSize(new Dimension(350, height));

        for (SystemRating rating: displayRating) {

            JPanel panel = new JPanel(null);
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            panel.setPreferredSize(new Dimension(350, 150));

            JLabel starRating = new JLabel("Ratings: " + rating.rating +  " stars");
            starRating.setFont(new Font("Avenir", Font.PLAIN, 18));
            starRating.setBounds(20, 20, 330, 30);

            JLabel comment = new JLabel("<html>" + rating.userFeedback + "</html>");
            comment.setFont(new Font("Avenir", Font.PLAIN, 16));
            comment.setBounds(20, 60, 330, 100);
            comment.setVerticalAlignment(JLabel.TOP);

            panel.add(starRating);
            panel.add(comment);

            scrollBackground.add(panel);
            scrollBackground.add(Box.createVerticalStrut(20));
        }

        scrollPane = new JScrollPane(scrollBackground);
        scrollPane.setBounds(500, 190, 380, 500);
        scrollPane.setBorder(null);

        this.add(feedbackSummary, JLayeredPane.PALETTE_LAYER);
        this.add(filterImage, JLayeredPane.PALETTE_LAYER);
        this.add(graphTitle, JLayeredPane.PALETTE_LAYER);
        this.add(graphImage, JLayeredPane.PALETTE_LAYER);
        this.add(graphLabel1, JLayeredPane.PALETTE_LAYER);
        this.add(graphLabel2, JLayeredPane.PALETTE_LAYER);
        this.add(graphLabel3, JLayeredPane.PALETTE_LAYER);
        this.add(graphLabel4, JLayeredPane.PALETTE_LAYER);
        this.add(graphLabel5, JLayeredPane.PALETTE_LAYER);
        this.add(recentFeedback, JLayeredPane.PALETTE_LAYER);
        this.add(scrollPane, JLayeredPane.PALETTE_LAYER);
        this.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);

    }


}
