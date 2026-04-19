// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              14/04/2026
// Purpose :           Trainer screen showing all classes they are running with confirmed booking counts.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * ViewSchedule.java
 * Trainer screen showing all classes they are running with confirmed booking counts.
 * Uses QueryHelpers.getTrainerClasses() to display trainer's schedule.
 */
public class ViewSchedule extends JFrame
{
    private int trainerID;
    private JButton backButton;

    public ViewSchedule(int trainerID)
    {
        this.trainerID = trainerID;

        setTitle("My Schedule");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Table setup ---
        // Columns: Class Title, Schedule, Confirmed Bookings
        String[] columns = { "Class Title", "Schedule", "Confirmed Bookings" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable scheduleTable = new JTable(tableModel);
        scheduleTable.getTableHeader().setReorderingAllowed(false);
        scheduleTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        scheduleTable.setRowHeight(24);
        scheduleTable.setAutoCreateRowSorter(true); // allows clicking column headers to sort

        JScrollPane scrollPane = new JScrollPane(scheduleTable);

        // --- Load trainer's classes ---
        // Each String[]: [ClassID, Title, Schedule, ConfirmedCount]
        String[][] classes = QueryHelpers.getTrainerClasses(trainerID);

        if (classes.length == 0)
        {
            JOptionPane.showMessageDialog(null,
                "You are not assigned to any classes.",
                "No Classes",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            for (String[] row : classes)
            {
                // Display: [Title, Schedule, ConfirmedCount] (hide ClassID)
                tableModel.addRow(new Object[] { row[1], row[2], row[3] });
            }
        }

        // --- Button panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonPanel.add(backButton);

        // Class count label
        JLabel countLabel = new JLabel("Total classes: " + classes.length);
        countLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        buttonPanel.add(countLabel);

        // --- Layout ---
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Listener ---
        backButton.addActionListener(new BackHandler());

        setVisible(true);

    } // end constructor


    // --- Back Handler ---
    class BackHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            new TrainerDashboard(trainerID);
        }

    } // end BackHandler

} // end class
