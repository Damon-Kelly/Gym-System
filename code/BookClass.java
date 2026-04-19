// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              10/04/2026
// Purpose :           GUI for members to browse and book gym classes.

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.*;

/**
 * BookClass.java
 * Displays all available gym classes in a table and allows
 * the logged-in member to book a selected class.
 * Uses QueryHelpers.getAllClasses() and InsertBooking DAO.
 */
public class BookClass extends JFrame
{
    private int memberID;
    private String memberName;

    private final JTable classTable;
    private final DefaultTableModel tableModel;
    private final JButton bookButton;
    private final JButton backButton;

    public BookClass(int memberID, String memberName)
    {
        this.memberID = memberID;
        this.memberName = memberName;

        setTitle("Book a Class");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Table setup ---
        String[] columns = { "Class ID", "Title", "Schedule", "Capacity", "Remaining" };
        tableModel = new DefaultTableModel(columns, 0)
        {
            // Make table cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        classTable = new JTable(tableModel);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classTable.getTableHeader().setReorderingAllowed(false);

        // Hide the Class ID column from the user (still accessible for booking)
        classTable.getColumnModel().getColumn(0).setMinWidth(0);
        classTable.getColumnModel().getColumn(0).setMaxWidth(0);
        classTable.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(classTable);

        // --- Load class data ---
        // Each String[]: [ClassID, Title, Schedule, Capacity, Remaining]
        String[][] classes = QueryHelpers.getAllClasses();

        if (classes.length == 0)
        {
            JOptionPane.showMessageDialog(null,
                "No classes are currently available.",
                "No Classes",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            for (String[] row : classes)
            {
                tableModel.addRow(row);
            }
        }

        // --- Button panel ---
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        bookButton = new JButton("Book Selected Class");
        backButton = new JButton("Back");

        buttonPanel.add(backButton);
        buttonPanel.add(bookButton);

        // --- Layout ---
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Listeners ---
        bookButton.addActionListener(new BookHandler());
        backButton.addActionListener(new BackHandler());

        setVisible(true);

    } // end constructor


    // --- Book Handler ---
    class BookHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int selectedRow = classTable.getSelectedRow();

            if (selectedRow == -1)
            {
                JOptionPane.showMessageDialog(null,
                    "Please select a class to book.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get the ClassID from the hidden column (index 0)
            int classID = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 0));
            String classTitle = (String) tableModel.getValueAt(selectedRow, 1);

            // Confirm booking with user
            int confirm = JOptionPane.showConfirmDialog(null,
                "Book class: " + classTitle + "?",
                "Confirm Booking",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION)
            {
                try
                {
                    int capacity = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 3));
                    int bookedCount = QueryHelpers.getConfirmedBookingCount(classID);

                    if (bookedCount >= capacity)
                    {
                        JOptionPane.showMessageDialog(null,
                            "This class is full. Please choose another class or try a different time.",
                            "Class Full",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Check if member already has a booking for this class
                    if (QueryHelpers.hasBookingForClass(memberID, classID))
                    {
                        JOptionPane.showMessageDialog(null,
                            "You already have a booking for this class!",
                            "Duplicate Booking",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Use today's date as booking date
                    String bookingDate = LocalDate.now().toString();

                    InsertBooking.insertBooking(bookingDate, "Confirmed", memberID, classID);

                    JOptionPane.showMessageDialog(null,
                        "Class booked successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                    dispose();
                    //noinspection ResultOfObjectAllocationIgnored
                    new MemberDashboard(memberID, memberName);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,
                        "Error booking class: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }

        } // end actionPerformed

    } // end BookHandler


    // --- Back Handler ---
    class BackHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            //noinspection ResultOfObjectAllocationIgnored
            new MemberDashboard(memberID, memberName);
        }

    } // end BackHandler

} // end class
