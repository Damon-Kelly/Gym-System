// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              14/04/2026
// Purpose :           GUI for member to view and manage their class bookings.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * ViewMyBookings.java
 * Member screen showing all their bookings with option to cancel.
 * Uses QueryHelpers.getBookingsByMemberID() to fetch data.
 */
public class ViewMyBookings extends JFrame
{
    private int memberID;
    private String memberName;

    private JTable bookingsTable;
    private DefaultTableModel tableModel;
    private JButton cancelButton;
    private JButton backButton;

    public ViewMyBookings(int memberID, String memberName)
    {
        this.memberID = memberID;
        this.memberName = memberName;

        setTitle("My Bookings");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Table setup ---
        String[] columns = { "Booking ID", "Class", "Schedule", "Booking Date", "Status" };
        tableModel = new DefaultTableModel(columns, 0)
        {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        bookingsTable = new JTable(tableModel);
        bookingsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookingsTable.getTableHeader().setReorderingAllowed(false);
        bookingsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        bookingsTable.setRowHeight(24);

        // Hide the Booking ID column (column 0)
        bookingsTable.getColumnModel().getColumn(0).setMinWidth(0);
        bookingsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        bookingsTable.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(bookingsTable);

        // --- Load booking data ---
        loadBookingData();

        // --- Button panel ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        cancelButton = new JButton("Cancel Selected Booking");
        cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 14));

        buttonPanel.add(backButton);
        buttonPanel.add(cancelButton);

        JLabel countLabel = new JLabel("Total bookings: " + tableModel.getRowCount());
        countLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        buttonPanel.add(countLabel);

        // --- Layout ---
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Listeners ---
        cancelButton.addActionListener(new CancelHandler());
        backButton.addActionListener(new BackHandler());

        setVisible(true);

    } // end constructor


    /**
     * Loads all bookings for this member from the database.
     */
    private void loadBookingData()
    {
        tableModel.setRowCount(0);

        String[][] bookings = QueryHelpers.getBookingsByMemberID(memberID);

        for (String[] row : bookings)
        {
            tableModel.addRow(row);
        }
    }


    // --- Cancel Handler ---
    class CancelHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int selectedRow = bookingsTable.getSelectedRow();

            if (selectedRow == -1)
            {
                JOptionPane.showMessageDialog(null,
                    "Please select a booking to cancel.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            String classTitle = (String) tableModel.getValueAt(selectedRow, 1);
            int bookingID = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 0));

            int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to cancel your booking for: " + classTitle + "?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION)
            {
                try
                {
                    DeleteBooking.deleteBooking(bookingID);

                    JOptionPane.showMessageDialog(null,
                        "Booking cancelled successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                    loadBookingData();
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,
                        "Error cancelling booking: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }

        } // end actionPerformed

    } // end CancelHandler


    // --- Back Handler ---
    class BackHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
            new MemberDashboard(memberID, memberName);
        }

    } // end BackHandler

} // end class
