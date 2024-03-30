import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BarChart extends JPanel {

    int[] data;
    JLabel magnitude;
    int width, max;

    public BarChart(int[] data) {

        this.setLayout(null);

        this.data = data;
        this.setBackground(new Color(255, 255, 255, 0));

        width = (380 - (data.length - 1) * 20 - 40) / data.length;
        max = Arrays.stream(data).max().getAsInt();

        for (int i = 0; i < data.length; i ++) {
            int startingX = 20 + (width + 20) * i;
            int height = data[i] * 400 / max;

            magnitude = new JLabel("(" + data[i] + ")");
            magnitude.setFont(new Font("Avenir", Font.PLAIN, 16));
            magnitude.setHorizontalAlignment(JLabel.CENTER);
            magnitude.setSize(width, 50);
            magnitude.setLocation(startingX, 398 - height);
            add(magnitude);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0, 448, 380, 448);

        g2d.setPaint(new Color(0, 0, 128));

        for (int i = 0; i < data.length; i ++) {
            int startingX = 20 + (width + 20) * i;
            int height = data[i] * 400 / max;
            g2d.fillRect(startingX, 448 - height, width, height);
        }
    }
}
