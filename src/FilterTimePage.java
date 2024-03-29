import javax.swing.*;
import java.awt.*;

public class FilterTimePage {
    static JFrame frame;
    static JLabel filterTimeLabel, selectLabel, selectPeriodLabel;
    static JPanel backgroundPanel;
    static JRadioButton monthlyRadioButton, yearlyRadioButton;
    static ButtonGroup buttonGroup;
    static JButton button;

    public static void main(String[] args) {
        frame = new JFrame("Feedback Summary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setResizable(false);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundPanel.setSize(frame.getWidth(), frame.getHeight());

        filterTimeLabel = new JLabel("Filter Time");
        filterTimeLabel.setFont(new Font("Advent Pro", Font.BOLD, 30));
        filterTimeLabel.setBounds(50, 20, 200, 100);

        selectLabel = new JLabel("Select Type:");
        selectLabel.setFont(new Font("Avenir", Font.PLAIN, 20));
        selectLabel.setBounds(50, 100, 200, 100);

        monthlyRadioButton = new JRadioButton("Monthly");
        monthlyRadioButton.setFont(new Font("Avenir", Font.PLAIN, 20));
        monthlyRadioButton.setBounds(200, 140, 100, 30);
        monthlyRadioButton.setBackground(Color.WHITE);

        yearlyRadioButton = new JRadioButton("Yearly");
        yearlyRadioButton.setFont(new Font("Avenir", Font.PLAIN, 20));
        yearlyRadioButton.setBounds(200, 200, 100, 30);
        yearlyRadioButton.setBackground(Color.WHITE);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(monthlyRadioButton);
        buttonGroup.add(yearlyRadioButton);

        selectPeriodLabel = new JLabel("Select Period");
        selectPeriodLabel.setFont(new Font("Advent Pro", Font.PLAIN,20));
        selectPeriodLabel.setBounds(50,250,200,100);

        button = new JButton("Okay");
        button.setFont(new Font("Avenir", Font.PLAIN, 25));
        button.setBounds(50,350,500,50);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        backgroundPanel.add(selectLabel);
        backgroundPanel.add(filterTimeLabel);
        backgroundPanel.add(monthlyRadioButton);
        backgroundPanel.add(yearlyRadioButton);
        backgroundPanel.add(selectPeriodLabel);
        backgroundPanel.add(button);

        frame.add(backgroundPanel);
        frame.setVisible(true);
    }
}

