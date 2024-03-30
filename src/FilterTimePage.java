import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class FilterTimePage implements ActionListener, MouseListener {
    static JFrame frame;
    static JLabel filterTimeLabel, selectLabel, selectPeriodLabel;
    static JPanel backgroundPanel;
    static JRadioButton monthlyRadioButton, yearlyRadioButton;
    static ButtonGroup buttonGroup;
    static JButton button;
    final static String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    final static String[] year = {"2020", "2021", "2022", "2023", "2024"};
    JComboBox<String> yearSelect, monthSelect;

    public FilterTimePage() {
        frame = new JFrame("Filter Time Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocation(AdminFrame.frame.getX() + (AdminFrame.frame.getWidth() - frame.getWidth()) /  2, AdminFrame.frame.getY() + (AdminFrame.frame.getHeight() - frame.getHeight()) /  2);
        frame.setLayout(null);
        frame.setResizable(false);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setSize(frame.getWidth(), frame.getHeight());

        filterTimeLabel = new JLabel("Filter Time");
        filterTimeLabel.setFont(new Font("Advent Pro", Font.BOLD, 30));
        filterTimeLabel.setBounds(50, 20, 200, 100);

        selectLabel = new JLabel("Select Type:");
        selectLabel.setFont(new Font("Avenir", Font.PLAIN, 20));
        selectLabel.setBounds(50, 120, 150, 30);

        monthlyRadioButton = new JRadioButton("Monthly");
        monthlyRadioButton.setFont(new Font("Avenir", Font.PLAIN, 20));
        monthlyRadioButton.setBounds(200, 120, 150, 30);
        monthlyRadioButton.setBackground(Color.WHITE);
        monthlyRadioButton.addActionListener(this);

        yearlyRadioButton = new JRadioButton("Yearly");
        yearlyRadioButton.setFont(new Font("Avenir", Font.PLAIN, 20));
        yearlyRadioButton.setBounds(350, 120, 150, 30);
        yearlyRadioButton.setBackground(Color.WHITE);
        yearlyRadioButton.addActionListener(this);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(monthlyRadioButton);
        buttonGroup.add(yearlyRadioButton);

        selectPeriodLabel = new JLabel("Select Period:");
        selectPeriodLabel.setFont(new Font("Avenir", Font.PLAIN,20));
        selectPeriodLabel.setBounds(50,160,150,100);

        yearSelect = new JComboBox<>(year);

        monthSelect = new JComboBox<>(month);

        button = new JButton("Okay");
        button.setFont(new Font("Avenir", Font.PLAIN, 20));
        button.setBounds(260,270,200,50);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.addActionListener(this);
        button.addMouseListener(this);

        backgroundPanel.add(selectLabel);
        backgroundPanel.add(filterTimeLabel);
        backgroundPanel.add(monthlyRadioButton);
        backgroundPanel.add(yearlyRadioButton);
        backgroundPanel.add(selectPeriodLabel);
        backgroundPanel.add(button);

        frame.add(backgroundPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == monthlyRadioButton) {

            if (yearSelect != null) {
                yearSelect.setSelectedIndex(0);
                backgroundPanel.remove(yearSelect);
            }

            monthSelect = new JComboBox<>(month);
            monthSelect.setBounds(200, 160, 150, 100);
            monthSelect.setFont(new Font("Avenir", Font.PLAIN, 18));

            yearSelect = new JComboBox<>(year);
            yearSelect.setSelectedIndex(0);
            yearSelect.setBounds(360, 160, 100, 100);
            yearSelect.setFont(new Font("Avenir", Font.PLAIN, 18));

            backgroundPanel.add(monthSelect);
            backgroundPanel.add(yearSelect);

            backgroundPanel.repaint();
            backgroundPanel.revalidate();

        } else if (e.getSource() == yearlyRadioButton) {
            if (monthSelect != null) {
                monthSelect.setSelectedIndex(0);
                backgroundPanel.remove(monthSelect);
            }

            if (yearSelect != null) {
                yearSelect.setSelectedIndex(0);
                backgroundPanel.remove(yearSelect);
            }

            yearSelect = new JComboBox<>(year);
            yearSelect.setSelectedIndex(0);
            yearSelect.setBounds(200, 160, 100, 100);
            yearSelect.setFont(new Font("Avenir", Font.PLAIN, 18));

            backgroundPanel.add(yearSelect);

            backgroundPanel.revalidate();
            backgroundPanel.repaint();

        } else if (e.getSource() == button) {

            String month = null, year;
            if (monthSelect != null) {
                month = Objects.requireNonNull(monthSelect.getSelectedItem()).toString();
                year = Objects.requireNonNull(yearSelect.getSelectedItem()).toString();
            } else {
                year = Objects.requireNonNull(yearSelect.getSelectedItem()).toString();
            }

            switch (month) {
                case "January":
                    month = "1";
                    break;
                case "February":
                    month = "2";
                    break;
                case "March":
                    month = "3";
                    break;
                case "April":
                    month = "4";
                    break;
                case "May":
                    month = "5";
                    break;
                case "June":
                    month = "6";
                    break;
                case "July":
                    month = "7";
                    break;
                case "August":
                    month = "8";
                    break;
                case "September":
                    month = "9";
                    break;
                case "October":
                    month = "10";
                    break;
                case "November":
                    month = "11";
                    break;
                case "December":
                    month = "12";
                    break;
            }

            SystemRating.readSystemRatingFromFile();
            SystemRating.overallRatingList.sort(Comparator.comparing(SystemRating::getTimestamp));

            ArrayList<SystemRating> filteredRating = new ArrayList<>();

            for (SystemRating rating: SystemRating.overallRatingList.reversed()) {
                LocalDateTime timeOfRating = LocalDateTime.ofInstant(rating.timestamp, ZoneId.of("Asia/Kuala_Lumpur"));
                if (yearlyRadioButton.isSelected()) {
                    if (timeOfRating.getYear() == Integer.parseInt(year)) {
                        filteredRating.add(rating);
                    }
                } else if (monthlyRadioButton.isSelected()) {
                    if (timeOfRating.getYear() == Integer.parseInt(year) && timeOfRating.getMonthValue() == Integer.parseInt(month)) {
                        filteredRating.add(rating);
                    }
                }
            }

            if (filteredRating.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Sorry, there is no data for the selected time. Please try another one.", "Invalid Data", JOptionPane.ERROR_MESSAGE);
            } else {
                AdminFrame.overallLayer.remove(AdminFrame.feedbackLayer);
                AdminFrame.feedbackLayer = new FeedbackSummaryPage(filteredRating);
                AdminFrame.overallLayer.add(AdminFrame.feedbackLayer, "Feedback");
                AdminFrame.feedbackLayer.repaint();
                AdminFrame.feedbackLayer.revalidate();

                AdminFrame.cardLayout.show(AdminFrame.overallLayer, "Feedback");
                JOptionPane.showMessageDialog(frame, "Results filtered successfully.", "Success filter", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));
                frame.dispose();
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

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}




